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

    private final String filePath;

    public Userlogin(String filePath) {
        this.filePath = filePath;
    }

    private String[] findUser(String username) {
        File file = new File(filePath);
        if (!file.exists()) return null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] user = line.split(",");
                if (user[0].trim().equals(username.trim())) {
                    return user;
                }
            }
        } catch (IOException e) {
            System.err.println("อ่านไฟล์ไม่สำเร็จ: " + e.getMessage());
        }
        return null;
    }

    public void SignIn(UserAcc id) {
        String username = id.getUser();
        String password = id.getPW();

        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("กรุณากรอก Username และ Password ให้ครบถ้วน");
        }
        if (username.contains(",") || password.contains(",")) {
            throw new IllegalArgumentException("ห้ามใช้เครื่องหมาย , ใน Username หรือ Password");
        }
        if (findUser(username) != null) {
            throw new IllegalArgumentException("มี Username นี้ในระบบอยู่แล้ว โปรดใช้ชื่ออื่นหรือทำการ Login");
        }

        // บันทึก UserAcc ลงใน data.csv
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(id.getUser() + "," + id.getPW());
            bw.newLine();
        } catch (Exception e) {
            System.err.println("บันทึกข้อมูลไม่สำเร็จ: " + e.getMessage());
        }

        // สร้างไฟล์ข้อมูลรายการแบบไม่มีบรรทัดว่างตั้งต้น
        File file_user = new File(makeUserFlie.getUserFileName(id));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_user))) {
            // ไม่ต้องใส่ bw.newLine() หรือบรรทัดว่างใดๆ ทิ้งไว้
        } catch (IOException e) {
            System.err.println("สร้างไฟล์ User ไม่สำเร็จ: " + e.getMessage());
        }

        // สร้างไฟล์ Category แบบไม่มีบรรทัดว่างตั้งต้น
        File file_cat = new File(makeUserFlie.getUserFileCat(id));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_cat))) {
            // ไม่ต้องใส่ bw.newLine() หรือบรรทัดว่างใดๆ ทิ้งไว้
        } catch (IOException e) {
            System.err.println("สร้างไฟล์ Category ไม่สำเร็จ: " + e.getMessage());
        }
    }

    public void logIn(UserAcc id) {
        String username = id.getUser();
        String password = id.getPW();

        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("กรุณากรอก Username และ Password ให้ครบถ้วน");
        }
        if (username.contains(",") || password.contains(",")) {
            throw new IllegalArgumentException("ห้ามใช้เครื่องหมาย , ใน Username หรือ Password");
        }

        String[] foundUser = findUser(username);
        if (foundUser == null) {
            throw new IllegalArgumentException("ไม่พบ Username นี้ในระบบ");
        }

        String storedPassword = foundUser[1].trim();
        if (!storedPassword.equals(password)) {
            throw new IllegalArgumentException("Password ไม่ถูกต้อง");
        }

        // เช็คเฉพาะว่ามีไฟล์อยู่แล้วหรือยัง ถ้ายังไม่มีให้สร้างไฟล์เปล่าๆ ขึ้นมา (ห้ามสั่ง bw.newLine() เขียนบรรทัดว่างลงไป)
        File file_user = new File(makeUserFlie.getUserFileName(id));
        if (!file_user.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_user))) {
                // สร้างไฟล์เปล่า ไม่เขียนบรรทัดว่าง
            } catch (IOException e) {
                System.err.println("สร้างไฟล์ User ไม่สำเร็จ: " + e.getMessage());
            }
        }

        File file_cat = new File(makeUserFlie.getUserFileCat(id));
        if (!file_cat.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_cat))) {
                // สร้างไฟล์เปล่า ไม่เขียนบรรทัดว่าง
            } catch (IOException e) {
                System.err.println("สร้างไฟล์ Category ไม่สำเร็จ: " + e.getMessage());
            }
        }
    }

    public Map<String, UserAcc> loadToMap(String filePath) {
        Map<String, UserAcc> map = new HashMap<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return map;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    UserAcc t = UserAcc.fromCsvRow(line);
                    map.put(t.getUser(), t);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("ไม่สามารถดึงข้อมูล User ได้");
        }

        return map;
    }

    public void saveMapToCsv(Map<String, UserAcc> map) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            for (UserAcc t : map.values()) {
                bw.write(t.toCsvRow());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("บันทึกไฟล์ไม่สำเร็จ: " + e.getMessage());
        }
    }

    public void updatePW(String user, UserAcc pw) {
        Map<String, UserAcc> map = loadToMap(filePath);
        if (!map.containsKey(user)) {
            throw new IllegalArgumentException("ไม่พบ User นี้ในระบบ");
        }

        map.put(user, pw);
        saveMapToCsv(map);
    }
}