package GUIUtils;
import java.awt.Graphics;
import javax.swing.JToggleButton;

//ทำขอบปุ่มกด Toggle มน
public class RoundedToggleButton extends JToggleButton {
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