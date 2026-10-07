package GUI;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class UIUtils {

    //เปลี่ยนสีIcon
    public static ImageIcon tint(ImageIcon icon, Color color) {
    Image img = icon.getImage();
    BufferedImage out = new BufferedImage(
            img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    Graphics2D g = out.createGraphics();
    g.drawImage(img, 0, 0, null);
    g.setComposite(AlphaComposite.SrcAtop);
    g.setColor(color);
    g.fillRect(0, 0, out.getWidth(), out.getHeight());
    g.dispose();
    
    return new ImageIcon(out);
    }
}

//set สีที่ใช้ประจำ
class setColor{
    public static Color Green(){ //เขียนอ่อน
        return new Color(0,221,143);
    }

    public static Color Red(){ //แดงสว่างอมส้มอ่อนๆ
        return new Color(255,78,78);
    }

    public static Color BackgroundColor(){ //เขียวเข้มเกือบดำ
        return new Color(17,23,21);
    }

    public static Color BoxColor() {
        return new Color(27, 34, 33);
    }
}

//ทำขอบมน
class RoundedPainter {
    public static void paintRounded(Graphics g, int w, int h, int arc,
                                     Color bgColor, Color borderColor, int borderThickness) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(bgColor);
        g2.fill(new RoundRectangle2D.Double(0, 0, w - 1, h - 1, arc, arc));

        if (borderColor != null) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.draw(new RoundRectangle2D.Double(1, 1, w - 3, h - 3, arc, arc));
        }

        g2.dispose();
    }
}
//ทำขอบกล่องมน
class RoundedBox extends JComponent {
    private int arc;

    public RoundedBox(int arc) {
        this.arc = arc;
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        RoundedPainter.paintRounded(g, getWidth(), getHeight(), arc,
            getBackground(), new Color(33, 35, 50), 3);
    }

}
//ทำขอบปุ่มกด Toggle มน
class RoundedToggleButton extends JToggleButton {
    private int arc;

    public RoundedToggleButton(int arc) {
        this.arc = arc;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public void paintComponent(Graphics g) {
        RoundedPainter.paintRounded(g, getWidth(), getHeight(), arc,
            getBackground(), null, 0);
        super.paintComponent(g);
    }
}
//ทำปุ่มกดมน
class RoundedButton extends JButton {
    private int arc;

    public RoundedButton(int arc) {
        this.arc = arc;
        
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        
    }

    public void paintComponent(Graphics g) {
        RoundedPainter.paintRounded(g, getWidth(), getHeight(), arc,
            getBackground(), null, 0);
        super.paintComponent(g);
    }
}