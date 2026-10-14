package GUI;
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
        TableList();
    }
    public void setComponent1(){
        JLabel Head = new JLabel("รายการ");
        Head.setFont(new Font(font, Font.BOLD, 64));
        Head.setForeground(Color.white);
        Head.setBounds(100, 45,600,100);
        add(Head);
    }

    public void Filter(){
        JPanel filter = new JPanel();
        filter.setBounds((750/2)-m_width, 150, 750, 50);
        filter.setBackground(setColor.BackgroundColor());
        filter.setFocusable(false);
        filter.setLayout(new BoxLayout(filter,BoxLayout.X_AXIS));

        typeBox = new JComboBox<>(new String[]{"ทั้งหมด"});
        typeBox.setFont(new Font(font, Font.BOLD, 16));
        typeBox.setBackground(setColor.BoxColor2());
        typeBox.setForeground(Color.white);
        typeBox.setPreferredSize(new Dimension(100,50));
        typeBox.addItem("รายรับ");
        typeBox.addItem("รายจ่าย");
        typeBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        categoryBox = new JComboBox<>(new String[]{"ทุกหมวดหมู่"});
        categoryBox.setFont(new Font(font, Font.BOLD, 16));
        categoryBox.setBackground(setColor.BoxColor2());
        categoryBox.setForeground(Color.white);
        categoryBox.setPreferredSize(new Dimension(100,50));
        Set<String> category= new HashSet<>();
        for (Transaction c : data) {
            category.add(c.getCategory());
        }
        for(String c : category){
            categoryBox.addItem(c);
        }

        yearBox = new JComboBox<>(new String[]{"ทุกปี"});
        yearBox.setFont(new Font(font, Font.BOLD, 16));
        yearBox.setBackground(setColor.BoxColor2());
        yearBox.setForeground(Color.white);
        yearBox.setPreferredSize(new Dimension(100,50));

        
        Set<String> year = new HashSet<>();
        for (Transaction y : data) {
            year.add(Integer.toString(y.getYear()));
        }
        for(String y : year){
            yearBox.addItem(y);
        }

        monthBox = new JComboBox<>(new String[]{"ทุกเดือน"});
        monthBox.setFont(new Font(font, Font.BOLD, 16));
        monthBox.setBackground(setColor.BoxColor2());
        monthBox.setForeground(Color.white);
        monthBox.setPreferredSize(new Dimension(100,50));
            
        for (int i = 12; i >= 1; i--) monthBox.addItem(YearMonth.of(y1, i).format(fmt));

        resetButton = new JButton("ล้างตัวกรอง");
        resetButton.setFont(new Font(font, Font.BOLD, 16));
        resetButton.setBackground(setColor.BoxColor2());
        resetButton.setForeground(Color.white);
        resetButton.setPreferredSize(new Dimension(100,50));

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

    public void TableList(){
        int table_height = 550;
        int table_width = 1050;

        Table = new table(p1,csv);
        Table.setBackground(setColor.BackgroundColor());
        for(Transaction t : data){
            Table.addRow(t.getId(),t.getname(), t.getDate(), t.getType() == TransactionType.INCOME ? "รายรับ" : "รายจ่าย", t.getCategory(), t.getAmount());
        }
        Table.setBackground(setColor.BackgroundColor());
        Table.setBounds((a_width-m_width)/2-(table_width/2), 250, table_width, table_height);
        add(Table);
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
