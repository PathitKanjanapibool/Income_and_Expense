import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
public class App extends JFrame implements ActionListener{
    Container cp;
    public App() {
        Initial();
        setComponent2();
        setComponent1();
        Finally();
    }
    public void Initial(){
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(17,23,21));
        
        JLabel l = new JLabel("บันทึกรายรับ-รายจ่าย");
        l.setBounds(100, 80, 1000, 30);
        l.setFont(new Font("Tahoma", Font.BOLD, 36));
        l.setForeground(Color.WHITE);
        cp.add(l);
    }
    public void setComponent1(){
        JLabel l1 = new JLabel("ยอดเงินคงเหลือ");
        l1.setBounds(100, 160, 500, 70);
        l1.setFont(new Font("Tahoma", Font.BOLD, 24));
        l1.setForeground(Color.WHITE);
        cp.add(l1);
        
        RoundedBox box = new RoundedBox(30);
        box.setBackground(new Color(27, 34, 33));
        box.setBounds(80, 172, 1281, 727);
        cp.add(box);
        
    }
    public void setComponent2(){
        RoundedToggleButton button1 = new RoundedToggleButton("รายรับ", 30);
        button1.setBounds(120, 360, 330, 50);
        cp.add(button1);
        
        RoundedToggleButton button2 = new RoundedToggleButton("รายจ่าย", 30);
        button2.setBounds(470, 360, 330, 50);
        cp.add(button2);
        
        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        
        JLabel l2 = new JLabel("ชื่อรายการ");
        l2.setBounds(120, 420, 500, 50);
        l2.setFont(new Font("Tahoma", Font.BOLD, 24));
        l2.setForeground(Color.WHITE);
        cp.add(l2);

        JTextField tf1 = new JTextField();
        tf1.setBounds(120, 470, 680, 50);
        tf1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        cp.add(tf1);

        JLabel l3 = new JLabel("หมวดหมู่");
        l3.setBounds(120, 530, 500, 50);
        l3.setFont(new Font("Tahoma", Font.BOLD, 24));
        l3.setForeground(Color.WHITE);
        cp.add(l3);

        JTextField tf2 = new JTextField();
        tf2.setBounds(120, 580, 320, 50);
        tf2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        cp.add(tf2);

        JLabel l4 = new JLabel("วันที่");
        l4.setBounds(470, 530, 500, 50);
        l4.setFont(new Font("Tahoma", Font.BOLD, 24));
        l4.setForeground(Color.WHITE);
        cp.add(l4);

        JTextField tf3 = new JTextField();
        tf3.setBounds(470, 580, 320, 50);
        tf3.setFont(new Font("Tahoma", Font.PLAIN, 24));
        cp.add(tf3);

        JLabel l5 = new JLabel("จำนวนเงิน");
        l5.setBounds(120, 650, 500, 50);
        l5.setFont(new Font("Tahoma", Font.BOLD, 24));
        l5.setForeground(Color.WHITE);
        cp.add(l5);

        JTextField tf4 = new JTextField();
        tf4.setBounds(120, 700, 320, 50);
        tf4.setFont(new Font("Tahoma", Font.PLAIN, 24));
        cp.add(tf4);

        RoundedButton roB1 = new RoundedButton("บันทึก", 30);
        roB1.setBounds(550, 700, 250,70);
        cp.add(roB1);

        RoundedBox box = new RoundedBox(30);
        box.setBackground(Color.BLACK);
        box.setBounds(100, 340, 730, 530);
        cp.add(box);
    }

    public void Finally(){
        this.setSize(1440, 960);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }
    public static void main(String[] args) {
        App app = new App();
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


class RoundedBox extends JComponent { //กล่องมุมมน
    private int arc;

    public RoundedBox(int arc) {
        this.arc = arc;
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, w - 1, h - 1, arc, arc));

        g2.setColor(new Color(33, 35, 50));
        g2.setStroke(new BasicStroke(3));
        g2.draw(new RoundRectangle2D.Double(1, 1, w - 3, h - 3, arc, arc));

        g2.dispose();
    }
}

class RoundedToggleButton extends JToggleButton {
    private int cornerRadius;
    private Color selectedColor = new Color(56, 142, 60);   // สีเขียวเข้ม เมื่อถูกกดเลือก
    private Color defaultColor = new Color(220, 220, 220);  // สีเทาอ่อน เมื่อยังไม่เลือก

    public RoundedToggleButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(new Font("Tahoma", Font.BOLD, 16));
        setForeground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (isSelected()) {
            g2.setColor(selectedColor);
            setForeground(Color.WHITE); 
        } else {
            g2.setColor(defaultColor);
            setForeground(Color.BLACK); 
        }
        
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();
        super.paintComponent(g);
    }
}

class RoundedButton extends JButton {
    private int cornerRadius;

    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        
        setFont(new Font("Tahoma", Font.BOLD, 24));
        setForeground(Color.WHITE);
        setBackground(new Color(76, 175, 80));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else {
            g2.setColor(getBackground());
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.dispose();

        super.paintComponent(g);
    }
}