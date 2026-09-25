import java.time.LocalDate;

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
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("assertion disabled");
        }
        System.out.println("=== test ===\n");

        //พื้นที่เรียกmethodที่ใช่เทสต่างๆ
        TestGet();


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
        Transaction t1 = new Transaction("dinner",LocalDate.of(2026, 6, 7), 
            TransactionType.OUTCOME, "ข้าว", 150.50, "shrimp", 250.00);
        LocalDate testdate = LocalDate.of(2026, 6, 7);
        check("name = dinner", t1.getname().equals("dinner"));
        check("date = 2026/6/7", t1.getDate().equals(testdate));
        check("type = outcome", t1.getType() == TransactionType.OUTCOME);
        check("category = shrimp", t1.getCategory().equals("ข้าว"));
        check("amount = 150.50", t1.getAmount() == 150.50);
        check("description = shrimp", t1.getDescription().equals("shrimp"));
        check("total_amount = 250.00", t1.getTotal_amount() == 250.00);
    }
    
}
