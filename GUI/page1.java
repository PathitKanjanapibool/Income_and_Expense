package GUI;
import GUIUtils.*;
import Income_and_Expense_main.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class page1 extends JPanel implements ActionListener,KeyListener,FocusListener{

    private final String filepath;

    private double money;
    private Double amount_input;
    private String name;
    private categoryColor_read catColor = new categoryColor_read();
    private LocalDate date;

    private String font = "Leelawadee UI";
    private RoundedBox box1; //กล่องใหญ่
    private RoundedBox box2; //กล่องซ้าย
    private RoundedBox box3; //กล่องขวา

    private RoundedToggleButton button1; 
    private RoundedToggleButton button2;

    private JLabel Money_left;

    private JTextField tf1;
    private JTextField tf2;
    private JTextField tf3;

    private JComboBox<String> cbb;

    private RoundedButton roB1;

    private JButton bc;

    private Timer timer;

    private String currentFile; 
    private calculate cal;
    private List<Transaction> money_remain;
    private Transaction IE_list;
    private Name csv;
    private TransactionType type;

    private DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public page1(String filepath) {

        this.filepath = filepath;

        this.currentFile = (filepath != null && !filepath.isEmpty()) ? filepath : "data.csv";
        this.cal = new calculate(currentFile);
        this.csv = new Name(currentFile);

        money_cal();
        setLayout(null);
        setOpaque(false);
        setBounds(0, 0, 1440, 960);
        setComponent1();
        setComponent2();
        setComponent3();
    }

    //องค์ประกอบภายนอก
    public void setComponent1(){
        JLabel Head = new JLabel("บันทึกรายรับ-รายจ่าย");
        Head.setFont(new Font(font, Font.BOLD, 64));
        Head.setForeground(Color.white);
        Head.setBounds(100, 45,600,100);
        add(Head);


        box1 = new RoundedBox(30);
        box1.setBackground(setColor.BoxColor());
        box1.setLayout(null);
        box1.setBounds(50, 172, 1200, 727);
        add(box1);
        
        box2 = new RoundedBox(50);
        box2.setBackground(setColor.BackgroundColor());
        box2.setLayout(null);
        box2.setBounds(40, 180, 650, 525);
        box1.add(box2);

        box3 = new RoundedBox(50);        
        box3.setLayout((new BoxLayout(box3, BoxLayout.Y_AXIS)));
        box3.setBackground(setColor.BackgroundColor());
        box3.setBounds(710, 50, 450, 655);
        box1.add(box3);

        JLabel Money_left_text = new JLabel("ยอดเงินคงเหลือ"); 
        Money_left_text.setFont(new Font(font , Font.BOLD, 32));
        Money_left_text.setForeground(Color.white);
        Money_left_text.setBounds(40, 10,600,50);
        box1.add(Money_left_text);

        Money_left = new JLabel(String.format("฿%,.2f", money));
        Money_left.setFont(new Font(font , Font.BOLD, 65));
        Money_left.setForeground(Color.white);
        Money_left.setBounds(40, 70,600,65);
        box1.add(Money_left);

    }
    //องค์ประกอบภายในกล่องฝั่งซ้าย
    public void setComponent2(){

        

        //ปุ่มรายสลับรับ
        button1 = new RoundedToggleButton(75);
        button1.setFont(new Font(font, Font.BOLD, 32));
        button1.setBackground(setColor.Green());
        button1.setText("รายรับ");
        button1.setForeground(Color.BLACK);
        button1.setBounds(35, 40, 275, 75);
        button1.setSelected(true);
        box2.add(button1);
        
        //ปุ่มรายสลับจ่าย
        button2 = new RoundedToggleButton(75);
        button2.setFont(new Font(font, Font.BOLD, 32));
        button2.setBackground(new Color(119,49,49));
        button2.setText("รายจ่าย");
        button2.setForeground(Color.BLACK);
        button2.setBounds(340, 40, 275, 75);
        box2.add(button2);
        
        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        
        JLabel l2 = new JLabel("ชื่อรายการ");
        l2.setBounds(50, 120, 500, 50);
        l2.setFont(new Font(font, Font.BOLD, 36));
        l2.setForeground(Color.WHITE);
        box2.add(l2);

        //ใส่ชื่่อรายการ
        tf1 = new JTextField();
        tf1.setBounds(50, 180, 550, 50);
        tf1.setFont(new Font(font, Font.PLAIN, 24));
        box2.add(tf1);

        JLabel l3 = new JLabel("หมวดหมู่");
        l3.setBounds(50, 240, 500, 50);
        l3.setFont(new Font(font, Font.BOLD, 36));
        l3.setForeground(Color.WHITE);
        box2.add(l3);

        //เลือกหมวดหมู่
        cbb = new JComboBox<>();
        cbb.addItem("อาหาร");
        cbb.addItem("เครื่องดื่ม");
        cbb.addItem("เงินเดือน");
        cbb.setBounds(50, 300, 250, 50);
        cbb.setFont(new Font(font, Font.PLAIN, 24));
        box2.add(cbb);

        JLabel l4 = new JLabel("วันที่");
        l4.setBounds(350, 240, 500, 50);
        l4.setFont(new Font(font, Font.BOLD, 36));
        l4.setForeground(Color.WHITE);
        box2.add(l4);

        bc = new JButton("+");
        bc.setBounds(530, 300, 70, 50);
        bc.setBackground(Color.WHITE);
        bc.setFont(new Font(font, Font.BOLD, 36));
        box2.add(bc);

        //ใส่วันเดือนปี
        tf2 = new JTextField(LocalDate.now().format(f));
        tf2.setBounds(350, 300, 180, 50);
        tf2.setFont(new Font(font, Font.PLAIN, 24));
        box2.add(tf2);

        JLabel l5 = new JLabel("จำนวนเงิน");
        l5.setBounds(50, 360, 500, 50);
        l5.setFont(new Font(font, Font.BOLD, 36));
        l5.setForeground(Color.WHITE);
        box2.add(l5);

        //ใส่จำนวนเงิน
        tf3 = new JTextField();
        tf3.setBounds(50, 420, 250, 50);
        tf3.setFont(new Font(font, Font.PLAIN, 36));
        box2.add(tf3);

        //ปุ่มบันทึก
        roB1 = new RoundedButton(50);
        roB1.setBounds(365, 400, 220,90);
        roB1.setBackground(setColor.Green());
        roB1.setText("บันทึก");
        roB1.setFont(new Font(font, Font.BOLD, 36));
        roB1.setForeground(Color.BLACK);
        box2.add(roB1);


        // box2.setComponentZOrder(bc, 0);
        // box2.setComponentZOrder(tf2, 1);

        button1.addActionListener(this);
        button2.addActionListener(this);
        roB1.addActionListener(this);
        tf2.addKeyListener(this);
        tf3.addKeyListener(this);
        bc.addActionListener(this);

        tf2.addFocusListener(this);

        tf1.addKeyListener(this);
    }

    public void setComponent3() {
    box3.setBorder(BorderFactory.createEmptyBorder(15, 25, 0, 0));
    JLabel l = new JLabel("หมวดหมู่ที่ใช้จ่ายมากที่สุด");
    l.setFont(new Font(font, Font.BOLD, 25));
    l.setForeground(Color.white);
    l.setAlignmentX(Component.LEFT_ALIGNMENT);
    box3.add(l);
    box3.add(Box.createVerticalStrut(10));
    
    Map<String, Double> sum = new HashMap<>();
    
    for (Transaction t : money_remain) {
        if (t == null) {continue;}
        if (t.getType() == TransactionType.OUTCOME) {
            String cat = t.getCategory();

            if (sum.containsKey(cat)) {
                sum.put(cat, sum.get(cat) + t.getAmount());
            } else {
                sum.put(cat, t.getAmount());
            }
        }
    }
    if (sum.isEmpty()) return;
    List<Map.Entry<String, Double>> entries = new ArrayList<>(sum.entrySet());
    entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    double max = entries.get(0).getValue();
    double total = cal.totalOutcome(money_remain);

    for (Map.Entry<String, Double> e : entries) {
        String cat = e.getKey();
        double amount = e.getValue();
        int progress = (int) (amount / total * 100);
        box3.add(new category_list(cat, catColor.getColor(cat), progress));
    }
}
    public void actionPerformed(ActionEvent e) {        

        //เปลี่ยนสีรายรับให้สว่างขึ้้น รายจ่ายมืดลง
        if(e.getSource() == button1){
            button1.setBackground(setColor.Green());
            button2.setBackground(new Color(119,49,49));

        }

        //เปลี่ยนสีรายจ่ายให้สว่างขึ้น รายจ่ายมืดลง
        if(e.getSource() == button2){
            button2.setBackground(setColor.Red());
            button1.setBackground(new Color(0,132,86));
        }

        if (e.getSource() == bc) {
            DatePicker picker = new DatePicker();
            picker.openPicker(null); 
        
            String selectedDate = picker.getFormattedDate();
        
            // ถ้าผู้ใช้เลือกวันที่มา
            if (!selectedDate.isEmpty()) {
                tf2.setText(selectedDate);
            }
        }

        //บันทึกรายการไปยังไฟล์csv โดยเก็บ ชื่อรายการ ประเภทรายรับหรือรายจ่าย เวลา หมวดหมู่ จำนวนเงิน
        if(e.getSource() == roB1){
            if (check()){
                String category = cbb.getSelectedItem().toString();
                if (button2.isSelected()) {
                    type = TransactionType.OUTCOME;
                } else {
                    type = TransactionType.INCOME;
                }
                Double amount = Double.parseDouble(tf3.getText());
                IE_list = new Transaction(name,date,type,category,amount);
                csv.saveToCsv(IE_list);
                tf1.setText("");
                tf3.setText("");
                money_cal();
                Money_left.setText(String.format("฿%,.2f", money));
                box1.repaint();
                box3.removeAll();     
                setComponent3();
                box3.revalidate();
                box3.repaint();
                
            };
        }


    }

    public boolean check() {
        try{
           name = tf1.getText();
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if ((c == ','))
                throw new IllegalArgumentException();
    }
            name = (tf1.getText().isBlank()) ? "-" : tf1.getText();

            } catch(IllegalArgumentException ex){
            time_messege(tf1, "ห้ามใส่ตัวอักษรพิเศษ", "", 2);
            return false;
        }
        try {
            date = LocalDate.parse(tf2.getText().trim(), f);
            } catch (DateTimeParseException ex) {
                time_messege(tf2, "วันที่ไม่ถูกต้อง", LocalDate.now().format(f), 1);
                return false;
        }

        try {
            amount_input = Double.parseDouble(tf3.getText().trim());
            if (amount_input < 0) throw new IllegalArgumentException();
            } catch (Exception ex) {
                time_messege(tf3, "ใส่ตัวเลขให้ถูกต้อง", "", 2);
                return false;
        }

        return true;
}

    private void money_cal(){
        money_remain = new ArrayList<>(csv.loadToMap(filepath).values());
        money = cal.totalMoney(money_remain);
    }

    

    public double getMoney(){
        return money;
    }

    public void time_messege(JTextField Cm,String Text, String newText,int second){
        Cm.setText(Text);
        timer = new Timer(second*1000, ev ->{
            Cm.setText(newText);
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void keyTyped(KeyEvent e) {
        if(e.getSource() == tf2){
            if(e.getKeyChar() < '/' || e.getKeyChar() > '9')
                e.consume();
        }
        if(e.getSource() == tf3){
            if(e.getKeyChar() < '0' || e.getKeyChar() > '9')
                e.consume();
        }

        if (e.getSource() == tf1) {
            char c = e.getKeyChar();
            if ((c == ','))
                e.consume();
        }
    }


    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (tf2.getText().equals(LocalDate.now().format(f)))
            tf2.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(tf2.getText().isEmpty()) tf2.setText(LocalDate.now().format(f));
    }
}