package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.Utilities;

import GUIUtils.*;

public class App extends JFrame{

    private final String filepath;
    
    public String font = "Leelawadee UI";
    public static final int width = 1440; 
    public static final int height = 960;
    int menu_width = Menu.width;
    Container cp;

    CardLayout cardLayout;
    JPanel contentArea;  
    setColor setColor;
    public App(String filepath) {

        this.filepath = filepath;

        Initial();
        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBounds(menu_width, 0, width-menu_width, height);
        contentArea.setOpaque(false);
        contentArea.add(new page1(filepath), "HOME");
        contentArea.add(new page2(), "CATEGORY");
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

