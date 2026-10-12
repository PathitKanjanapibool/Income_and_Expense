package GUI;

import java.awt.*;
import javax.swing.*;

public class logsign extends JFrame {
    String font = "Leelawadee UI";
    Container cp;
    int width = 1280; 
    int height = 720;

    CardLayout cardLayout2;
    JPanel contentArea2; 

    public logsign(){
        Initial();
        cardLayout2 = new CardLayout();
        contentArea2 = new JPanel(cardLayout2);

        contentArea2.setBounds(0, 0, width, height);
        contentArea2.setOpaque(false);
        
        // ส่ง cardLayout2 และ contentArea2 เข้าไปให้ทั้งสองหน้า
        contentArea2.add(new loginP(cardLayout2, contentArea2), "loginPage");
        contentArea2.add(new signP(cardLayout2, contentArea2), "signPage");
        
        // กำหนดให้หน้าแรกเป็น loginPage
        cardLayout2.show(contentArea2, "loginPage");
        cp.add(contentArea2);

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

    public static void main(String[] args) {
        logsign lg = new logsign();
    }
}