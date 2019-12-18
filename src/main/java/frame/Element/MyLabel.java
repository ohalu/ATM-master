package frame.Element;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    public MyLabel(String text){
        super(text);
        setFont(new Font("华文琥珀", 1, 28));
    }
    public void setBounds(int x,int y){
        super.setBounds(x,y,150,40);
    }
    public void changeSize(int size){
        setFont(new Font("华文琥珀", 1, size));
    }
}
