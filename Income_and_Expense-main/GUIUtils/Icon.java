package GUIUtils;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Icon {
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