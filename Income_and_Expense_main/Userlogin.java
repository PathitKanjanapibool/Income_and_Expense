package Income_and_Expense_main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Userlogin {

    private final String filePath ;

    public Userlogin(String filePath) {
        this.filePath = filePath;
    }

     private String[] findUser(String username) {
        File file = new File(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                //if (line.trim().isEmpty()) continue;
                String[] user = line.split(",");
                if (user[0].equals(username)) {
                    return user;
                }
            }
        } catch (IOException e) {
            System.err.println("อ่านไฟล์ไม่สำเร็จ: " + e.getMessage());
        }
        return null;
    }

    public String SignIn(UserAcc id) {

         String username =id.getUser();
         String password =id.getPW();

        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("ไม่มีuserหรือpassword");
        }
        if (username.contains(",")) {
           throw new IllegalArgumentException("มีเครื่องหมาย ,");
        }
        if (findUser(username) != null) {
            throw new IllegalArgumentException("มีuserอยู่แล้ว โปรดlogin");
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
            String line = String.join(",",
                    id.getUser(),
                    id.getPW()
            );
            bw.write(line);
            bw.newLine();
            bw.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }

        String currentUser = id.getUser();
            return makeUserFlie.getUserFileName(currentUser);
   }

   public String logIn(UserAcc id) {

         String username =id.getUser();
         String password =id.getPW();

        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("ไม่มีuserหรือpassword");
        }
        if (username.contains(",")) {
           throw new IllegalArgumentException("มีเครื่องหมาย ,");
        }
        if (findUser(username) == null) {
            throw new IllegalArgumentException("ไม่มีไฟล์อยู่");
        }

        String currentUser = id.getUser();
       return makeUserFlie.getUserFileName(currentUser);
        

   }


    public Map<String, UserAcc> loadToMap(String filePath) {
        Map<String, UserAcc> map = new HashMap<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return map;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            //boolean isHeader = true;

             while ((line = br.readLine()) != null) {
            //     if (isHeader) { // ข้ามบรรทัด Header
            //         isHeader = false;
            //         continue;
            //     }
                if (!line.trim().isEmpty()) {
                    UserAcc t = UserAcc.fromCsvRow(line);
                    map.put(t.getUser(), t); // ใส่ลง HashMap โดยใช้ ID เป็น Key
                }
            }
        } catch (IOException e) {
           throw new IllegalArgumentException("not have user");
        }

        return map;
    }

     public void saveMapToCsv( Map<String, UserAcc> map) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) { // false = เขียนทับ

            // นำ Value ทั้งหมดใน Map มาเขียนลง CSV
            for (UserAcc t : map.values()) {
                bw.write(t.toCsvRow());
                bw.newLine();
            }
            
        } catch (IOException e) {
            System.err.println(" บันทึกไฟล์ไม่สำเร็จ: " + e.getMessage());
        }
    }

    public void updatePW( String user, UserAcc pw) {
        // Step 1: โหลด CSV เข้า HashMap
        Map<String, UserAcc> map = loadToMap(filePath);
        // System.out.println("targetId ที่ค้นหา: [" + targetId + "]");
        // System.out.println("keys ที่มีอยู่ใน map: " + map.keySet());
        // Step 2: เช็กว่ามี ID นี้ใน Map ไหม ถ้ามีให้ Put ค่าใหม่เข้าไปทับ Key เดิม
        if (!map.containsKey(user)) {
            throw new IllegalArgumentException("not found user");
        }

        map.put(user,pw); // ทับค่าเดิมใน Map
            
        // Step 3: แปลง HashMap ทั้งหมดกลับลง CSV
        saveMapToCsv( map);
    }
}
