import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
public class App extends JFrame implements ActionListener{
    Container cp;
    public App() {
        Initial();
        setComponent1();
        Finally();
    }
    public void Initial(){
        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(17,23,21));
    }
    public void setComponent1(){
        RoundedBox box = new RoundedBox(30);
        box.setBackground(new Color(27, 34, 33));
        box.setBounds(80, 172, 1281, 727);
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
