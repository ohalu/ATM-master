package frame.WorkFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import common.AccountInfo;
import common.DateTimeThread;
import frame.Element.MyLabel;
import frame.Element.myButton;
import frame.CommonFrame.ImageFrame;
import frame.FrontFrame;
import frame.WorkFrame.TransferFrame.TransferCardInput;

/**
 * ��¼�����������
 */
public final class MainInterface extends ImageFrame {
    /*
     * ��Ӱ�ť����ȡ�ֽ𣬴�ת��ҵ�񣬲�ѯ���޸����룬�˳�����
     */
    private JButton draw, save, transfer, inquiry, modifyPwd, exit;
    String[] info;

    public MainInterface() {
        addComponents();
        addListener();
        setVisible(true);
    }

    public void addComponents() {

        JLabel describeL = new MyLabel("��ѡ������Ҫ�ķ���");
        describeL.setBounds(280, 150, 300, 60);
        add(describeL);

        date.setBounds(600, 0, 200, 20);

        draw = new myButton("ȡ ��");
        save = new myButton("�� ��");
        transfer = new myButton("ת ��");
        inquiry = new myButton("�� ѯ");
        modifyPwd = new myButton("�޸�����");
        exit = new myButton("�� ��");

        draw.setBounds(10, 250, 200, 60);
        save.setBounds(10, 350, 200, 60);
        transfer.setBounds(10, 450, 200, 60);
        inquiry.setBounds(580, 250, 200, 60);
        modifyPwd.setBounds(580, 350, 200, 60);
        exit.setBounds(580, 450, 200, 60);

        add(draw);
        add(save);
        add(transfer);
        add(inquiry);
        add(modifyPwd);
        add(exit);
    }

    public void addListener() {
        draw.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DateTimeThread(new WithdrawMoney());
            }
        });
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DateTimeThread(new SaveMoney());
            }
        });
        transfer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DateTimeThread(new TransferCardInput());
            }
        });
        modifyPwd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DateTimeThread(new ModifyPassword());
            }
        });

        inquiry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DateTimeThread(new QueryBalance());
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new FrontFrame();
            }
        });
    }

}
