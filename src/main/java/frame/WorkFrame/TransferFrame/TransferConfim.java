package frame.WorkFrame.TransferFrame;

import common.TransferInfo;
import common.DateTimeThread;
import Database.accountDao;
import frame.Element.myButton;
import frame.CommonFrame.ImageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferConfim  extends ImageFrame {

    myButton confirmBtn,backupBtn;
    TransferInfo info;
    public TransferConfim(TransferInfo info){
        this.info = info;
        addComponents();
        addListener();
        setVisible(true);
    }

    public void addComponents(){

        JLabel describe=new JLabel("请确认账户信息是否正确：");
        describe.setBounds(180, 160, 400, 40);
        describe.setFont(new Font("华文琥珀", 1, 28));
        add(describe);

        JLabel cardHint=new JLabel("卡   号：");
        JLabel nameHint=new JLabel("户   名：");
        JLabel moneyHint=new JLabel("转入金额：");
        cardHint.setBounds(150, 250, 150, 40);
        nameHint.setBounds(150, 300, 150, 40);
        moneyHint.setBounds(150, 350, 150, 40);
        cardHint.setFont(new Font("华文琥珀", 1, 28));
        nameHint.setFont(new Font("华文琥珀", 1, 28));
        moneyHint.setFont(new Font("华文琥珀", 1, 28));

        JTextField  cardFiled = new JTextField();
        JLabel  nameFiled = new JLabel();
        JLabel  moneyFiled = new JLabel();
        cardFiled.setBounds(320, 250, 300, 40);
        nameFiled.setBounds(320, 300, 300, 40);
        moneyFiled.setBounds(320, 350, 300, 40);
        cardFiled.setFont(new Font("华文琥珀", 1, 28));
        nameFiled.setFont(new Font("华文琥珀", 1, 28));
        moneyFiled.setFont(new Font("华文琥珀", 1, 28));
        cardFiled.setText(info.getCardNumber());
        nameFiled.setText(info.getName());
        moneyFiled.setText(String.valueOf(info.getMoney()));
        cardFiled.setEnabled(false);
        nameFiled.setEnabled(false);
        moneyFiled.setEnabled(false);

        add(cardHint);
        add(nameHint);
        add(moneyHint);
        add(cardFiled);
        add(nameFiled);
        add(moneyFiled);

        //两个button
        confirmBtn = new myButton("确 认");
        backupBtn = new myButton("返 回");

        confirmBtn.setBounds(650, 440, 120, 60);
        backupBtn.setBounds(50, 440, 120, 60);

        add(backupBtn);
        add(confirmBtn);
    }
    /**
     * 提交按钮的监听器
     */
    public void addListener(){

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double now_money = new accountDao().transferMoney(info);
                if(now_money == -1){
                    JOptionPane.showMessageDialog(getContentPane(),"输入金额不合法" );
                    dispose();
                    new TransferMoneyInput(info);
                }else if(now_money == -2)
                {
                    JOptionPane.showMessageDialog(getContentPane(),"当前账户余额不足" );
                    dispose();
                    new TransferMoneyInput(info);
                }else{
                    JOptionPane.showMessageDialog(getContentPane(), "您的请求已经被受理。当前账户余额为: "+now_money+"元");
                    new TransferCardInput();
                }

            }
        });
        

        backupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TransferMoneyInput(info);
            }
        });


    }


}
