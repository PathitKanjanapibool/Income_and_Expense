package Income_and_Expense_main;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class main {

    public static void main(String[] args) {
        // สมมติชื่อผู้ใช้ที่กำลังล็อกอินหรือใช้งานอยู่
        //String currentUser = "john_doe";

        // 1. ดึงชื่อไฟล์ของ User คนนี้
        //String userFile = makeUserFlie.getUserFileName(currentUser); // ได้ "data_john_doe.csv"

        Transaction t1 = new Transaction("9fefebd4-ed9a-4841-8af3-78c3dca5249d","Book", LocalDate.now(), TransactionType.OUTCOME, "Food", 15.0);
        String g = t1.getCategory();
        UserAcc t2 = new UserAcc("guinei", "p1234");
        
        
        //Name.saveToCsv(userFile, t1);
        //System.out.println(t1.getId());
       
        Userlogin n = new Userlogin("data.csv");
        makeUserFlie make = new makeUserFlie();
        String m = makeUserFlie.getUserFileName(t2);
        String mc = makeUserFlie.getUserFileCat(t2);
        //Name.editCsv(userFile, t1);
        calculate k = new calculate(m);
        List<String> allCategories = k.loadCat(m);
        Map<String, Double> totalsMap = k.getTotalCat();
        
        // l.updateTransaction("9fefebd4-ed9a-4841-8af3-78c3dca5249d",  t1);
        //n.SignIn(t2);
        n.logIn(t2);
        Name l = new Name(m);
        category c = new category(mc);
        l.saveToCsv(t1);
        c.saveToCsv("food");
        // l.editCsv("9fefebd4-ed9a-4841-8af3-78c3dca5249d", t1);
        //l.deleteByIndex("9fefebd4-ed9a-4841-8af3-78c3dca5249d");
        
        //n.logIn(t2);

        //Name.deleteByIndex("data.csv",1);

    // List<Transaction> a = l.filter("transactions.csv", 2026, 10, null, null);

    // // แสดงรายการ
    // for (Transaction t : a) {
    //     System.out.println(t.toCsvRow());
    // }

    // //  รวมยอด
    // double total = a.stream().mapToDouble(Transaction::getAmount).sum();
    // System.out.println("รวม: " + total);

    for (String cat : allCategories) {
    double total = totalsMap.getOrDefault(cat, 0.0);
    System.out.println("หมวดหมู่: " + cat + " | ยอดรวม: ฿" + total);
}
}

    }
    

