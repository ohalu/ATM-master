package frame.Element;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * ����ʱ��/����
 */
public class  CountdownElement extends JFrame{
	//��ʾ����ʱ�ı�ǩ
	public JLabel date;
	
	/**
	 * ���һ��ʱ���ǩ
	 */
	public void create(){

		date=new JLabel("");
//		add(new JLabel("����ʱ�� "));
		add(date);
//		add(new JLabel(" ��"));
	}

	/**
	 * ��������
	 */
	public void setSecond(String s) {
		date.setText(s);
	}
}
