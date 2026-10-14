package GUIUtils;
import java.awt.*;
import javax.swing.*;


public class category_list extends JPanel{
    private String font = "Leelawadee UI";
    int barWidth = 400;
    public category_list(String s, Color c,int progress){
        //หาprogress 
        //ดึงรายจ่ายสูงสุดมาดู 
        //ดึงข้อมูลจากcategoryว่ามีการจ่ายเท่าไหร่ เท่าไหร่ของรายจ่ายทั้งหมด
        //
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
 
    


        JLabel l_1 = new JLabel(s);
        l_1.setFont(new Font(font, Font.PLAIN, 20));
        l_1.setForeground(Color.white);

        JLabel l_2 = new JLabel(progress + "%");
        l_2.setFont(new Font(font, Font.PLAIN, 20));
        l_2.setForeground(Color.white);

        RoundedBox b1 = new RoundedBox(25);
        b1.setLayout(new BoxLayout(b1, BoxLayout.X_AXIS));
        b1.setAlignmentX(Component.LEFT_ALIGNMENT);
        b1.setMaximumSize(new Dimension(barWidth, 25));

        RoundedBox b2 = new RoundedBox(25);
        b2.setBackground(c);
        b2.setMaximumSize(new Dimension(barWidth * progress / 100, 50));
        b1.add(b2);

        JPanel horizon = new JPanel(new BorderLayout());
        horizon.setOpaque(false);
        horizon.setAlignmentX(Component.LEFT_ALIGNMENT);
        horizon.setMaximumSize(new Dimension(barWidth, 30));
        horizon.add(l_1, BorderLayout.WEST);
        horizon.add(l_2, BorderLayout.EAST);

        Box group = Box.createVerticalBox();
        group.setAlignmentX(Component.LEFT_ALIGNMENT); 
        group.add(horizon);
        group.add(Box.createVerticalStrut(5));
        group.add(b1);
        add(group);
    }

}
