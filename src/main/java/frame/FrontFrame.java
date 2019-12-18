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
 * ��ҳ���������뿨�š�
* */
public class FrontFrame extends ImageFrame {
    //���������
    NumberField cardNumberField;
    //ȷ�ϼ�
    myButton confirmBtn;

    public FrontFrame(){
        addComponents();
        addListener();
    }
    //����ҳ��
    public void addComponents(){

        MyLabel cardNumberL=new MyLabel("�����뿨�ţ�");
        cardNumberL.setBounds(150, 200, 240, 40);
        add(cardNumberL);

        cardNumberField=new NumberField(Constant.CARD);
        cardNumberField.setBounds(350, 200, 220, 40);
        add(cardNumberField);

        confirmBtn=new myButton("ȷ ��");
        confirmBtn.setBounds(600, 400, 120, 60);
        add(confirmBtn);

        setVisible(true);
    }
    //��Ӽ�����
    public void addListener(){
        confirmBtn.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        //�˺���֤
                        String cardNumber = cardNumberField.getText();
                        //����Ӧ
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
                            String message = status==-1?"���޴˺ţ�����������":"���˻��ѱ��������뵽Ӫҵ������";
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
