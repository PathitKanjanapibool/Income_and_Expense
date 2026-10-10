package GUI;
import java.awt.*;
import java.time.LocalDate;

import javax.swing.*;

import Income_and_Expense_main.TransactionType;

public class page3 extends JPanel{
    private String font = "Leelawadee UI";
    int a_height = App.height;
    int a_width = App.width;

    int m_height = Menu.height;
    int m_width = Menu.width;

    RoundedToggleButton button1; 
    RoundedToggleButton button2;

    public page3(){
        this.setLayout(null);
        this.setOpaque(false);
        Filter();
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

    public void Filter(){
        JPanel filter = new JPanel();
        filter.setBounds((750/2)-m_width, 150, 750, 50);
        filter.setBackground(setColor.BackgroundColor());
        filter.setFocusable(false);
        filter.setLayout(new BoxLayout(filter,BoxLayout.X_AXIS));

        JComboBox<String> typeBox = new JComboBox<>();
        typeBox.setFont(new Font(font, Font.BOLD, 16));
        typeBox.setBackground(setColor.BoxColor2());
        typeBox.setForeground(Color.white);
        typeBox.setPreferredSize(new Dimension(100,50));
        typeBox.addItem("ทั้งหมด");
        typeBox.addItem("รายรับ");
        typeBox.addItem("รายจ่าย");
        typeBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"ทุกหมวดหมู่"});
        categoryBox.setFont(new Font(font, Font.BOLD, 16));
        categoryBox.setBackground(setColor.BoxColor2());
        categoryBox.setForeground(Color.white);
        categoryBox.setPreferredSize(new Dimension(100,50));

        JComboBox<String> monthBox = new JComboBox<>(new String[]{"ทุกเดือน"});
        monthBox.setFont(new Font(font, Font.BOLD, 16));
        monthBox.setBackground(setColor.BoxColor2());
        monthBox.setForeground(Color.white);
        monthBox.setPreferredSize(new Dimension(100,50));

        JButton resetButton = new JButton("ล้างตัวกรอง");
        resetButton.setFont(new Font(font, Font.BOLD, 16));
        resetButton.setBackground(setColor.BoxColor2());
        resetButton.setForeground(Color.white);
        resetButton.setPreferredSize(new Dimension(100,50));


        filter.add(typeBox);
        filter.add(Box.createHorizontalStrut(15));
        filter.add(categoryBox);
        filter.add(Box.createHorizontalStrut(15));
        filter.add(monthBox);
        filter.add(Box.createHorizontalStrut(15));
        filter.add(resetButton);
        add(filter);
    }

    public void TableList(){
        int table_height = 550;
        int table_width = 1050;

        table Table = new table();
        Table.setBackground(setColor.BackgroundColor());
        Table.addRow("นํ้าปั่น",LocalDate.now(),TransactionType.OUTCOME,"เครื่องดื่ม",100);
        Table.addRow("ข้าวมันไก่",LocalDate.now(),TransactionType.OUTCOME,"อาหาร",100);
        Table.addRow("ขนม",LocalDate.now(),TransactionType.OUTCOME,"อาหาร",50);
        Table.addRow("-",LocalDate.now(),TransactionType.INCOME,"เงินเดือน",15000);
        Table.setBackground(setColor.BackgroundColor());
        Table.setBounds((a_width-m_width)/2-(table_width/2), 250, table_width, table_height);
        add(Table);
        System.out.println(getWidth());
    }
}
