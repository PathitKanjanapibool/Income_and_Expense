import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class UIUtils {
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

class RoundedPainter { //ทำขอบมน
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

class RoundedToggleButton extends JToggleButton {
    private int cornerRadius;

    public RoundedToggleButton(int arc) {
        this.cornerRadius = arc;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public void paintComponent(Graphics g) {
        RoundedPainter.paintRounded(g, getWidth(), getHeight(), cornerRadius,
            getBackground(), null, 0);
        super.paintComponent(g);
    }
}

class RoundedButton extends JButton {
    private int cornerRadius;

    public RoundedButton(int arc) {
        this.cornerRadius = arc;
        
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        
    }

    public void paintComponent(Graphics g) {
        RoundedPainter.paintRounded(g, getWidth(), getHeight(), cornerRadius,
            getBackground(), null, 0);
        super.paintComponent(g);
    }
}