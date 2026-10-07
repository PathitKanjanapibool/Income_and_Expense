package GUI;
import java.awt.*;
import java.util.Calendar;
import javax.swing.*;
import java.text.SimpleDateFormat;

public class DatePicker {
    private int month = Calendar.getInstance().get(Calendar.MONTH);
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private JLabel monthYearLabel = new JLabel("", JLabel.CENTER);
    private String day = "";
    private JDialog dialog;
    private JButton[] buttons = new JButton[49];

    // Method สำหรับเรียกเปิดหน้าต่างปฏิทิน
    public void openPicker(JFrame parent) {
        dialog = new JDialog(parent, "Select Date", true); 
        String[] header = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        
        JPanel calendarPanel = new JPanel(new GridLayout(7, 7));
        calendarPanel.setPreferredSize(new Dimension(430, 150));

        // สร้างปุ่ม 49 ปุ่ม
        for (int i = 0; i < 49; i++) {
            final int selection = i;
            buttons[i] = new JButton();
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(Color.WHITE);
            
            if (i > 6) {
                // loop สร้างปุ่มวันที่
                buttons[i].addActionListener(e -> {
                    if (!buttons[selection].getText().isEmpty()) {
                        day = buttons[selection].getText();
                        dialog.dispose(); // ปิดหน้าต่างเมื่อเลือกวันที่เสร็จ
                    }
                });
            } else {
                // ส่วนของหัวตาราง (วัน)
                buttons[i].setText(header[i]);
                buttons[i].setForeground(Color.RED);
                buttons[i].setEnabled(false); 
            }
            calendarPanel.add(buttons[i]);
        }

        // เปลี่ยนเดือน
        JPanel controlPanel = new JPanel(new GridLayout(1, 3));
        JButton prevButton = new JButton("<< Prev");
        prevButton.addActionListener(e -> { month--; displayDate(); });
        
        JButton nextButton = new JButton("Next >>");
        nextButton.addActionListener(e -> { month++; displayDate(); });

        controlPanel.add(prevButton);
        controlPanel.add(monthYearLabel);
        controlPanel.add(nextButton);

        dialog.add(calendarPanel, BorderLayout.CENTER);
        dialog.add(controlPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        
        displayDate();
        dialog.setVisible(true); // โค้ดจะหยุดรอที่บรรทัดนี้จนกว่าผู้ใช้จะปิดหน้าต่าง
    }

    // Method คำนวณและวาดตัวเลขวันที่ลงในตาราง
    private void displayDate() {
        for (int i = 7; i < 49; i++) {
            buttons[i].setText("");
        }
        
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        
        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK); 
        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
        
        int startIndex = 6 + firstDayOfWeek;
        
        for (int currentDay = 1; currentDay <= daysInMonth; currentDay++) {
            buttons[startIndex].setText(String.valueOf(currentDay)); 
            startIndex++; 
        }
        
        monthYearLabel.setText(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.US) + " " + cal.get(Calendar.YEAR));
        
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
    }

    // Method ส่งคืนวันที่ในรูปแบบ yyyy-MM-dd
    public String getFormattedDate() {
        if (day.equals("")) return "";
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
}