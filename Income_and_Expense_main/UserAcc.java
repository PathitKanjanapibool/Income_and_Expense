package Income_and_Expense_main;

public class UserAcc {
    // Abstraction Function:
    //   AF(user, pw) = บัญชีผู้ใช้งานระบบชื่อ user และรหัสผ่าน pw
    // Representation Invariant (RI):
    //   - user ต้องไม่เป็น null และไม่ใช่สตริงว่าง หรือมีเครื่องหมาย ,
    //   - pw ต้องไม่เป็น null และไม่ใช่สตริงว่าง

    private  String user;
    private  String pw;

    public UserAcc(String user,String pw){
        if (user == null || user.trim().isEmpty()) {
            throw new IllegalArgumentException("user ไม่มีค่า");
        }
        if (pw == null || pw.isEmpty()) {
            throw new IllegalArgumentException("PassWord ไม่มีค่า");
        }
        if (user.contains(",")) {
            throw new IllegalArgumentException("user ไม่ควรมี ,");
        }
        this.user = user;
        this.pw = pw;

        checkrep();
    }

    public String getUser(){ return this.user; }
    public String getPW(){ return  this.pw; }

     public String toCsvRow() {
        return user + "," + pw ;
    }

     public static UserAcc fromCsvRow(String csvRow) {
        if (csvRow == null || csvRow.trim().isEmpty()) {return null;}
        String[] data = csvRow.split(",");
        String user = data[0].trim();
        String pw = data[1];
      
        return new UserAcc(user, pw );
    }

    private void checkrep(){
        assert user != null && !user.isEmpty() : "user ไม่มีค่า";
        assert pw != null && !pw.isEmpty() : "password ไม่มีค่า";
        assert !user.contains(",") : "user มีเครื่องหมาย ,";
    }

}
