package frame.WorkFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import common.DateTimeThread;
import Database.accountDao;
import frame.Element.myButton;
import frame.CommonFrame.ImageFrame;

/**
 * 提取现金功能的界面
 * @author overlord
 *
 */
public final class WithdrawMoney extends ImageFrame {

	JTextField drawMoney;
    myButton btn100,btn200,btn500,btn1000,btn2000,deleteBtn,confirmBtn,backupBtn;
	public WithdrawMoney(){
        addComponents();
        addListener();
        setVisible(true);
	}
	/**
	 * 创建取款界面
	 */
	public void addComponents(){

		JLabel drawhint=new JLabel("请输入取款金额：");
		drawhint.setBounds(280, 150, 300, 40);
        drawhint.setFont(new Font("华文琥珀", 1, 28));
		add(drawhint);

		drawMoney=new JTextField();
		drawMoney.setBounds(280, 200, 300, 40);
        drawMoney.setFont(new Font("Arial", 1, 28));
		add(drawMoney);

		//快捷输入
        btn100=new myButton("100");
        btn200=new myButton("200");
        btn500=new myButton("500");
        btn1000=new myButton("1000");
        btn2000=new myButton("2000");
        deleteBtn=new myButton("更 正");
        confirmBtn = new myButton("确 认");
        backupBtn = new myButton("返 回");

        btn100.setBounds(5, 120, 120, 60);
        btn200.setBounds(5, 220, 120, 60);
        btn500.setBounds(5, 320, 120, 60);
        btn1000.setBounds(5, 420, 120, 60);
        btn2000.setBounds(650, 120, 120, 60);
        deleteBtn.setBounds(650, 220, 120, 60);
        confirmBtn.setBounds(650, 320, 120, 60);
        backupBtn.setBounds(650, 420, 120, 60);

        add(btn100);
        add(btn200);
        add(btn500);
        add(btn1000);
        add(btn2000);
        add(backupBtn);
        add(deleteBtn);
        add(confirmBtn);

        date.setBounds(600, 0, 200, 20);
    }

	/**
	 * 提交按钮的监听器
	 */
	public void addListener(){

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String moneyL = drawMoney.getText();
                if(moneyL==null || moneyL.length()==0)
                    return;
                int money=Integer.valueOf(moneyL);
                String message = new String();
                double now_money = new accountDao().withdrawMoney(money);
                if(now_money == -1){
                    JOptionPane.showMessageDialog(getContentPane(),"输入金额不合法" );
                }else if(now_money == -2)
                {
                    JOptionPane.showMessageDialog(getContentPane(),"当前账户余额不足" );
                }else{
                    JOptionPane.showMessageDialog(getContentPane(), "请取走您的现金。当前账户余额为: "+now_money+"元");
                }

                drawMoney.setText("");
                drawMoney.grabFocus();
            }
        });

	    btn100.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawMoney.setText("100");
            }
        });
        btn200.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawMoney.setText("200");
            }
        });
        btn500.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawMoney.setText("500");
            }
        });
        btn1000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawMoney.setText("1000");
            }
        });
        btn2000.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawMoney.setText("2000");
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String money =drawMoney.getText();
                if(money==null || money.isEmpty())
                    return;
                else if(money.length()==1)
                    drawMoney.setText("");
                else{
                    drawMoney.setText(money.substring(0,money.length()-1));
                }
                drawMoney.grabFocus();
            }
        });
        backupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DateTimeThread(new MainInterface());
            }
        });


        /**
         * 输入检测：（1）必须为数字，（2）长度不超过10
         * */
        drawMoney.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int code = e.getKeyChar();
                if( code<48 || code > 57 || drawMoney.getText().length()==10)//!(code>=KeyEvent.VK_0&&code<=KeyEvent.VK_9) ||
                {
                    e.consume();
                }
            }
        });
	}

}
