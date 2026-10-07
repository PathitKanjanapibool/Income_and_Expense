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
