package GUI;

import java.awt.*;
import javax.swing.*;

public class App extends JFrame{
    
    String font = "Leelawadee UI";
    int width = 1440; 
    int height = 960;
    Container cp;

    CardLayout cardLayout;
    JPanel contentArea;  

    RoundedToggleButton button1; RoundedToggleButton button2;
    public App() {
        Initial();
        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBounds(150, 0, 1290, 960);
        contentArea.setOpaque(false);
        contentArea.add(new page1(), "HOME");
        contentArea.add(new page2(), "CATEGORY");
        // contentArea.add(new page3(), "LIST");

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

