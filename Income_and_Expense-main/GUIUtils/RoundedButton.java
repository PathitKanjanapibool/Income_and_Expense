package GUIUtils;

import java.awt.Graphics;

import javax.swing.JButton;

public class RoundedButton extends JButton {
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
