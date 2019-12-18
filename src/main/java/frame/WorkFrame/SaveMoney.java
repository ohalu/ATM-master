package frame.WorkFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Database.accountDao;
import frame.Element.myButton;
import frame.CommonFrame.ImageFrame;


public final class SaveMoney extends ImageFrame {
	JTextField saveMoney;
    myButton confirmBtn,backupBtn;
	public SaveMoney() {
        addComponents();
        addListener();
        setVisible(true);
	}

	public void addComponents(){

        JLabel saveHint=new JLabel("请放入您的现金");
        saveHint.setBounds(280, 150, 300, 40);
        saveHint.setFont(new Font("华文琥珀", 1, 28));
        add(saveHint);

        saveMoney=new JTextField();
        saveMoney.setBounds(280, 200, 300, 40);
        saveMoney.setFont(new Font("Arial", 1, 28));
        add(saveMoney);

        confirmBtn = new myButton("确 认");
        confirmBtn.setBounds(650, 320, 120, 60);
        add(confirmBtn);

        backupBtn = new myButton("返 回");
        backupBtn.setBounds(650, 400, 120, 60);
        add(backupBtn);

	}


	public void addListener(){
	    //输入框检测
        /**
         * 输入检测：（1）必须为数字，（2）长度不超过10
         * */
        saveMoney.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int code = e.getKeyChar();
                if( code<48 || code > 57 || saveMoney.getText().length()==10)//!(code>=KeyEvent.VK_0&&code<=KeyEvent.VK_9) ||
                {
                    e.consume();
                }
            }
        });

        confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                int money=Integer.valueOf(saveMoney.getText());
                double now_money = new accountDao().saveMoney(money);
                if(now_money == -1){
                    JOptionPane.showMessageDialog(getContentPane(),"输入金额不合法" );
                }else{
                    JOptionPane.showMessageDialog(getContentPane(), "现金已存入账户。\r\n当前账户余额为: "+now_money+"元");
                }
                saveMoney.setText("");
                saveMoney.grabFocus();
			}
		});

        backupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainInterface();
            }
        });
	}


}
