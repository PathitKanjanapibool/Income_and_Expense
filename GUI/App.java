import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class App extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel contentArea; // ส่วนเปลี่ยนหน้าทางขวา
    private int money = 0;
    
    JPanel MenuBox;
    Container cp;
    RoundedBox box1, box2, box3;
    RoundedToggleButton button1, button2;
    String font = "Leelawadee UI";

    
    private JLabel lblHome, lblCategory, lblList; //เก็บข้อความดเมนู 

    public App() {
        Initial();
        setupLayout();
        Finally();
    }

    public void Initial() {
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(17, 23, 21));
    }

    public void setupLayout() {
        
        setMenu();

        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBounds(150, 0, 1290, 960); 
        contentArea.setOpaque(false);

        contentArea.add(page1(), "HOME");
        contentArea.add(page2(), "CATEGORY");
        contentArea.add(page3(), "LIST");

        cp.add(contentArea);
    }

    
    public JPanel page1() {
        JPanel page = new JPanel(null);
        page.setOpaque(false);

        JLabel Head = new JLabel("บันทึกรายรับ-รายจ่าย");
        Head.setFont(new Font(font, Font.BOLD, 53));
        Head.setForeground(Color.white);
        Head.setBounds(25, 45, 600, 100);
        page.add(Head);

        box1 = new RoundedBox(30); //กล่องใหญ่ครอบทั้งหมด
        box1.setBackground(new Color(27, 34, 33));
        box1.setBounds(50, 172, 1200, 727);
        box1.setLayout(null);
        page.add(box1);

        box2 = new RoundedBox(50); //กล่องเล็กด้านซ้าย รับ input 
        box2.setBackground(new Color(17, 23, 21));
        box2.setBounds(40, 180, 650, 525);
        box1.add(box2);

        box3 = new RoundedBox(50); //กล่องแสดงรายการด้านขวา
        box3.setBackground(new Color(17, 23, 21));
        box3.setBounds(700, 50, 450, 655);
        box1.add(box3);

        JLabel Money_left_text = new JLabel("ยอดเงินคงเหลือ");
        Money_left_text.setFont(new Font(font, Font.BOLD, 32));
        Money_left_text.setForeground(Color.white);
        Money_left_text.setBounds(40, 10, 600, 50);
        box1.add(Money_left_text);

        JLabel Money_left = new JLabel("฿" + money);
        Money_left.setFont(new Font(font, Font.BOLD, 65));
        Money_left.setForeground(Color.white);
        Money_left.setBounds(40, 70, 600, 65);
        box1.add(Money_left);

        setComponent2(); 
        return page;
    }

    
    public JPanel page2() {
        JPanel page = new JPanel(null);
        page.setOpaque(false);

        JLabel Head = new JLabel("หมวดหมู่");
        Head.setFont(new Font(font, Font.BOLD, 53));
        Head.setForeground(Color.white);
        Head.setBounds(25, 45, 600, 100);
        page.add(Head);

        
        return page;
    }

    
    public JPanel page3() {
        JPanel page = new JPanel(null);
        page.setOpaque(false);

        JLabel Head = new JLabel("รายการ");
        Head.setFont(new Font(font, Font.BOLD, 53));
        Head.setForeground(Color.white);
        Head.setBounds(25, 45, 600, 100);
        page.add(Head);

        
        return page;
    }

    
    public void setMenu() {
        JPanel Menu = new JPanel(null);
        Menu.setBounds(0, 0, 150, 960);
        Menu.setBackground(new Color(17, 23, 21));

        MenuBox = new JPanel();
        MenuBox.setLayout(new BoxLayout(MenuBox, BoxLayout.Y_AXIS));
        MenuBox.setBorder(BorderFactory.createEmptyBorder(60, 15, 15, 10));
        MenuBox.setBackground(new Color(27, 34, 33));
        MenuBox.setBounds(0, 0, 150, 960);

        ImageIcon homeIcon = new ImageIcon("images/home.png");
        ImageIcon categoryIcon = new ImageIcon("images/category.png");
        ImageIcon listIcon = new ImageIcon("images/list.png");

        lblHome = addContent("หน้าแรก", homeIcon, true);
        lblCategory = addContent("หมวดหมู่", categoryIcon, false);
        lblList = addContent("รายการ", listIcon, false);

        
        lblHome.addMouseListener(new MouseAdapter() { //event click for Home
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentArea, "HOME");
                setActiveMenu(lblHome);
            }
        });

        lblCategory.addMouseListener(new MouseAdapter() { //event click for Category
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentArea, "CATEGORY");
                setActiveMenu(lblCategory);
            }
        });

        lblList.addMouseListener(new MouseAdapter() { //event click for List
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentArea, "LIST");
                setActiveMenu(lblList);
            }
        });

        Menu.add(MenuBox);
        cp.add(Menu);
    }

    public JLabel addContent(String text, ImageIcon image, boolean first) {
        ImageIcon colored = UIUtils.tint(image, first ? new Color(0, 221, 143) : Color.WHITE);
        Image scaled = colored.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(text, new ImageIcon(scaled), SwingConstants.CENTER);
        label.setFont(new Font(font, Font.BOLD, 32));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setForeground(first ? new Color(0, 221, 143) : Color.WHITE);
        label.setIconTextGap(-10);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        MenuBox.add(label);
        MenuBox.add(Box.createVerticalStrut(30));
        return label;
    }

    private void setActiveMenu(JLabel selected) {// change color when click menu
        lblHome.setForeground(Color.WHITE);
        lblCategory.setForeground(Color.WHITE);
        lblList.setForeground(Color.WHITE);

        selected.setForeground(new Color(0, 221, 143));
    }

    public void setComponent2() {
        button1 = new RoundedToggleButton(75);
        button1.setFont(new Font(font, Font.BOLD, 32));
        button1.setBackground(new Color(0, 221, 143));
        button1.setText("รายรับ");
        button1.setForeground(Color.BLACK);
        button1.setBounds(35, 40, 275, 75);
        box2.add(button1);

        button2 = new RoundedToggleButton(75);
        button2.setFont(new Font(font, Font.BOLD, 32));
        button2.setBackground(new Color(119, 49, 49));
        button2.setText("รายจ่าย");
        button2.setForeground(Color.BLACK);
        button2.setBounds(340, 40, 275, 75);
        box2.add(button2);

        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);

        JLabel l2 = new JLabel("ชื่อรายการ");
        l2.setBounds(50, 120, 500, 50);
        l2.setFont(new Font(font, Font.BOLD, 36));
        l2.setForeground(Color.WHITE);
        box2.add(l2);

        JTextField tf1 = new JTextField();
        tf1.setBounds(50, 180, 550, 50);
        tf1.setFont(new Font(font, Font.PLAIN, 24));
        box2.add(tf1);

        JLabel l3 = new JLabel("หมวดหมู่");
        l3.setBounds(50, 240, 500, 50);
        l3.setFont(new Font(font, Font.BOLD, 36));
        l3.setForeground(Color.WHITE);
        box2.add(l3);

        JComboBox<String> cbb = new JComboBox<>();
        cbb.addItem("ComboBox1");
        cbb.addItem("ComboBox2");
        cbb.setBounds(50, 300, 250, 50);
        cbb.setFont(new Font(font, Font.PLAIN, 24));
        box2.add(cbb);

        JLabel l4 = new JLabel("วันที่");
        l4.setBounds(350, 240, 500, 50);
        l4.setFont(new Font(font, Font.BOLD, 36));
        l4.setForeground(Color.WHITE);
        box2.add(l4);

        JTextField tf3 = new JTextField("dd/mm/yyyy");
        tf3.setBounds(350, 300, 250, 50);
        tf3.setFont(new Font(font, Font.PLAIN, 24));
        box2.add(tf3);

        JLabel l5 = new JLabel("จำนวนเงิน");
        l5.setBounds(50, 360, 500, 50);
        l5.setFont(new Font(font, Font.BOLD, 36));
        l5.setForeground(Color.WHITE);
        box2.add(l5);

        JTextField tf4 = new JTextField();
        tf4.setBounds(50, 420, 250, 50);
        tf4.setFont(new Font(font, Font.PLAIN, 36));
        box2.add(tf4);

        RoundedButton roB1 = new RoundedButton(50);
        roB1.setBounds(365, 400, 220, 90);
        roB1.setBackground(new Color(0, 221, 143));
        roB1.setText("บันทึก");
        roB1.setFont(new Font(font, Font.BOLD, 36));
        roB1.setForeground(Color.BLACK);
        box2.add(roB1);

        button1.addActionListener(this);
        button2.addActionListener(this);
    }

    public void Finally() {
        this.setSize(1440, 960);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "0.85");
        new App();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            button1.setBackground(new Color(0, 221, 143));
            button2.setBackground(new Color(119, 49, 49));
        }
        if (e.getSource() == button2) {
            button2.setBackground(new Color(255, 78, 78));
            button1.setBackground(new Color(0, 132, 86));
        }
    }
}
