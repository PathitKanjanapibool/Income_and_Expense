package Income_and_Expense_main;

import java.io.File;

public class makeUserFile {

    // เมธอดสำหรับสร้างชื่อไฟล์ CSV ตามชื่อผู้ใช้
    public static String getUserFileName(UserAcc username) {
        // ตัดช่องว่างและแปลงเป็นตัวพิมพ์เล็กเพื่อความปลอดภัยของชื่อไฟล์
        String cleanUsername = username.getUser().trim().toLowerCase().replaceAll("\\s+", "_");
        
        // เช่น ถ้าส่ง "John Doe" จะได้ชื่อไฟล์เป็น "user/john_doe/data_john_doe.csv"
        return "users/" + cleanUsername + "/data_" + cleanUsername + ".csv";
    }
    public static String getUserFileCat(UserAcc username) {
        // ตัดช่องว่างและแปลงเป็นตัวพิมพ์เล็กเพื่อความปลอดภัยของชื่อไฟล์
        String cleanUsername = username.getUser().trim().toLowerCase().replaceAll("\\s+", "_");
        
        // เช่น ถ้าส่ง "John Doe" จะได้ชื่อไฟล์เป็น "user/john_doe/Cat_john_doe.csv"
        return "users/" + cleanUsername + "/Cat_" + cleanUsername + ".csv";
    }

    public static String getUserFileCatColor(UserAcc username) {
        // ตัดช่องว่างและแปลงเป็นตัวพิมพ์เล็กเพื่อความปลอดภัยของชื่อไฟล์
        String cleanUsername = username.getUser().trim().toLowerCase().replaceAll("\\s+", "_");
        
        // เช่น ถ้าส่ง "John Doe" จะได้ชื่อไฟล์เป็น "user/john_doe/Cat_Color_john_doe.csv"
        return "users/" + cleanUsername + "/Cat_Color_" + cleanUsername + ".csv";
    }
}
