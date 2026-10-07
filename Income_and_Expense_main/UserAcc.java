package Income_and_Expense_main;

public class UserAcc {
    private  String user;
    private  String pw;

    UserAcc(String user,String pw){
        this.user = user;
        this.pw = pw;
    }

    public String getUser(){ return this.user; }
    public String getPW(){ return  this.pw; }

     public String toCsvRow() {
        return user + "," + pw ;
    }

     public static UserAcc fromCsvRow(String csvRow) {
        String[] data = csvRow.split(",");
        String user = data[0].trim();
        String pw = data[1];
      
        return new UserAcc(user, pw );
    }

}
