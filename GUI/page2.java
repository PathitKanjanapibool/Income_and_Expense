package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import GUIUtils.RoundedButton;
import GUIUtils.RoundedToggleButton;
import GUIUtils.setColor;
import GUIUtils.table;
import Income_and_Expense_main.category;

public class page2 extends JPanel implements ActionListener{
    private int money = 0;
    private final page1 p1;
    private final String catpath;
    category cat ;

    String font = "Leelawadee UI";

    RoundedToggleButton button1; 
    RoundedToggleButton button2;

    JTextField inputCate;
    RoundedButton saveCate;
    RoundedButton deleteCate;
    JTextArea displayCategory;

    public page2(page1 p1,String catpath){
        this.p1 = p1;
        this.catpath = catpath;
        cat = new category(catpath);
        this.setLayout(null);
        this.setOpaque(false);
        setComponent1();
    }
    private void setComponent1(){
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
        saveCate.setBackground(setColor.Green());
        saveCate.setForeground(Color.BLACK);
        saveCate.setBounds(323, 300, 322, 70);
        add(saveCate);

        deleteCate = new RoundedButton(30);
        deleteCate.setText("ลบหมวดหมู่");
        deleteCate.setFont(new Font(font, Font.BOLD, 36));
        deleteCate.setBackground(setColor.Red());
        deleteCate.setForeground(Color.BLACK);
        deleteCate.setBounds(645, 300, 322, 70);
        add(deleteCate);

        displayCategory = new JTextArea();
        displayCategory.setEditable(false);
        displayCategory.setFont(new Font(font, Font.PLAIN, 32));
        for(String c : cat.getCategory()){
            displayCategory.append(c);
            displayCategory.append("\n");
        }

        JScrollPane scDisplayCategory = new JScrollPane(displayCategory);
        scDisplayCategory.setBounds(162, 400, 978,450);
        add(scDisplayCategory);

        saveCate.addActionListener(this);
        deleteCate.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveCate){
            if (inputCate.getText().trim().isEmpty()) { return ;
            }
            cat.saveToCsv(inputCate.getText());
            displayCategory.setText("");
            for(String c : cat.getCategory()){
                displayCategory.append(c);
                displayCategory.append("\n");
            }
            p1.refreshCategory();
        }

        if(e.getSource() == deleteCate){
            if (inputCate.getText().isEmpty()) return;
            cat.deleteCategory(inputCate.getText());
            displayCategory.setText("");
            for(String c : cat.getCategory()){
                displayCategory.append(c);
                displayCategory.append("\n");
            }
            p1.refreshCategory();
        }
    }
}
