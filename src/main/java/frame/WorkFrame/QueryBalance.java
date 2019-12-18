package frame.WorkFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import common.DateTimeThread;
import Database.accountDao;
import frame.Element.myButton;
import frame.CommonFrame.ImageFrame;

/**
 * ��ѯ���������
 * @author overlord
 *
 */
public final class QueryBalance extends ImageFrame {
	JButton backup;
	public QueryBalance() {
        addComponents();
        addListener();
        setVisible(true);
    }
	public void addComponents(){

		Font f=new Font("��������", 0, 36);

		double balance = new accountDao().queryBalance();
        JLabel currenB=new JLabel("�˻���ǰ���Ϊ��"+balance+" Ԫ");
		currenB.setBounds(150, 200, 600, 40);
		currenB.setFont(f);
		add(currenB);

		backup=new myButton("�� ��");
        backup.setBounds(600, 400, 120, 60);
		add(backup);

        date.setBounds(600, 0, 200, 20);
	}

	public void addListener(){
		backup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DateTimeThread(new MainInterface());
			}
		});
	}
}
