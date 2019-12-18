package frame.WorkFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import common.AccountInfo;
import Database.accountDao;

import common.DateTimeThread;
import frame.Element.MyLabel;
import frame.Element.PasswordField;
import frame.Element.myButton;
import frame.CommonFrame.ImageFrame;
import frame.FrontFrame;
import frame.LoginFrame;

/**
 * �޸��������
 *
 * @author overlord
 */
public final class ModifyPassword extends ImageFrame {
    /*
     * �������������
     * �������ѱ�ǩ
     */
    JPasswordField old, new1, new2;
    JLabel alert1, alert2;
    myButton confirmBtn, backupBtn;

    public ModifyPassword() {
        addComponents();
        addListener();
        setVisible(true);
    }

    public void addComponents() {

        MyLabel hint1 = new MyLabel("������ԭ���룺");
        MyLabel hint2 = new MyLabel("�����������룺");
        MyLabel hint3 = new MyLabel("���ٴ����������룺");
        hint1.setBounds(80, 180, 300, 40);
        hint2.setBounds(80, 240, 300, 40);
        hint3.setBounds(80, 300, 300, 40);
        add(hint1);
        add(hint2);
        add(hint3);

        alert1 = new JLabel();
        alert1.setForeground(Color.red);
        alert1.setBounds(630, 180, 70, 40);
        add(alert1);

        alert2 = new JLabel();
        alert2.setForeground(Color.red);
        alert2.setBounds(630, 300, 100, 40);
        add(alert2);

        old = new PasswordField();
        new1 = new PasswordField();
        new2 = new PasswordField();
        old.setBounds(410, 180, 210, 40);
        new1.setBounds(410, 240, 210, 40);
        new2.setBounds(410, 300, 210, 40);
        add(old);
        add(new1);
        add(new2);

        confirmBtn = new myButton("ȷ ��");
        confirmBtn.setBounds(650, 400, 120, 60);
        add(confirmBtn);

        backupBtn = new myButton("�� ��");
        backupBtn.setBounds(50, 400, 120, 60);
        add(backupBtn);


    }

    public void addListener() {
        old.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                String oldPW = new String(old.getPassword());
                if (oldPW == null || oldPW.length() == 0) {
                    return;
                }
                if (!oldPW.equals(AccountInfo.getPassword()))
                    alert1.setText("ԭ�������");
            }

            @Override
            public void focusGained(FocusEvent e) {
                alert1.setText("");
            }
        });

        new2.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                String new2PW = new String(new2.getPassword());
                if (new2PW == null || new2PW.length() == 0) {
                    return;
                }
                if (!new2PW.equals(new String(new1.getPassword())))
                    alert2.setText("�������벻һ��");
            }

            @Override
            public void focusGained(FocusEvent e) {
                alert2.setText("");
            }
        });

        confirmBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkPwd()) {
                    new accountDao().modifyPwd(new String(new2.getPassword()));
                    JOptionPane.showMessageDialog(getContentPane(), "�����޸ĳɹ�");
                    dispose();
                    new LoginFrame();
                } else {
                    JOptionPane.showMessageDialog(getContentPane(), "������������");
                    new1.setText("");
                    new2.setText("");
                    old.setText("");
                    old.grabFocus();
                }
            }
        });

        backupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DateTimeThread(new MainInterface());
            }
        });
    }

    public boolean checkPwd() {
        String pwd1 = new String(new1.getPassword());
        String pwd2 = new String(new2.getPassword());
        if (alert1.getText().length() != 0 || new1.getPassword().length != 6 || !pwd1.equals(pwd2)) {
            return false;
        }
        return true;
    }

}
