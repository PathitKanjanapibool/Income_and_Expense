package GUI;
import java.awt.*;
import javax.swing.*;

public class page2 extends JPanel{
    private int money = 0;

    String font = "Leelawadee UI";

    table list = new table();

    RoundedToggleButton button1; 
    RoundedToggleButton button2;

    public page2(){
        this.setLayout(null);
        this.setOpaque(false);
        setComponent1();
    }
    public void setComponent1(){
        JLabel Head = new JLabel("หมวดหมู่    ");
        Head.setFont(new Font(font, Font.BOLD, 64));
        Head.setForeground(Color.white);
        Head.setBounds(100, 45,600,100);
        add(Head);
}
}

