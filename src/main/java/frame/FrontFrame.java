package frame;

import common.AccountInfo;
import Database.accountDao;
import common.Constant;
import frame.CommonFrame.ImageFrame;
import frame.Element.MyLabel;
import frame.Element.NumberField;
import frame.Element.myButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 首页，【请输入卡号】
* */
public class FrontFrame extends ImageFrame {
    //卡号输入框
    NumberField cardNumberField;
    //确认键
    myButton confirmBtn;

    public FrontFrame(){
        addComponents();
        addListener();
    }
    //绘制页面
    public void addComponents(){

        MyLabel cardNumberL=new MyLabel("请输入卡号：");
        cardNumberL.setBounds(150, 200, 240, 40);
        add(cardNumberL);

        cardNumberField=new NumberField(Constant.CARD);
        cardNumberField.setBounds(350, 200, 220, 40);
        add(cardNumberField);

        confirmBtn=new myButton("确 认");
        confirmBtn.setBounds(600, 400, 120, 60);
        add(confirmBtn);

        setVisible(true);
    }
    //添加监听器
    public void addListener(){
        confirmBtn.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        //账号验证
                        String cardNumber = cardNumberField.getText();
                        //不响应
                        if(cardNumber==null || cardNumber.isEmpty())
                        {
                            return;
                        }
                        int status = new accountDao().cardIsExist(cardNumber);
                        if (status==0) {
                            dispose();
                            AccountInfo.setAccountInfo(cardNumber);
                            new LoginFrame();
                        }else{
                            String message = status==-1?"查无此号，请重新输入":"该账户已被锁定，请到营业厅解锁";
                            JOptionPane.showMessageDialog(getContentPane(),message);
                            cardNumberField.setText("");
                        }
                    }
                });


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }


}
