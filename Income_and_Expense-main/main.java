import java.time.LocalDate;

public class main {

    public static void main(String[] args) {
        // Transaction t1 = new Transaction("food", LocalDate.now(), TransactionType.OUTCOME, "Food", 150.0, "lunch");
        // Name.saveToCsv("data.csv", t1);

        // Name.Read();

        Name.deleteByIndex("data.csv",1);
    }
    
}
