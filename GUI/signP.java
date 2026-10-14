package GUI;

import java.awt.*;
import javax.swing.*;

import GUIUtils.RoundedButton;
import Income_and_Expense_main.UserAcc;
import Income_and_Expense_main.Userlogin;

import java.awt.event.*;

public class signP extends JPanel implements ActionListener{
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

        UIManager.put("OptionPane.messageFont", new Font(font, Font.PLAIN, 20));
        UIManager.put("OptionPane.buttonFont", new Font(font, Font.PLAIN, 20));

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
        
        //Add actionlistener ให้ปุ่ม SignUp
        SignButton2.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        // เมื่อกดปุ่ม Sign up
    if (e.getSource() == SignButton2) { // เปลี่ยน SignButton2 เป็นชื่อตัวแปรปุ่ม Sign up ของคุณ
        String username = uSernmaetfsign.getText().trim();
        String password = new String(passWordFsign.getPassword()).trim();
        String confirmPassword = new String(conFirmpassfield.getPassword()).trim();

        // เช็กการกรอกข้อมูลช่องว่าง
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "กรุณากรอกข้อมูลให้ครบทุกช่อง",
                "แจ้งเตือน",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // เช็ก Password และ Confirm Password 
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(
                this,
                "รหัสผ่านและยืนยันรหัสผ่านไม่ตรงกัน",
                "ข้อผิดพลาด",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            UserAcc acc = new UserAcc(username, password);
            Userlogin loginService = new Userlogin("data.csv");

            //เรียก SignIn จาก Userlogin.java เพื่อบันทึกข้อมูลและสร้างไฟล์ประจำตัว
            loginService.SignIn(acc);

            JOptionPane.showMessageDialog(
                this,
                "สมัครสมาชิกสำเร็จ! กรุณาเข้าสู่ระบบ",
                "สำเร็จ",
                JOptionPane.INFORMATION_MESSAGE
            );

            //ล้างค่าใน Text Fields
            uSernmaetfsign.setText("");
            passWordFsign.setText("");
            conFirmpassfield.setText("");

            //กลับหน้า login
            cardLayout.show(contentArea, "loginPage");
            contentArea.revalidate();
            contentArea.repaint();

        } catch (IllegalArgumentException ex) {
            // ดักจับข้อความ Error จาก SignIn() เช่น "มีuserอยู่แล้ว โปรดlogin" หรือ "มีเครื่องหมาย ,"
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "สมัครสมาชิกไม่สำเร็จ",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this,
                "เกิดข้อผิดพลาดในระบบ: " + ex.getMessage(),
                "ข้อผิดพลาด",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    }
}