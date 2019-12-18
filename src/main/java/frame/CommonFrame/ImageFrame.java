package frame.CommonFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * ���ñ���ͼƬ
* */
public class ImageFrame extends JFrame{

    final static Dimension frame_size = new Dimension(800,600);
    //��ʾʱ��ı�ǩ
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
        setTitle("ģ��ATM");
        setSize(frame_size);

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setUndecorated(true);
        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setLayout(null);
        setContentPane(imagePanel);
        setLocationRelativeTo(null); //������ʾ
        setResizable(false);
//        setVisible(true);

        //���ʱ����
        date = new JLabel("");
        date.setForeground(SystemColor.WHITE);
        add(date);
    }
    /**
     * ����ʱ��ate
     */
    public void setDate(String s) {
        date.setText(s);
    }
}
