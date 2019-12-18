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
 * 密码验证页面
* */
public class LoginFrame extends ImageFrame {
	/*
	 * 卡号输入框
	 * 密码输入框
	 * 登录次数计数器
	 */
	JPasswordField passwordField;
	JButton confirmBtn,cancelBtn,deleteBtn;    //确认键，取消键，退位键
	byte count = 1;   //计数器，三次错误锁卡

	public LoginFrame()  {
        addComponents();
        addListener();

	}

	/**
     * 添加其他组件
     * */

	public void addComponents() {

        MyLabel passwordL=new MyLabel("请输入密码：");
        passwordL.setBounds(150, 200, 240, 40);
        add(passwordL);

        passwordField=new JPasswordField();
        passwordField.setBounds(350, 200, 220, 40);
        passwordField.setFont(new Font("Arial", 1, 24));
        add(passwordField);

        confirmBtn=new myButton("确 认");
        confirmBtn.setBounds(600, 400, 120, 60);
        add(confirmBtn);

        deleteBtn = new myButton("回 删");
        deleteBtn.setBounds(350, 400, 120, 60);
        add(deleteBtn);

        cancelBtn = new myButton("退 出");
        cancelBtn.setBounds(100, 400, 120, 60);
        add(cancelBtn);

		setVisible(true);
	}
	/**
	 * 添加监听器
	 */

	public void addListener() {

	    /**
         * 输入检测：（1）必须为数字，（2）长度不超过6
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
		 * 若3次登录失败，则锁定卡片
		 */
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			    String password = new String(passwordField.getPassword());
                if(password==null || password.length()<6){
                    JOptionPane.showMessageDialog(getContentPane(),"密码输入不完整，请重新输入");
                    return;
                }
                //验证密码是否正确
				boolean right = new accountDao().checkPwd(password);
				if(right){
						dispose();
						AccountInfo.setPassword(password);
						new DateTimeThread(new MainInterface());

				}else{
				    if(count > 3){
				        dispose();
                        JOptionPane.showMessageDialog(getContentPane(),"失败超过三次，卡片已锁定，请到营业厅解锁");
                        new accountDao().lockCard();
                        new DateTimeThread(new FrontFrame());
                    }else{
                        JOptionPane.showMessageDialog(getContentPane(),"密码输入错误！\r\n已尝试"+count+"次。失败超过三次，卡片将锁定");
                        passwordField.setText("");
                        passwordField.grabFocus();
				    }
				    count ++;
                }

			}
		});

		//回删
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
		 * 取消回到首页
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
