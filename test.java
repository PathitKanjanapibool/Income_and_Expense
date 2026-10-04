import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * test
 */
public class test {
    private  static int pass = 0;
    private  static int fail = 0;

    private static void check(String name, boolean condition){
        if (condition) {
            pass++;
            System.out.println("[PASS] "+name);
        }else{
            fail++;
            System.out.println("[Fail] "+name);
        }
    }
    public static void main(String[] args){
        //Test assert ว่าเปิดอยู่มั้ย
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("assertion disabled");
        }
        System.out.println("=== test start ===\n");

        //พื้นที่เรียกmethodที่ใช่เทสต่างๆ
        TestGet();
        TestCalculate();
        TestUserService();
        TestCsvRowParsing();
        TestNameCsvOperations();


        //สรุปผล
        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + pass);
        System.out.println("Failed: " + fail);
        System.out.println("Total: "+(pass+fail));
        System.out.println(fail ==0? "All tests pass" : "Some test fail");
        
        //หยุดทำงานเมื่อfail >0
        if (fail > 0 ) {
            System.exit(1);
        }
    }
    /* private static void test(){
        check("test", true);
    } */

    private static void TestGet(){
        System.out.println("---- Test Get ----");
        Transaction t1 = new Transaction("dinner",LocalDate.of(2026, 6, 7), 
            TransactionType.OUTCOME, "ข้าว", 150.50, "shrimp");
        LocalDate testdate = LocalDate.of(2026, 6, 7);
        check("name = dinner", t1.getname().equals("dinner"));
        check("date = 2026/6/7", t1.getDate().equals(testdate));
        check("type = outcome", t1.getType() == TransactionType.OUTCOME);
        check("category = ข้าว", t1.getCategory().equals("ข้าว"));
        check("amount = 150.50", t1.getAmount() == 150.50);
        check("description = shrimp", t1.getDescription().equals("shrimp"));
        //check("total_amount = 250.00", t1.total_amount() == 250.00);
        System.out.println();
    }

    private static void TestCalculate(){
        System.out.println("---- Test Calculate ----");
        calculate calc = new calculate();
        Transaction t1 = new Transaction("เงินเดือน",LocalDate.now(), TransactionType.INCOME, "Salary", 1000.00, "Bonus");
        Transaction t2 = new Transaction("ค่าข้าว", LocalDate.now(), TransactionType.OUTCOME, "Food", 200.00, "Lunch");
        Transaction t3 = new Transaction("ค่ารถ", LocalDate.now(), TransactionType.OUTCOME, "Transport", 50.0, "BTS");

        List<Transaction> list = List.of(t1,t2,t3);

        check("TotalIncome = 1000.00", calc.totalIncome(list) == 1000.0);
        check("TotalOutcome == 250.00", calc.totalOutcome(list) == 250.0);
        check("TotalMoney == 750.00", calc.totalMoney(list) == 750.0);
        System.out.println();
    }

    private static void TestUserService(){
        System.out.println("---- Test User Service ----");
        String filename = UserService.getUserFileName("Grape eiei");
        check("filename clean format",filename.equals("data_grape_eiei.csv"));
        System.out.println();
    }

    private static void TestCsvRowParsing(){
        System.out.println("---- test CSV Serialization/Deserialization ----");
        Transaction original = new Transaction("Fixed_ID_123", "Shabu", LocalDate.of(2026, 9, 24), TransactionType.OUTCOME, "Food", 399.0, "Buffet");

        String CsvRow = original.toCsvRow();
        Transaction parsed = Transaction.fromCsvRow(CsvRow);

        check("parsed ID matches", parsed.getId().equals("Fixed_ID_123"));
        check("parsed name matches", parsed.getname().equals("Shabu"));
        check("parsed Type matches", parsed.getType() == TransactionType.OUTCOME);
        check("parsed Amount matches", parsed.getAmount() == 399.0);
        System.out.println();
    }

    private static void TestNameCsvOperations(){
        System.out.println("---- Test Name CSV Operation ----");
        String TestFile = "Grape_dummy_data.csv";

        File F = new File(TestFile);
        if (F.exists()) {F.delete();}

        Transaction t1 = new Transaction("Test_ID_01", "ขนม", LocalDate.now(), TransactionType.OUTCOME, "Snack", 40.0, "Lays");

        Map<String,Transaction> map = Name.loadToMap(TestFile);
        map.put(t1.getId(), t1);
        Name.saveMapToCsv(TestFile, map);

        Map<String,Transaction> loadedmap = Name.loadToMap(TestFile);
        check("Map Contains saved key", loadedmap.containsKey("Test_ID_01"));
        if (loadedmap.containsKey("Test_ID_01")) {
            check("loadedmap Amount is 40.0", loadedmap.get("Test_ID_01").getAmount() == 40.0);
        }
        if (F.exists()) {F.delete();}
    }
    
}
