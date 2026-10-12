package GUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

//หน้าเมนูฝั่งซ้าย

public class Menu extends JPanel {
    private final String font = "Leelawadee UI";
    private final String[] menuKeys  = {"HOME", "CATEGORY", "LIST"};
    private final String[] menuNames = {"หน้าแรก", "หมวดหมู่", "รายการ"};
    private final int height = 960;
    private final int width = 150;

    private JLabel[] menuLabels;
    private ImageIcon[] menuIcons;
    private JPanel MenuBox;

    private CardLayout cardLayout;
    private JPanel contentArea;

    public Menu(CardLayout cardLayout, JPanel contentArea) {
        this.cardLayout = cardLayout;
        this.contentArea = contentArea;

        this.setLayout(null);
        this.setBounds(0, 0, width, height);
        this.setOpaque(false);
        setMenu();
    }

    //องค์ประกอบต่างๆของเมนู
    private void setMenu() {
        MenuBox = new JPanel();
        MenuBox.setLayout(new BoxLayout(MenuBox, BoxLayout.Y_AXIS));
        MenuBox.setBorder(BorderFactory.createEmptyBorder(60, 15, 15, 10));
        MenuBox.setBackground(setColor.BoxColor());
        MenuBox.setBounds(0, 0, 150, 960);

        //เรียกไฟล์รูปภาพ
        menuIcons = new ImageIcon[] {
            new ImageIcon("GUI/images/home.png"),
            new ImageIcon("GUI/images/category.png"),
            new ImageIcon("GUI/images/list.png")
        };
        menuLabels = new JLabel[menuNames.length];

        for (int i = 0; i < menuNames.length; i++) {
            final int idx = i;
            menuLabels[i] = addContent(menuNames[i]);
            menuLabels[i].addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentArea, menuKeys[idx]);
                    setActiveMenu(idx);
                }
            });
        }
        setActiveMenu(0);

        add(MenuBox);
    }

    //สร้าง Icon พร้อมข้อความ
    private JLabel addContent(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font(font, Font.BOLD, 32));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setIconTextGap(-10);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        MenuBox.add(label);
        MenuBox.add(Box.createVerticalStrut(30));
        return label;
    }

    //ถ้าคลิ้กแล้ว Icon จะเป็นเสีเขียว
    private void setActiveMenu(int active) {
        for (int i = 0; i < menuLabels.length; i++) {
            Color c = (i == active) ? setColor.Green() : Color.WHITE;
            Image scaled = UIUtils.tint(menuIcons[i], c).getImage()
                                  .getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            menuLabels[i].setIcon(new ImageIcon(scaled));
            menuLabels[i].setForeground(c);
        }
    }
    public int getHeight() {int h = this.height; return  h;}
    public int getWidth() {int w = this.width; return  w;}
}