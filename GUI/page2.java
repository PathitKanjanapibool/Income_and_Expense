package GUI;
import java.awt.*;
import javax.swing.*;

public class page2 extends JPanel{
    private int money = 0;

    String font = "Leelawadee UI";
    RoundedBox box1; //กล่องใหญ่
    RoundedBox box2; //กล่องซ้าย
    RoundedBox box3; //กล่องขวา

    RoundedToggleButton button1; 
    RoundedToggleButton button2;

    JTextField inputCate;
    RoundedButton saveCate;
    JTextArea displayCategory;

    public page2(){
        this.setLayout(null);
        this.setOpaque(false);
        setComponent1();
    }
    public void setComponent1(){
        JLabel Head = new JLabel("หมวดหมู่");
        Head.setFont(new Font(font, Font.BOLD, 64));
        Head.setForeground(Color.white);
        Head.setBounds(70, 45,600,100);
        add(Head);

        inputCate = new JTextField("");
        inputCate.setBounds(323, 200, 645, 70);
        inputCate.setFont(new Font(font, Font.PLAIN, 36));
        add(inputCate);

        saveCate = new RoundedButton(30);
        saveCate.setText("เพิ่มหมวดหมู่");
        saveCate.setFont(new Font(font, Font.BOLD, 36));
        saveCate.setBackground(Color.GREEN);
        saveCate.setForeground(Color.BLACK);
        saveCate.setBounds(323, 300, 645, 70);
        add(saveCate);

        displayCategory = new JTextArea();
        displayCategory.setEditable(false);

        JScrollPane scDisplayCategory = new JScrollPane(displayCategory);
        scDisplayCategory.setBounds(162, 400, 978,450);
        add(scDisplayCategory);

    }
}

