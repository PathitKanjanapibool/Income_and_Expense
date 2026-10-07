package Income_and_Expense_main;
public class makeUserFlie {

    // เมธอดสำหรับสร้างชื่อไฟล์ CSV ตามชื่อผู้ใช้
    public static String getUserFileName(String username) {
        // ตัดช่องว่างและแปลงเป็นตัวพิมพ์เล็กเพื่อความปลอดภัยของชื่อไฟล์
        String cleanUsername = username.trim().toLowerCase().replaceAll("\\s+", "_");
        
        // เช่น ถ้าส่ง "John Doe" จะได้ชื่อไฟล์เป็น "data_john_doe.csv"
        return "data_" + cleanUsername + ".csv";
    }
}
