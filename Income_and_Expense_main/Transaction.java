package Income_and_Expense_main;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    private String id;
    private String name;
    private LocalDate date;
    private TransactionType type;        // "INCOME" หรือ "EXPENSE"
    private String category;    // เช่น "Food", "Transport"
    private double amount;
    private double total_amount;

    // Constructor สำหรับสร้างรายการใหม่
    public Transaction(String name, LocalDate date, TransactionType type, String category, double amount) {
        this.id = UUID.randomUUID().toString();
        this.name = name; //ชื่อรายการ
        this.date = date;
        this.type = type; 
        this.category = category;
        this.amount = amount;

    }

     public Transaction(String id, String name, LocalDate date, TransactionType type, String category, double amount) {
        this.id = id;
        this.name = name; //ชื่อรายการ
        this.date = date;
        this.type = type; 
        this.category = category;
        this.amount = amount;

    }

    // Getters
    public String getId() { return id; }
    public String getname() { return name; }
    public LocalDate getDate() { return date; }
    public TransactionType getType() { return type; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    

    // แปลง Object เป็นบรรทัด CSV
    public String toCsvRow() {
        return id + "," + name + "," + date + "," + type + "," + category + "," + amount ;
    }

    @Override
    public int hashCode() {
        // เมธอดนี้จะแปลงข้อมูล id และ name ให้กลายเป็นตัวเลข int (บวกหรือลบก็ได้) 
        // หากข้อมูลข้างในเหมือนกัน จะได้ตัวเลขเดียวกันเสมอ
        return Objects.hash(id, name);
    }

     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transaction user = (Transaction) obj;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    // สร้าง Object จากบรรทัด CSV (Static Helper)
    public static Transaction fromCsvRow(String csvRow) {
        String[] data = csvRow.split(",");
        String id = data[0].trim();
        String name = data[1];
        LocalDate date = LocalDate.parse(data[2]);
        TransactionType type = TransactionType.valueOf(data[3].toUpperCase());
        String category = data[4];
        double amount = Double.parseDouble(data[5]);
      
        return new Transaction(id, name, date, type, category, amount );
    }
}