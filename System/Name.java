package System;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Name {

   public static void saveToCsv(String filePath, Transaction transactions) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
            String line = String.join(",",
                    transactions.getId(),
                    transactions.getname(),
                    transactions.getDate().toString(),
                    transactions.getType().toString(),
                    transactions.getCategory(),
                    String.valueOf(transactions.getAmount())
            );
            bw.write(line);

            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
   }

    public static void Read (String filePath){

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String s;
            while ((s=br.readLine()) != null) {
                System.out.println(s);
            }
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteByIndex(String filePath, int indexToDelete) {
    List<String> lines = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String s;
            while ((s=br.readLine()) != null) {
                lines.add(s);
            }
        
        } catch (Exception e) {
            System.out.println(e);
        }

         if (indexToDelete < 0 || indexToDelete >= lines.size()) {
            throw new IllegalArgumentException("no index");
        }
        lines.remove(indexToDelete);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
        for (String line : lines) {
            bw.write(line);
            bw.newLine();
        }
    } catch (Exception e) {
        System.out.println(e);
        }
    }

        public static Map<String, Transaction> loadToMap(String filePath) {
        Map<String, Transaction> map = new HashMap<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return map;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;

             while ((line = br.readLine()) != null) {
            //     if (isHeader) { // ข้ามบรรทัด Header
            //         isHeader = false;
            //         continue;
            //     }
                if (!line.trim().isEmpty()) {
                    Transaction t = Transaction.fromCsvRow(line);
                    map.put(t.getId(), t); // ใส่ลง HashMap โดยใช้ ID เป็น Key
                }
            }
        } catch (IOException e) {
            System.err.println("อ่านไฟล์ไม่สำเร็จ: " + e.getMessage());
        }

        return map;
    }

    public static void saveMapToCsv(String filePath, Map<String, Transaction> map) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) { // false = เขียนทับ

            // นำ Value ทั้งหมดใน Map มาเขียนลง CSV
            for (Transaction t : map.values()) {
                bw.write(t.toCsvRow());
                bw.newLine();
            }
            
        } catch (IOException e) {
            System.err.println(" บันทึกไฟล์ไม่สำเร็จ: " + e.getMessage());
        }
    }

    public static void updateTransaction(String filePath, String targetId, Transaction transaction) {
        // Step 1: โหลด CSV เข้า HashMap
        Map<String, Transaction> map = loadToMap(filePath);
        System.out.println("targetId ที่ค้นหา: [" + targetId + "]");
        System.out.println("keys ที่มีอยู่ใน map: " + map.keySet());
        // Step 2: เช็กว่ามี ID นี้ใน Map ไหม ถ้ามีให้ Put ค่าใหม่เข้าไปทับ Key เดิม
        if (!map.containsKey(targetId)) {
            throw new IllegalArgumentException("not found ID");
        }

        map.put(targetId, transaction); // ทับค่าเดิมใน Map
            
        // Step 3: แปลง HashMap ทั้งหมดกลับลง CSV
        saveMapToCsv(filePath, map);
    }
}
