package System;
import GUI.*;
import java.time.LocalDate;

public class main {

    public static void main(String[] args) {

        System.setProperty("sun.java2d.uiScale", "0.85");
        App app = new App();

        // สมมติชื่อผู้ใช้ที่กำลังล็อกอินหรือใช้งานอยู่
        String currentUser = "john_doe";

        // 1. ดึงชื่อไฟล์ของ User คนนี้
        String userFile = UserService.getUserFileName(currentUser); // ได้ "data_john_doe.csv"

        Transaction t1 = new Transaction("Book", LocalDate.now(), TransactionType.OUTCOME, "Food", 15.0);
        //Name.saveToCsv(userFile, t1);
        //System.out.println(t1.getId());

        //Name.editCsv(userFile, t1);
      Name.updateTransaction(userFile, "8f1b5242-e97d-421a-9713-d20392437b56",  t1);

        //Name.deleteByIndex("data.csv",1);
    }
    
}
