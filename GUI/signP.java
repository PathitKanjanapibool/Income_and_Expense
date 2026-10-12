package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class signP extends JPanel{
    String font = "Leelawadee UI";
    JTextField uSernmaetfsign;
    JPasswordField passWordFsign;
    JPasswordField conFirmpassfield;
    RoundedButton loginButton2;
    RoundedButton SignButton2;
    
    CardLayout cardLayout;
    JPanel contentArea;

    public signP(CardLayout cardLayout, JPanel contentArea) {
        this.cardLayout = cardLayout;
        this.contentArea = contentArea;
        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, 1280, 720);
        setConponent();
        setComponent2();
    }

    public void setConponent(){
        JLabel Head = new JLabel("Income & Expense App");
        Head.setFont(new Font(font, Font.BOLD, 36));
        Head.setBounds(450,20, 400, 60);
        Head.setForeground(Color.WHITE);
        add(Head);

        JLabel info = new JLabel("Sign up");
        info.setFont(new Font(font, Font.BOLD, 36));
        info.setBounds(580,90, 400, 50);
        info.setForeground(Color.WHITE);
        add(info);

        JLabel userName = new JLabel("Username :");
        userName.setFont(new Font(font, Font.BOLD, 24));
        userName.setBounds(390,170, 150, 40);
        userName.setForeground(Color.WHITE);
        add(userName);

        JLabel passWord = new JLabel("Password :");
        passWord.setFont(new Font(font, Font.BOLD, 24));
        passWord.setBounds(390,270, 150, 40);
        passWord.setForeground(Color.WHITE);
        add(passWord);

        JLabel conFirmpass = new JLabel("Confirm Password :");
        conFirmpass.setFont(new Font(font, Font.BOLD, 24));
        conFirmpass.setBounds(390,370, 250, 40);
        conFirmpass.setForeground(Color.WHITE);
        add(conFirmpass);
        
        uSernmaetfsign = new JTextField();
        uSernmaetfsign.setBounds(390,220, 500, 40);
        uSernmaetfsign.setFont(new Font(font, Font.BOLD, 24));
        add(uSernmaetfsign);

        passWordFsign = new JPasswordField();
        passWordFsign.setBounds(390,320, 500, 40);
        passWordFsign.setFont(new Font(font, Font.BOLD, 24));
        add(passWordFsign);

        conFirmpassfield = new JPasswordField();
        conFirmpassfield.setBounds(390, 420, 500, 40);
        conFirmpassfield.setFont(new Font(font, Font.BOLD, 24));
        add(conFirmpassfield);

    }
    public void setComponent2(){
        SignButton2 = new RoundedButton(30);
        SignButton2.setText("Sign up");
        SignButton2.setFont(new Font(font, Font.BOLD, 24));
        SignButton2.setBounds(530, 490, 250, 40);
        add(SignButton2);

        JLabel descriptionOR = new JLabel("--------------------Or--------------------");
        descriptionOR.setFont(new Font(font, Font.BOLD, 24));
        descriptionOR.setBounds(450,540, 490, 40);
        descriptionOR.setForeground(Color.WHITE);
        add(descriptionOR);

        loginButton2 = new RoundedButton(30);
        loginButton2.setText("Log in");
        loginButton2.setFont(new Font(font, Font.BOLD, 24));
        loginButton2.setBounds(530, 600, 250, 40);
        add(loginButton2);

        loginButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // สั่งให้ CardLayout สลับไปแสดงหน้าที่มีชื่อว่า "loginPage"
                cardLayout.show(contentArea, "loginPage"); 
                contentArea.revalidate();
                contentArea.repaint();
            }
        });
    }
}
