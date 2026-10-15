package GUI;

import java.awt.*;
import javax.swing.*;

import GUIUtils.RoundedButton;
import Income_and_Expense_main.*;
import GUIUtils.Icon;

import java.awt.event.*;

public class loginP extends JPanel implements ActionListener{
    String font = "Leelawadee UI";
    JTextField uSernmaetf;
    JPasswordField passWordF;
    RoundedButton loginButton;
    RoundedButton SignButton;
    
    CardLayout cardLayout;
    JPanel contentArea;
    
    public loginP(CardLayout cardLayout, JPanel contentArea) {
        this.cardLayout = cardLayout;
        this.contentArea = contentArea;
        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, 1280, 720);
        setComponent();
        setComponent2();
    }

    public void setComponent(){
        JLabel Head = new JLabel("Income & Expense App");
        Head.setFont(new Font(font, Font.BOLD, 36));
        Head.setBounds(450,20, 400, 60);
        Head.setForeground(Color.WHITE);
        add(Head);

        JLabel info = new JLabel("Login");
        info.setFont(new Font(font, Font.BOLD, 36));
        info.setBounds(600,90, 400, 50);
        info.setForeground(Color.WHITE);
        add(info);

        JLabel userName = new JLabel("Username :");
        userName.setFont(new Font(font, Font.BOLD, 24));
        userName.setBounds(390,190, 150, 40);
        userName.setForeground(Color.WHITE);
        add(userName);

        JLabel passWord = new JLabel("Password :");
        passWord.setFont(new Font(font, Font.BOLD, 24));
        passWord.setBounds(390,290, 150, 40);
        passWord.setForeground(Color.WHITE);
        add(passWord);
        
        uSernmaetf = new JTextField();
        uSernmaetf.setBounds(390,240, 500, 40);
        uSernmaetf.setFont(new Font(font, Font.BOLD, 24));
        add(uSernmaetf);

        // passWordF = new JPasswordField();
        // passWordF.setBounds(390,340, 500, 40);
        // passWordF.setFont(new Font(font, Font.BOLD, 24));
        // add(passWordF);

        passWordF = new JPasswordField();
        passWordF.setBounds(390,340, 500, 40);
        passWordF.setFont(new Font(font, Font.BOLD, 24));
        add(passWordF);
    }

    public void setComponent2(){
        loginButton = new RoundedButton(30);
        loginButton.setText("Log in");
        loginButton.setFont(new Font(font, Font.BOLD, 24));
        loginButton.setBounds(530, 410, 250, 40);
        add(loginButton);

        loginButton.addActionListener(this);

        JLabel descriptionOR = new JLabel("--------------------Or--------------------");
        descriptionOR.setFont(new Font(font, Font.BOLD, 24));
        descriptionOR.setBounds(450,460, 500, 40);
        descriptionOR.setForeground(Color.WHITE);
        add(descriptionOR);

        SignButton = new RoundedButton(30);
        SignButton.setText("Sign up");
        SignButton.setFont(new Font(font, Font.BOLD, 24));
        SignButton.setBounds(530, 520, 250, 40);
        add(SignButton);

        SignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // สั่งให้ CardLayout สลับไปแสดงหน้าที่มีชื่อว่า "signPage"
                cardLayout.show(contentArea, "signPage"); 
                contentArea.revalidate();
                contentArea.repaint();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = uSernmaetf.getText().trim();
            String password = passWordF.getText().trim();

            try {
                UserAcc acc = new UserAcc(username, password);
                Userlogin loginService = new Userlogin("data.csv");
                
                loginService.logIn(acc);

                String userFilePath = makeUserFile.getUserFileName(acc);
                String userCAtatPath = makeUserFile.getUserFileCat(acc);
                String colorpath = makeUserFile.getUserFileCatColor(acc);
                JOptionPane.showMessageDialog(
                    this,
                    "login Success!",
                    "sum reid",
                    JOptionPane.INFORMATION_MESSAGE
                );

                new App(userFilePath,userCAtatPath,colorpath); 

                Window window = SwingUtilities.getWindowAncestor(this);
                if (window instanceof JFrame) {
                    ((JFrame) window).dispose();
                }

            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(
                    this,
                    "เกิดข้อผิดพลาด: " + ex.getMessage(),
                    "ล็อกอินไม่สำเร็จ",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
