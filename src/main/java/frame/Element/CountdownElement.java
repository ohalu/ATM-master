package frame.Element;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 倒计时器/读秒
 */
public class  CountdownElement extends JFrame{
	//显示倒计时的标签
	public JLabel date;
	
	/**
	 * 添加一个时间标签
	 */
	public void create(){

		date=new JLabel("");
//		add(new JLabel("倒计时： "));
		add(date);
//		add(new JLabel(" 秒"));
	}

	/**
	 * 设置秒数
	 */
	public void setSecond(String s) {
		date.setText(s);
	}
}
