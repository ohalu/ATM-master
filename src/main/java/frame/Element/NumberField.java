package frame.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberField extends JTextField {
    //输入位数
    public NumberField(final int maxLength){
        setFont(new Font("Arial", 1, 24));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int code = e.getKeyChar();
                if( code<48 || code > 57 || getText().length()==maxLength)
                {
                    e.consume();
                }
            }
        });
    }
}
