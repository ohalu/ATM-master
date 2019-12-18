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
 * 登录后操作主界面
 */
public final class MainInterface extends ImageFrame {
    /*
     * 添加按钮：提取现金，存款，转账业务，查询余额，修改密码，退出程序
     */
    private JButton draw, save, transfer, inquiry, modifyPwd, exit;
    String[] info;

    public MainInterface() {
        addComponents();
        addListener();
        setVisible(true);
    }

    public void addComponents() {

        JLabel describeL = new MyLabel("请选择您需要的服务");
        describeL.setBounds(280, 150, 300, 60);
        add(describeL);

        date.setBounds(600, 0, 200, 20);

        draw = new myButton("取 款");
        save = new myButton("存 款");
        transfer = new myButton("转 账");
        inquiry = new myButton("查 询");
        modifyPwd = new myButton("修改密码");
        exit = new myButton("退 出");

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
