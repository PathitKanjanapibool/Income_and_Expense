package GUI;
import GUIUtils.RoundedBox;
import GUIUtils.setColor;
import GUIUtils.table;
import Income_and_Expense_main.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List; 
import java.util.Locale;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class page3 extends JPanel implements ActionListener{
    private String font = "Leelawadee UI";
    private table Table;
    private final Name csv;
    private final String filepath;
    private final List<Transaction> data;

    Locale th = Locale.forLanguageTag("th");
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMMM", th);

    JComboBox<String> typeBox;
    JComboBox<String> categoryBox;
    JComboBox<String> monthBox;
    JComboBox<String> yearBox;
    JButton resetButton;

    int y1 = LocalDate.now().getYear();
    YearMonth now = YearMonth.now();

    int a_height = App.height;
    int a_width = App.width;

    int m_height = Menu.height;
    int m_width = Menu.width;

    private JLabel incomeLabel;
    private JLabel outcomeLabel;
    private JLabel countLabel;

    private int table_width = 1050;
    private int table_x = (a_width - m_width)/2 - table_width/2;    // 120

    private int head_y = 30;                                        // y 30-130
    private int filter_y = 150;                                     // y 150-200
    private int filter_size_width = 198;
    private int filter_size_height = 50;

    private int table_y = filter_y + filter_size_height + 15;       // 215
    private int table_height = 520;                                 // y 215-735

    private int summary_y = table_y + table_height + 40;            // 775
    private int summary_height = 105;                               // y 755-860

    private final page1 p1;


    public page3(page1 p1,String filepath){
        this.p1 = p1;
        this.filepath = filepath;
        csv = new Name(filepath);
        data = new ArrayList<>(csv.loadToMap(filepath).values());


        this.setLayout(null);
        this.setOpaque(false);
        Filter();
        setComponent1();
        Summary();
        TableList();
    }
    public void setComponent1(){
        JLabel Head = new JLabel("รายการ");
        Head.setFont(new Font(font, Font.BOLD, 64));
        Head.setForeground(Color.white);
        Head.setBounds(100, head_y, 600, 100);
        add(Head);
    }

    public void Filter(){
        JPanel filter = new JPanel();
        filter.setBounds((a_width - m_width)/2 - table_width/2, 150, 1050, 50);
        filter.setBackground(setColor.BackgroundColor());
        filter.setFocusable(false);
        filter.setLayout(new BoxLayout(filter,BoxLayout.X_AXIS));

        //Filter รายรับ-รายจ่าย
        typeBox = new JComboBox<>(new String[]{"ทั้งหมด"});
        typeBox.setFont(new Font(font, Font.BOLD, 16));
        typeBox.setBackground(setColor.BoxColor2());
        typeBox.setForeground(Color.white);
        setSize(typeBox,filter_size_width,filter_size_height);
        typeBox.addItem("รายรับ");
        typeBox.addItem("รายจ่าย");
        typeBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Filter หมวดหมู่
        categoryBox = new JComboBox<>(new String[]{"ทุกหมวดหมู่"});
        categoryBox.setFont(new Font(font, Font.BOLD, 16));
        categoryBox.setBackground(setColor.BoxColor2());
        categoryBox.setForeground(Color.white);
        setSize(categoryBox,filter_size_width,filter_size_height);
        Set<String> category= new HashSet<>();
        for (Transaction c : data) {
            category.add(c.getCategory());
        }
        for(String c : category){
            categoryBox.addItem(c);
        }

        //Filter ปี
        yearBox = new JComboBox<>(new String[]{"ทุกปี"});
        yearBox.setFont(new Font(font, Font.BOLD, 16));
        yearBox.setBackground(setColor.BoxColor2());
        yearBox.setForeground(Color.white);
        setSize(yearBox,filter_size_width,filter_size_height);

        Set<String> year = new HashSet<>();
        for (Transaction y : data) {
            year.add(Integer.toString(y.getYear()));
        }
        for(String y : year){
            yearBox.addItem(y);
        }
        
        //Filter เดือน
        monthBox = new JComboBox<>(new String[]{"ทุกเดือน"});
        monthBox.setFont(new Font(font, Font.BOLD, 16));
        monthBox.setBackground(setColor.BoxColor2());
        monthBox.setForeground(Color.white);
        setSize(monthBox,filter_size_width,filter_size_height);
            
        for (int i = 12; i >= 1; i--) monthBox.addItem(YearMonth.of(y1, i).format(fmt));

        //Filter ล้างตัวกรอง
        resetButton = new JButton("ล้างตัวกรอง");
        resetButton.setFont(new Font(font, Font.BOLD, 16));
        resetButton.setBackground(setColor.BoxColor2());
        resetButton.setForeground(Color.white);
        resetButton.setMinimumSize(new Dimension(125,filter_size_height));
        resetButton.setPreferredSize(new Dimension(125,filter_size_height));
        resetButton.setMaximumSize(new Dimension(125,filter_size_height));

        filter.add(typeBox);
        filter.add(Box.createHorizontalStrut(15));
        filter.add(categoryBox);
        filter.add(Box.createHorizontalStrut(15));
        filter.add(yearBox);
        filter.add(Box.createHorizontalStrut(15));
        filter.add(monthBox);
        filter.add(Box.createHorizontalStrut(15));
        filter.add(resetButton);
        add(filter);
        yearBox.addActionListener(this);
        resetButton.addActionListener(this);
        typeBox.addActionListener(this);
        categoryBox.addActionListener(this);
        monthBox.addActionListener(this);
    }

    public void Summary(){
        JPanel summary = new JPanel(new GridLayout(1, 3, 15, 0));
        summary.setOpaque(false);
        summary.setBounds(table_x, summary_y, table_width, summary_height);
 
        incomeLabel  = summaryCard(summary, "รายรับรวม",   setColor.Green());
        outcomeLabel = summaryCard(summary, "รายจ่ายรวม",  setColor.Red());
        countLabel   = summaryCard(summary, "จำนวนรายการ", Color.white);
        add(summary);
    }
 
    //สร้างกล่องเก็บข้อมูล
    private JLabel summaryCard(JPanel parent, String title, Color valueColor){
        RoundedBox card = new RoundedBox(30);
        card.setBackground(setColor.BoxColor());
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
 
        JLabel t = new JLabel(title);
        t.setFont(new Font(font, Font.BOLD, 20));
        t.setForeground(Color.white);
 
        JLabel v = new JLabel("-");
        v.setFont(new Font(font, Font.BOLD, 36));
        v.setForeground(valueColor);
 
        card.add(t, BorderLayout.NORTH);
        card.add(v, BorderLayout.CENTER);
        parent.add(card);
        return v;
    }
    //คำนวณยอด รายรับ รายจ่าย จำนวนรายการ
    public void updateSummary(){
        if (Table == null || incomeLabel == null) return;
        DefaultTableModel model = Table.getTableModel();
        double income = 0, outcome = 0;
        int rows = model.getRowCount();
        for (int r = 0; r < rows; r++) {
            String type = model.getValueAt(r, 3).toString(); //คอลัมน์ "ประเภท"
            double amount = ((Number) model.getValueAt(r, 5)).doubleValue(); //คอลัมน์ "จำนวนเงิน"
            if (type.equals("รายรับ")) income += amount;
            else outcome += amount;
        }
        incomeLabel.setText(String.format("฿%,.2f", income));
        outcomeLabel.setText(String.format("฿%,.2f", outcome));
        countLabel.setText(String.format("%,d รายการ", rows));
    }

    public void setSize(JComboBox<String> cbb, int width,int height) {
        cbb.setMinimumSize(new Dimension(width,height));
        cbb.setPreferredSize(new Dimension(width,height));
        cbb.setMaximumSize(new Dimension(width,height));
    }

    public void TableList(){

        Table = new table(p1,csv);
        Table.setBackground(setColor.BackgroundColor());
        Table.getTableModel().addTableModelListener(e -> updateSummary());
        for(Transaction t : data){
            Table.addRow(t.getId(),t.getname(), t.getDate(), t.getType() == TransactionType.INCOME ? "รายรับ" : "รายจ่าย", t.getCategory(), t.getAmount());
        }
        Table.setBackground(setColor.BackgroundColor());
        Table.setBounds((a_width-m_width)/2-(table_width/2), table_y, table_width, table_height);
        add(Table);
        updateSummary();
    }

    public void loadTable(){    
    if (Table == null) return;   // กันถูกเรียกก่อนสร้างตาราง

    //ประเภท
    TransactionType type = null;
    if (typeBox.getSelectedIndex() == 1) type = TransactionType.INCOME;
    if (typeBox.getSelectedIndex() == 2) type = TransactionType.OUTCOME;

    //หมวดหมู่
    String category = (categoryBox.getSelectedIndex() > 0) ? (String) categoryBox.getSelectedItem() : null;

    //ปี
    Integer year = (yearBox.getSelectedIndex() > 0) ? Integer.parseInt((String) yearBox.getSelectedItem()) : null;

    //แปลงชื่อเดือนไทยกลับเป็นเลข 1-12
    Integer month = null;
    if (monthBox.getSelectedIndex() > 0) {
        String name = (String) monthBox.getSelectedItem();
        for (int i = 1; i <= 12; i++) {
            if (YearMonth.of(y1, i).format(fmt).equals(name)) { month = i; break; }
        }
    }

    Table.clearRows();
    for (Transaction t : csv.filter(filepath, year, month, category, type)) {
        Table.addRow(t.getId(),t.getname(), t.getDate(), t.getType() == TransactionType.INCOME ? "รายรับ" : "รายจ่าย", t.getCategory(), t.getAmount());
    }
}

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == yearBox){
            monthBox.removeAllItems();
            monthBox.addItem("ทุกเดือน");
            int last = yearBox.getSelectedItem().equals(y1+"") ? now.getMonthValue() : 12;
            for (int i = last; i >= 1; i--) 
                monthBox.addItem(YearMonth.of(y1, i).format(fmt));
            
        }

        if(e.getSource() == resetButton){
            typeBox.setSelectedIndex(0);
            categoryBox.setSelectedIndex(0);
            yearBox.setSelectedIndex(0);
            monthBox.setSelectedIndex(0);
        }
        loadTable();
    }
}
