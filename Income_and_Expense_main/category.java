package Income_and_Expense_main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class category {

    private final String filePath;

    public category (String filePath) {
        this.filePath = filePath;
    }

      public void saveToCsv(String category) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
            
            bw.write(category);

            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

   }

   public void ReadCat (){

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String s;
            while ((s=br.readLine()) != null) {
                System.out.println(s);
            }
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
