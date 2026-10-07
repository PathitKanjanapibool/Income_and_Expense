package Income_and_Expense_main;
import java.time.LocalDate;
import java.util.List;

public class main {

    public static void main(String[] args) {
        // สมมติชื่อผู้ใช้ที่กำลังล็อกอินหรือใช้งานอยู่
        //String currentUser = "john_doe";

        // 1. ดึงชื่อไฟล์ของ User คนนี้
        //String userFile = makeUserFlie.getUserFileName(currentUser); // ได้ "data_john_doe.csv"

        Transaction t1 = new Transaction("9fefebd4-ed9a-4841-8af3-78c3dca5249d","Book", LocalDate.now(), TransactionType.OUTCOME, "Food", 15.0);
        UserAcc t2 = new UserAcc("guin", "p1234");
        //Name.saveToCsv(userFile, t1);
        //System.out.println(t1.getId());
       
        Userlogin n = new Userlogin("data.csv");
        //Name.editCsv(userFile, t1);
        
        // l.updateTransaction("9fefebd4-ed9a-4841-8af3-78c3dca5249d",  t1);
        //n.SignIn(t2);
        n.logIn(t2);
        Name l = new Name(n.logIn(t2));
        // l.saveToCsv(t1);
        // l.editCsv("9fefebd4-ed9a-4841-8af3-78c3dca5249d", t1);
        l.deleteByIndex("9fefebd4-ed9a-4841-8af3-78c3dca5249d");
        
        //n.logIn(t2);

        //Name.deleteByIndex("data.csv",1);
    }
    
}
