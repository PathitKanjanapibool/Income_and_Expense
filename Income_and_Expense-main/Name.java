import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Name {

   public static void saveToCsv(String filePath, Transaction transactions) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data.csv",true));
            String line = String.join(",",
                    transactions.getname(),
                    transactions.getDate().toString(),
                    transactions.getType().toString(),
                    transactions.getCategory(),
                    String.valueOf(transactions.getAmount()),
                    transactions.getDescription()
            );
            bw.write(line);

            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
   }

    public static void Read(){

        try(BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
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

        try(BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
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

    
}
