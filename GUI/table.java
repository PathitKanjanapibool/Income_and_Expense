package GUI;
import System.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class table extends JPanel{
    private final DefaultTableModel Table;
    private final JTable jt;

    public String font = "Leelawadee UI";

    public table(){
        setLayout(new BorderLayout());
        setFont(new Font(font, Font.BOLD, 64));
        String[] columns = {"รายการ", "เวลา", "ประเภท", "หมวดหมู่", "จำนวนเงิน"};
        Table = new DefaultTableModel(columns,0);

        jt = new JTable(Table);
        jt.getTableHeader().setFont(new Font(font, Font.BOLD, 20));
        jt.setFont(new Font(font, Font.BOLD, 15));
        jt.setAutoCreateRowSorter(true);
        add(new JScrollPane(jt),BorderLayout.CENTER);
    }
    public void addRow(String name, LocalDate date, TransactionType type, String category, double amount){
        Table.addRow(new Object[]{name, date, type, category, amount});
    }
}
