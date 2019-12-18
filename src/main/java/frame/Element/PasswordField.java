package frame.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PasswordField extends JPasswordField {
    //输入位数
    public PasswordField(){
        setFont(new Font("Arial", 1, 24));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int code = e.getKeyChar();
                if( code<48 || code > 57 || getPassword().length==6)
                {
                    e.consume();
                }
            }
        });
    }
}
