package Income_and_Expense_main;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

    // Abstraction Function (AF):
    //   AF(id, name, date, type, category, amount) = 
    //   รายการธุรกรรมทางการเงิน (รายรับหรือรายจ่าย) ของชิ้นหนึ่ง โดยมีรหัส ID เป็นตัวระบุตัวตน, 
    //   ชื่อรายการ name, เกิดขึ้นในวันที่ date, เป็นประเภท type (INCOME/OUTCOME), 
    //   จัดอยู่ในหมวดหมู่ category, และมีมูลค่าเป็นจำนวนเงิน amount
    //
    // Representation Invariant (RI):
    //   - id ต้องไม่เป็น null และไม่ใช่สตริงว่าง
    //   - name ต้องไม่เป็น null
    //   - date ต้องไม่เป็น null
    //   - type ต้องไม่เป็น null (ต้องเป็น INCOME หรือ OUTCOME เท่านั้น)
    //   - category ต้องไม่เป็น null และไม่ใช่สตริงว่าง
    //   - amount ต้องมีค่ามากกว่าหรือเท่ากับ 0 (amount >= 0)
public class Transaction {

    private String id;
    private String name;
    private LocalDate date;
    private TransactionType type;        // "INCOME" หรือ "EXPENSE"
    private String category;    // เช่น "Food", "Transport"
    private double amount;
    private double total_amount;

    private void checkrep(){
        assert id != null && !id.trim().isEmpty(): "ID ต้องมีค่า";
        assert name != null : "name ต้องไม่เป็น null";
        assert date != null : "date ต้องไม่เป็น null";
        assert type != null : "type ต้องไม่เป็น null";
        assert category != null && !category.trim().isEmpty() : "category มีค่า";
        assert amount >= 0 : "เงินต้องไม่ติดลบ";
    }

    // Constructor สำหรับสร้างรายการใหม่
    public Transaction(String name, LocalDate date, TransactionType type, String category, double amount) {

        if (name == null || date == null || type == null || category == null) {
            throw new IllegalArgumentException("ต้องมีข้อมูล");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("เงินต้องไม่ติดลบ");
        }

        this.id = UUID.randomUUID().toString();
        this.name = name; //ชื่อรายการ
        this.date = date;
        this.type = type; 
        this.category = category;
        this.amount = amount;

        checkrep();
    }

    public Transaction(String id, String name, LocalDate date, TransactionType type, String category, double amount) {

        if (id == null || name == null || date == null || type == null || category == null) {
            throw new IllegalArgumentException("ไม่มีข้อมูล");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("เงินต้องไม่เป็นลบ");
        }

        this.id = id;
        this.name = name; //ชื่อรายการ
        this.date = date;
        this.type = type; 
        this.category = category;
        this.amount = amount;

        checkrep();
    }

    // Getters
    public String getId() { return id; }
    public String getname() { return name; }
    public LocalDate getDate() { return date; }
    public TransactionType getType() { return type; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public int getYear() {return date.getYear();}
    public int getMonth() {return date.getMonthValue();}
    public int getDay() {return date.getDayOfMonth();}
    

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
    public static Transaction fromCsvRow(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        
        String[] data = line.split(",");
        if (data.length < 6) {
            return null; // คอลัมน์ไม่ครบให้ส่ง null
        }

        try {
            String id = data[0].trim();
            String name = data[1].trim();
            LocalDate date = LocalDate.parse(data[2].trim());
            TransactionType type = TransactionType.valueOf(data[3].trim());
            String category = data[4].trim();
            double amount = Double.parseDouble(data[5].trim());

            return new Transaction(id, name, date, type, category, amount);
        } catch (Exception e) {
            return null; // ถ้าเป็นบรรทัด Header หรือแปลงค่าไม่ผ่าน ให้ส่ง null
        }
    }
}

