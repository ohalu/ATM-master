package frame.CommonFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import common.DateTimeThread;
import frame.Element.CountdownElement;
import frame.WorkFrame.MainInterface;

/**
 * ͨ�ñ�׼���棬һ��UI�ӿڵ�ʵ����
 * ��Ҫ��д����Ŀ��
 * setTitle;setBounds
 * �Դ�������
 * @author overlord
 *
 */
//public class General_UI extends CountdownElement implements UI{
public class General_Frame extends CountdownElement {
	/*
	 * ��¼���û���Ϣ
	 */
	public String[] info;

	/**
	 * ����һ������Ĺ��췽��
	 * ִ�в�����
	 * ��ȡ��¼��Ϣ����Ϊinfo����
	 * ����һ������
	 * ��ӽ���Ԫ�صļ�����
	 * ��ӹرմ��ڰ�ť������
	 * @param info	��¼���˻���Ϣ
	 */
	public General_Frame(String[] info){
		create();
		setVisible(true);
		listen();
		closeWindow();
	}

	/**
	 * ����һ���������š��͡�����ʱ�䡱�Ľ���
	 * ��Ҫ�ڼ̳���������:
	 * setTitle,setBounds,date.setBounds
	 */
//	@Override
	public void create() {
		super.create();
		setRootPaneCheckingEnabled(true);
		setLayout(null);
		getContentPane().setBackground(Color.PINK);
		setResizable(false);

		JLabel account=new JLabel("���ţ�"+info[1]);
		account.setBounds(10, 0, 150, 20);
		add(account);
	}

	/**
	 * ����Ԫ�صļ�����
	 */
//	@Override
	public void listen() {

	}

	/**
	 * �رմ��ڰ�ť�˳�����
	 */
//	@Override
	public void closeWindow() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

	/**
	 * �رյ�ǰ���棬����һ��������
	 */
	public void openMainUI(){
		dispose();
		new DateTimeThread(new MainInterface());
	}

}
