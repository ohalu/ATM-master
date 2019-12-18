package frame.CommonFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 设置背景图片
* */
public class ImageFrame extends JFrame{

    final static Dimension frame_size = new Dimension(800,600);
    //显示时间的标签
    public JLabel date;

    class ImagePanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            URL resource = getClass().getClassLoader().getResource("back.png");
            ImageIcon image = new ImageIcon(resource);
            image.paintIcon(this,g,0,0);
        }
    }

    public ImageFrame(){
        setTitle("模拟ATM");
        setSize(frame_size);

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setUndecorated(true);
        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setLayout(null);
        setContentPane(imagePanel);
        setLocationRelativeTo(null); //居中显示
        setResizable(false);
//        setVisible(true);

        //添加时间栏
        date = new JLabel("");
        date.setForeground(SystemColor.WHITE);
        add(date);
    }
    /**
     * 设置时间ate
     */
    public void setDate(String s) {
        date.setText(s);
    }
}
