package frame.WorkFrame.TransferFrame;

import common.TransferInfo;
import common.DateTimeThread;
import frame.Element.myButton;
import frame.CommonFrame.ImageFrame;
import frame.WorkFrame.MainInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransferMoneyInput extends ImageFrame {
    JTextField transferMoney;
    myButton confirmBtn,deleteBtn,backupBtn;
    TransferInfo info;
    public TransferMoneyInput(TransferInfo info){
        this.info = info;
        addComponents();
        addListener();
        setVisible(true);
    }
    public void addComponents(){

        JLabel describe=new JLabel("请输入转账金额：");
        describe.setBounds(200, 200, 350, 40);
        describe.setFont(new Font("华文琥珀", 1, 28));
        add(describe);

        transferMoney = new JTextField();
        transferMoney.setBounds(200, 250, 350, 40);
        transferMoney.setFont(new Font("Arial", 1, 28));
        if(info.getMoney()!=0.0)
            transferMoney.setText(String.valueOf(info.getMoney()));
        add(transferMoney);

        deleteBtn=new myButton("更 正");
        confirmBtn = new myButton("确 认");
        backupBtn = new myButton("返 回");

        deleteBtn.setBounds(650, 350, 120, 60);
        confirmBtn.setBounds(650, 440, 120, 60);
        backupBtn.setBounds(50, 440, 120, 60);

        add(backupBtn);
        add(deleteBtn);
        add(confirmBtn);
    }

     /**
     * 提交按钮的监听器
     */
    public void addListener(){

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                String  money = transferMoney.getText();
                info.setMoney(Double.valueOf(money));
                new DateTimeThread(new TransferConfim(info));


            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNumber =transferMoney.getText();
                if(cardNumber==null || cardNumber.isEmpty())
                    return;
                else if(cardNumber.length()==1)
                    transferMoney.setText("");
                else{
                    transferMoney.setText(cardNumber.substring(0,cardNumber.length()-1));
                }
                transferMoney.grabFocus();
            }
        });
        backupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DateTimeThread(new MainInterface());
            }
        });


        /**
         * 输入检测：（1）必须为数字，（2）长度不超过10
         * */
        transferMoney.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int code = e.getKeyChar();
                if( code<48 || code > 57 || transferMoney.getText().length()==8)//!(code>=KeyEvent.VK_0&&code<=KeyEvent.VK_9) ||
                {
                    e.consume();
                }
            }
        });
    }

}
