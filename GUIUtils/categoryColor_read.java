package GUIUtils;
import java.awt.Color;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class categoryColor_read {
    private Map<String, Color> map = new HashMap<>();
    Random rand;
    private final String colorpath;

    public categoryColor_read(String colorpath){
        this.colorpath = colorpath;
        rand = new Random();
        Read();
    }

    public void Read (){

        try(BufferedReader br = new BufferedReader(new FileReader(colorpath,StandardCharsets.UTF_8))) {
            String s;
            while ((s = br.readLine()) != null) {
                String[] data = s.split(",");
                String name = data[0];
                int r = Integer.parseInt(data[1]); 
                int g = Integer.parseInt(data[2]);
                int b = Integer.parseInt(data[3]);
                map.put(name, new Color(r,g,b));
            }
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void saveToCsv() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(colorpath,StandardCharsets.UTF_8,false));
                for (Map.Entry<String, Color> e : map.entrySet()) {
                String name = e.getKey();     // "อาหาร"
                Color c = e.getValue();       // สีของอาหาร
                bw.write(name+","+c.getRed()+","+c.getGreen()+","+c.getBlue());
                bw.newLine();
            }
                bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
   }

    public Color getColor(String category) {
    if (map.containsKey(category)) {
        return map.get(category);
    }

    Color c;
    do {
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        c = new Color(r, g, b);
    } while (map.containsValue(c));

    map.put(category, c);
    saveToCsv();

    return c;
    }
}
