package Income_and_Expense_main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class calculate {

    private final String filePath;
    
    public calculate(String filePath) {
        this.filePath = filePath;
    }


    public double totalIncome(List<Transaction> transactions) {
        double total = 0;
        for (Transaction t : transactions) {
            // ใช้ == เปรียบเทียบกับ Enum 
            if (t.getType() == TransactionType.INCOME) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double totalOutcome(List<Transaction> transactions) {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.OUTCOME) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double totalMoney(List<Transaction> transactions) {
        return totalIncome(transactions) - totalOutcome(transactions);
    }

    public Map<String, Double> getTotalCat() {
    Map<String, Double> categoryTotals = new HashMap<>();
    Map<String, Transaction> transactions = loadToMap(filePath);
    double sum = 0.0;

    for (Transaction t : transactions.values()) {

        String cat = t.getCategory();
        double amount = t.getAmount();
        sum += amount;


        // เอาค่าเดิมมาบวกเพิ่ม (ถ้ายังไม่มีจะเป็น 0.0)
        categoryTotals.put(cat, sum);
    }

    return categoryTotals;
}

 public Map<String, Transaction> loadToMap(String filePath) {
        Map<String, Transaction> map = new HashMap<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return map;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            //boolean isHeader = true;

             while ((line = br.readLine()) != null) {
            
                if (!line.trim().isEmpty()) {
                    Transaction t = Transaction.fromCsvRow(line);
                    
                    if (t != null) {
                    map.put(t.getId(), t);  }// ใส่ลง HashMap โดยใช้ ID เป็น Key
                }
            }
        } catch (IOException e) {
            System.err.println("อ่านไฟล์ไม่สำเร็จ: " + e.getMessage());
        }

        return map;
    }

    public List<String> loadCat(String filePath) {
    List<String> categories = new ArrayList<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            // เช็คว่าไม่ใช่บรรทัดว่าง และไม่ใช่ค่าซ้ำ
            if (!line.isEmpty() && !categories.contains(line)) {
                categories.add(line);
            }
        }
    } catch (IOException e) {
        System.err.println("ไม่สามารถอ่านไฟล์หมวดหมู่ได้: " + e.getMessage());
    }
    
    return categories;
}

}