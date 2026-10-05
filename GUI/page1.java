package GUI;
import System.Transaction;
import System.TransactionType;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class page1 extends JPanel implements ActionListener,KeyListener{
    private int money = 0;

    String font = "Leelawadee UI";
    RoundedBox box1; //กล่องใหญ่
    RoundedBox box2; //กล่องซ้าย
    RoundedBox box3; //กล่องขวา

    RoundedToggleButton button1; 
    RoundedToggleButton button2;

    JTextField tf1;
    JTextField tf2;
    JTextField tf3;

    RoundedButton roB1;

    Transaction List;
    TransactionType Type;

    public page1() {
        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, 1440, 960);
        setComponent1();
        setComponent2();
    }

    public void setComponent1(){
        JLabel Head = new JLabel("บันทึกรายรับ-รายจ่าย");
        Head.setFont(new Font(font, Font.BOLD, 64));
        Head.setForeground(Color.white);
        Head.setBounds(100, 45,600,100);
        add(Head);


        box1 = new RoundedBox(30);
        box1.setBackground(setColor.BoxColor());
        box1.setLayout(null);
        box1.setBounds(50, 172, 1200, 727);
        add(box1);
        
        box2 = new RoundedBox(50);
        box2.setBackground(setColor.BackgroundColor());
        box2.setLayout(null);
        box2.setBounds(40, 180, 650, 525);
        box1.add(box2);

        box3 = new RoundedBox(50);
        box3.setBackground(setColor.BackgroundColor());
        box3.setLayout(null);
        box3.setBounds(700, 50, 450, 655);
        box1.add(box3);

        JLabel Money_left_text = new JLabel("ยอดเงินคงเหลือ");
        Money_left_text.setFont(new Font(font , Font.BOLD, 32));
        Money_left_text.setForeground(Color.white);
        Money_left_text.setBounds(40, 10,600,50);
        box1.add(Money_left_text);

        JLabel Money_left = new JLabel("฿"+money);
        Money_left.setFont(new Font(font , Font.BOLD, 65));
        Money_left.setForeground(Color.white);
        Money_left.setBounds(40, 70,600,65);
        box1.add(Money_left);

    }

    public void setComponent2(){
        button1 = new RoundedToggleButton(75);
        button1.setFont(new Font(font, Font.BOLD, 32));
        button1.setBackground(setColor.Green());
        button1.setText("รายรับ");
        button1.setForeground(Color.BLACK);
        button1.setBounds(35, 40, 275, 75);
        box2.add(button1);
        
        button2 = new RoundedToggleButton(75);
        button2.setFont(new Font(font, Font.BOLD, 32));
        button2.setBackground(new Color(119,49,49));
        button2.setText("รายจ่าย");
        button2.setForeground(Color.BLACK);
        button2.setBounds(340, 40, 275, 75);
        box2.add(button2);
        
        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        
        JLabel l2 = new JLabel("ชื่อรายการ");
        l2.setBounds(50, 120, 500, 50);
        l2.setFont(new Font(font, Font.BOLD, 36));
        l2.setForeground(Color.WHITE);
        box2.add(l2);

        tf1 = new JTextField();
        tf1.setBounds(50, 180, 550, 50);
        tf1.setFont(new Font(font, Font.PLAIN, 24));
        box2.add(tf1);

        JLabel l3 = new JLabel("หมวดหมู่");
        l3.setBounds(50, 240, 500, 50);
        l3.setFont(new Font(font, Font.BOLD, 36));
        l3.setForeground(Color.WHITE);
        box2.add(l3);

        JComboBox cbb = new JComboBox<>();
        cbb.addItem("ComboBox1");
        cbb.addItem("ComboBox2");
        cbb.addItem("");
        cbb.setBounds(50, 300, 250, 50);
        cbb.setFont(new Font(font, Font.PLAIN, 24));
        box2.add(cbb);

        JLabel l4 = new JLabel("วันที่");
        l4.setBounds(350, 240, 500, 50);
        l4.setFont(new Font(font, Font.BOLD, 36));
        l4.setForeground(Color.WHITE);
        box2.add(l4);

        tf2 = new JTextField("dd/mm/yyyy");
        tf2.setBounds(350, 300, 250, 50);
        tf2.setFont(new Font(font, Font.PLAIN, 24));
        box2.add(tf2);

        JLabel l5 = new JLabel("จำนวนเงิน");
        l5.setBounds(50, 360, 500, 50);
        l5.setFont(new Font(font, Font.BOLD, 36));
        l5.setForeground(Color.WHITE);
        box2.add(l5);

        tf3 = new JTextField();
        tf3.setBounds(50, 420, 250, 50);
        tf3.setFont(new Font(font, Font.PLAIN, 36));
        box2.add(tf3);

        roB1 = new RoundedButton(50);
        roB1.setBounds(365, 400, 220,90);
        roB1.setBackground(setColor.Green());
        roB1.setText("บันทึก");
        roB1.setFont(new Font(font, Font.BOLD, 36));
        roB1.setForeground(Color.BLACK);
        box2.add(roB1);


        button1.addActionListener(this);
        button2.addActionListener(this);
        roB1.addActionListener(this);

        tf3.addKeyListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            button1.setBackground(setColor.Green());
            button2.setBackground(new Color(119,49,49));
        }
        if(e.getSource() == button2){
            button2.setBackground(setColor.Red());
            button1.setBackground(new Color(0,132,86));
        }
        if(e.getSource() == roB1){
            // List = new Transaction(tf1.getText(),null,null,null,null,null,null);
        }


    }

    public void keyTyped(KeyEvent e) {
        if(e.getSource() == tf3){
            if(e.getKeyChar() < '0' || e.getKeyChar() > '9')
                e.consume();
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}

