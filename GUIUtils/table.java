package GUIUtils;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Income_and_Expense_main.Name;

public class table extends JPanel implements MouseListener {
    
    private DefaultTableModel Table;
    private JTable jt;
    private String filePath;
    private final Name csv;
    private static final String font = "Leelawadee UI";

    public table(Name csv){
        this.csv = csv;
        setLayout(new BorderLayout());
        setFont(new Font(font, Font.BOLD, 64));
        String[] columns = {"ID","รายการ", "เวลา", "ประเภท", "หมวดหมู่", "จำนวนเงิน","ลบ"};
        Table = new DefaultTableModel(columns,0){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jt = new JTable(Table);
        jt.setOpaque(false);
        jt.getTableHeader().setFont(new Font(font, Font.BOLD, 24));
        jt.setFont(new Font(font, Font.PLAIN, 20));
        jt.setAutoCreateRowSorter(true);
        jt.setRowHeight(30);
        jt.removeColumn(jt.getColumnModel().getColumn(0));
        DefaultTableCellRenderer red = new DefaultTableCellRenderer();
            red.setHorizontalAlignment(SwingConstants.CENTER);
            red.setBackground(setColor.Red());
            red.setForeground(Color.WHITE);
            jt.getColumnModel().getColumn(5).setCellRenderer(red);
            jt.getColumnModel().getColumn(5).setMaxWidth(80);
        add(new JScrollPane(jt),BorderLayout.CENTER);
        jt.addMouseListener(this);

    }
    public void addRow(String id, String name, LocalDate date, String type, String category, double amount){
        Table.addRow(new Object[]{id,name, date, type, category, amount,"ลบ"});
    }
    public void clearRows(){
    Table.setRowCount(0);
}

    public void mouseClicked(MouseEvent e) {
        int viewRow = jt.rowAtPoint(e.getPoint());
        int viewCol = jt.columnAtPoint(e.getPoint());
        if(viewRow < 0) return;
        if(viewCol < 0) return; 
        if(viewCol != 5) return;
        int modelRow = jt.convertRowIndexToModel(viewRow);
        String id = Table.getValueAt(modelRow, 0).toString();
        csv.deleteByIndex(id);
        Table.removeRow(modelRow);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
