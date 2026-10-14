package GUIUtils;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

//ทำขอบกล่องมน
public class RoundedBox extends JComponent {
    private int arc; //องศามุม

    public RoundedBox(int arc) {
        this.arc = arc;
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        RoundedPainter.paintRounded(g, getWidth(), getHeight(), arc,
            getBackground(), new Color(33, 35, 50), 3);
    }

}