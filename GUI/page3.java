package GUI;
import java.awt.*;
import javax.swing.*;

public class page3 extends JPanel{
    String font = "Leelawadee UI";
    RoundedBox box1; //กล่องใหญ่
    RoundedBox box2; //กล่องซ้าย
    RoundedBox box3; //กล่องขวา

    RoundedToggleButton button1; 
    RoundedToggleButton button2;

    public page3(){
        this.setLayout(null);
        this.setOpaque(false);
        setComponent1();
    }
    public void setComponent1(){
        JLabel Head = new JLabel("รายการ");
        Head.setFont(new Font(font, Font.BOLD, 64));
        Head.setForeground(Color.white);
        Head.setBounds(100, 45,600,100);
        add(Head);
}
}
