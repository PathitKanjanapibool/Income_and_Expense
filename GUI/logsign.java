package GUI;
import java.awt.*;
import javax.swing.*;

public class logsign extends JFrame{
    String font = "Leelawadee UI";
    Container cp;
    int width = 1280; 
    int height = 720;

    JTextField uSernmaetf;
    JTextField passWordF;
    RoundedButton loginButton;
    RoundedButton SignButton;

    public logsign(){
        Initial();
        setComponent();
        setComponent2();
        Finally();
    }
    
    public void Initial(){
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(setColor.BackgroundColor());
    }
    public void setComponent(){
        JLabel Head = new JLabel("Income & Expense App");
        Head.setFont(new Font(font, Font.BOLD, 36));
        Head.setBounds(450,70, 400, 60);
        Head.setForeground(Color.WHITE);
        add(Head);
        
        JLabel userName = new JLabel("Username :");
        userName.setFont(new Font(font, Font.BOLD, 24));
        userName.setBounds(390,150, 150, 40);
        userName.setForeground(Color.WHITE);
        add(userName);

        JLabel passWord = new JLabel("Password :");
        passWord.setFont(new Font(font, Font.BOLD, 24));
        passWord.setBounds(390,250, 150, 40);
        passWord.setForeground(Color.WHITE);
        add(passWord);
        
        uSernmaetf = new JTextField();
        uSernmaetf.setBounds(390,200, 500, 40);
        uSernmaetf.setFont(new Font(font, Font.BOLD, 24));
        add(uSernmaetf);

        passWordF = new JTextField();
        passWordF.setBounds(390,300, 500, 40);
        passWordF.setFont(new Font(font, Font.BOLD, 24));
        add(passWordF);
    }

    public void setComponent2(){
        loginButton = new RoundedButton(30);
        loginButton.setText("Log in");
        loginButton.setFont(new Font(font, Font.BOLD, 24));
        loginButton.setBounds(530, 370, 250, 40);
        add(loginButton);

        JLabel descriptionOR = new JLabel("--------------------Or--------------------");
        descriptionOR.setFont(new Font(font, Font.BOLD, 24));
        descriptionOR.setBounds(450,420, 500, 40);
        descriptionOR.setForeground(Color.WHITE);
        add(descriptionOR);

        SignButton = new RoundedButton(30);
        SignButton.setText("Sign up");
        SignButton.setFont(new Font(font, Font.BOLD, 24));
        SignButton.setBounds(530, 480, 250, 40);
        add(SignButton);
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
