package frame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import common.AccountInfo;
import common.DateTimeThread;
import Database.accountDao;
import frame.CommonFrame.ImageFrame;
import frame.Element.MyLabel;
import frame.Element.myButton;

import frame.WorkFrame.MainInterface;

/**
 * ������֤ҳ��
* */
public class LoginFrame extends ImageFrame {
	/*
	 * ���������
	 * ���������
	 * ��¼����������
	 */
	JPasswordField passwordField;
	JButton confirmBtn,cancelBtn,deleteBtn;    //ȷ�ϼ���ȡ��������λ��
	byte count = 1;   //�����������δ�������

	public LoginFrame()  {
        addComponents();
        addListener();

	}

	/**
     * ����������
     * */

	public void addComponents() {

        MyLabel passwordL=new MyLabel("���������룺");
        passwordL.setBounds(150, 200, 240, 40);
        add(passwordL);

        passwordField=new JPasswordField();
        passwordField.setBounds(350, 200, 220, 40);
        passwordField.setFont(new Font("Arial", 1, 24));
        add(passwordField);

        confirmBtn=new myButton("ȷ ��");
        confirmBtn.setBounds(600, 400, 120, 60);
        add(confirmBtn);

        deleteBtn = new myButton("�� ɾ");
        deleteBtn.setBounds(350, 400, 120, 60);
        add(deleteBtn);

        cancelBtn = new myButton("�� ��");
        cancelBtn.setBounds(100, 400, 120, 60);
        add(cancelBtn);

		setVisible(true);
	}
	/**
	 * ��Ӽ�����
	 */

	public void addListener() {

	    /**
         * �����⣺��1������Ϊ���֣���2�����Ȳ�����6
         * */
	    passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int code = e.getKeyChar();
                if( code<48 || code > 57 || passwordField.getPassword().length==6)//!(code>=KeyEvent.VK_0&&code<=KeyEvent.VK_9) ||
                {
                    e.consume();
                }
            }
        });

		/**
		 * ��3�ε�¼ʧ�ܣ���������Ƭ
		 */
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			    String password = new String(passwordField.getPassword());
                if(password==null || password.length()<6){
                    JOptionPane.showMessageDialog(getContentPane(),"�������벻����������������");
                    return;
                }
                //��֤�����Ƿ���ȷ
				boolean right = new accountDao().checkPwd(password);
				if(right){
						dispose();
						AccountInfo.setPassword(password);
						new DateTimeThread(new MainInterface());

				}else{
				    if(count > 3){
				        dispose();
                        JOptionPane.showMessageDialog(getContentPane(),"ʧ�ܳ������Σ���Ƭ���������뵽Ӫҵ������");
                        new accountDao().lockCard();
                        new DateTimeThread(new FrontFrame());
                    }else{
                        JOptionPane.showMessageDialog(getContentPane(),"�����������\r\n�ѳ���"+count+"�Ρ�ʧ�ܳ������Σ���Ƭ������");
                        passwordField.setText("");
                        passwordField.grabFocus();
				    }
				    count ++;
                }

			}
		});

		//��ɾ
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String password =new String(passwordField.getPassword());
				if(password==null || password.isEmpty())
				    return;
				else if(password.length()==1)
				    passwordField.setText("");
				else{
				    passwordField.setText(password.substring(0,password.length()-1));
                }
                passwordField.grabFocus();
			}
		});
		
		/**
		 * ȡ���ص���ҳ
		 */
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    dispose();
				new FrontFrame();
			}
		});

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
	}

}
