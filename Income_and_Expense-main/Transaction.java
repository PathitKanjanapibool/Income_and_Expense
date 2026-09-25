import java.time.LocalDate;

public class Transaction {
    private String name;
    private LocalDate date;
    private TransactionType type;        // "INCOME" หรือ "EXPENSE"
    private String category;    // เช่น "Food", "Transport"
    private double amount;
    private String description;
    private double total_amount;

    // Constructor สำหรับสร้างรายการใหม่
    public Transaction(String name, LocalDate date, TransactionType type, String category, double amount, String description,double total_amount) {
        this.name = name; //ชื่อรายการ
        this.date = date; 
        this.type = type; 
        this.category = category; 
        this.amount = amount; 
        this.description = description; 
        this.total_amount = total_amount; 

    }

    // Getters
    public String getname() { return name; }
    public LocalDate getDate() { return date; }
    public TransactionType getType() { return type; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public double total_amount() {return total_amount;}

    // แปลง Object เป็นบรรทัด CSV
    public String toCsvRow() {
        return name + "," + date + "," + type + "," + category + "," + amount + "," + description;
    }

    // สร้าง Object จากบรรทัด CSV (Static Helper)
    public static Transaction fromCsvRow(String csvRow) {
        String[] data = csvRow.split(",");
        String name = data[0];
        LocalDate date = LocalDate.parse(data[1]);
        TransactionType type = TransactionType.valueOf(data[2].toUpperCase());
        String category = data[3];
        double amount = Double.parseDouble(data[4]);
        String description = data.length > 5 ? data[5] : "";
        double total_amount = Double.parseDouble(data[6]);
        return new Transaction(name, date, type, category, amount, description, total_amount);
    }
}
