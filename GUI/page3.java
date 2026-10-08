package GUI;
import java.awt.*;
import java.time.LocalDate;

import javax.swing.*;

import System.TransactionType;

public class page3 extends JPanel{
    int height = getHeight();
    int width = getWidth();
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
        TableList();
    }
    public void setComponent1(){
        JLabel Head = new JLabel("รายการ");
        Head.setFont(new Font(font, Font.BOLD, 64));
        Head.setForeground(Color.white);
        Head.setBounds(100, 45,600,100);
        add(Head);
    }
    public void TableList(){
        Menu m = new Menu(null, null);

        JPanel filter = new JPanel();
        filter.setBounds((750/2)-m.getWidth(), 150, 750, 50);
        filter.setLayout(new BoxLayout(filter,BoxLayout.X_AXIS));

        JComboBox<String> typeBox = new JComboBox<>();
        typeBox.setFont(new Font(font, Font.BOLD, 16));
        typeBox.setPreferredSize(new Dimension(100,50));
        typeBox.addItem("ทั้งหมด");
        typeBox.addItem("รายรับ");
        typeBox.addItem("รายจ่าย");
        typeBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"ทุกหมวดหมู่"});
        categoryBox.setFont(new Font(font, Font.BOLD, 16));
        categoryBox.setPreferredSize(new Dimension(100,50));

        JComboBox<String> monthBox = new JComboBox<>(new String[]{"ทุกเดือน"});
        monthBox.setFont(new Font(font, Font.BOLD, 16));
        monthBox.setPreferredSize(new Dimension(100,50));

        JButton resetButton = new JButton("ล้างตัวกรอง");
        resetButton.setFont(new Font(font, Font.BOLD, 16));
        resetButton.setPreferredSize(new Dimension(100,50));


        filter.add(typeBox);
        filter.add(categoryBox);
        filter.add(monthBox);
        filter.add(resetButton);
        add(filter);
    
        int height = 655;
        int width = 750;
        table Table = new table();
        Table.addRow("นํ้าปั่น",LocalDate.now(),TransactionType.OUTCOME,"เครื่องดื่ม",100);
        Table.addRow("ข้าวมันไก่",LocalDate.now(),TransactionType.OUTCOME,"อาหาร",100);
        Table.addRow("ขนม",LocalDate.now(),TransactionType.OUTCOME,"อาหาร",50);
        Table.addRow("-",LocalDate.now(),TransactionType.INCOME,"เงินเดือน",15000);
        Table.setBackground(setColor.BackgroundColor());
        Table.setBounds((width/2)-m.getWidth(), 250, width, height);
        add(Table);
    }
}
