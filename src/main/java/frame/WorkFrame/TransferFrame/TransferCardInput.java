package frame.WorkFrame.TransferFrame;

import common.TransferInfo;
import common.DateTimeThread;
import Database.accountDao;
import frame.Element.myButton;
import frame.CommonFrame.ImageFrame;
import frame.WorkFrame.MainInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TransferCardInput extends ImageFrame {
    JTextField transferCardNumber;
    myButton confirmBtn,deleteBtn,backupBtn;
    public TransferCardInput(){
        addComponents();
        addListener();
        setVisible(true);
    }
    public void addComponents(){

        JLabel drawhint=new JLabel("请输入转入卡号，并点击【确认】");
        drawhint.setBounds(200, 200, 450, 40);
        drawhint.setFont(new Font("华文琥珀", 1, 28));
        add(drawhint);

        transferCardNumber = new JTextField();
        transferCardNumber.setBounds(200, 250, 350, 40);
        transferCardNumber.setFont(new Font("Arial", 1, 28));
        add(transferCardNumber);

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
                String  cardNumber = transferCardNumber.getText();
                TransferInfo transferAccount = new accountDao().queryAccount(cardNumber);
                if(transferAccount == null){
                    JOptionPane.showMessageDialog(getContentPane(),"输入账号不存在" );
                    transferCardNumber.setText("");
                    transferCardNumber.grabFocus();
                }else if(transferAccount.getStatus()==1){
                    JOptionPane.showMessageDialog(getContentPane(),"输入账号已被锁定，无法交易" );
                    transferCardNumber.setText("");
                    transferCardNumber.grabFocus();
                }
                else {
                    dispose();
                    new TransferMoneyInput(transferAccount);
                }

            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNumber =transferCardNumber.getText();
                if(cardNumber==null || cardNumber.isEmpty())
                    return;
                else if(cardNumber.length()==1)
                    transferCardNumber.setText("");
                else{
                    transferCardNumber.setText(cardNumber.substring(0,cardNumber.length()-1));
                }
                transferCardNumber.grabFocus();
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
        transferCardNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int code = e.getKeyChar();
                if( code<48 || code > 57 || transferCardNumber.getText().length()==8)//!(code>=KeyEvent.VK_0&&code<=KeyEvent.VK_9) ||
                {
                    e.consume();
                }
            }
        });
    }

}
