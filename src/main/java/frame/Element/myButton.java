package frame.Element;

import javax.swing.*;
import java.awt.*;

public class myButton extends JButton {
    public myButton(String text){
        super(text);
        setFont(new Font("华文琥珀", 1, 24));
        setContentAreaFilled(false);//透明
        setBorder(BorderFactory.createRaisedBevelBorder());//凸起
    }

}
