package GUI;

import java.awt.*;
import javax.swing.*;
import GUIUtils.*;

public class App extends JFrame {
    
    public String font = "Leelawadee UI";
    public static final int width = 1440; 
    public static final int height = 960;
    int menu_width = Menu.width;
    Container cp;

    CardLayout cardLayout;
    JPanel contentArea;  
    setColor setColor;

    // รับ userFile เข้ามาจากหน้า Login
    public App(String userFile) {
        Initial();
        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBounds(menu_width, 0, width - menu_width, height);
        contentArea.setOpaque(false);

        // ส่ง userFile ให้กับ page1
        contentArea.add(new page1(userFile), "HOME");
        contentArea.add(new page2(), "CATEGORY"); // ถ้า page2/page3 ต้องใช้ ก็ส่ง userFile เข้าไปแบบเดียวกันได้ครับ
        contentArea.add(new page3(), "LIST");

        cp.add(new Menu(cardLayout, contentArea));
        cp.add(contentArea);
        Finally();
    }

    public void Initial(){
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(setColor.BackgroundColor());
    }

    public void Finally(){
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }
}