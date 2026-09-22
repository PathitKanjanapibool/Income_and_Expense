
/**
 * test
 */
public class test {
    private  static int pass = 0;
    private  static int fail = 0;

    private static void check(String name, boolean condition){
        if (condition) {
            pass++;
            System.out.println("PASS "+name);
        }else{
            fail++;
            System.out.println("Fail "+name);
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
    //test

    //test

    //test

    //test
    
}
