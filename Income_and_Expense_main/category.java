package Income_and_Expense_main;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class category {

    private final String filePath;
    private ArrayList<String> arr = new ArrayList<>();
    private ArrayList<String> temp = new ArrayList<>();
    public category (String filePath) {
        this.filePath = filePath;
        this.ReadCat();
    }
      public void saveToCsv(String category) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,StandardCharsets.UTF_8,true));
            bw.write(category);
            bw.newLine();
            arr.add(category);
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }

   }

   public void ReadCat (){

        try(BufferedReader br = new BufferedReader(new FileReader(filePath,StandardCharsets.UTF_8))) {
            String s;
            while ((s=br.readLine()) != null) {
                arr.add(s);
            }
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void deleteCategory(String name) {
        name = name.trim();
        if (!arr.remove(name)) return;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8,false))){
            for (String c : arr) {
                bw.write(c);
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<String> getCategory(){
        return new ArrayList<>(arr);
    }
    
}
