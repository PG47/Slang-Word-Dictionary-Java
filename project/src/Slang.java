import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Slang {
    private TreeMap<String, List<String>> map = new TreeMap<>();
    private static Slang obj = new Slang();
    private int size_map;
    private String FILE="slangword.txt";
    private String ORIGINAL_FILE = "original-slangword.txt";

    private Slang() {
        try {
            ReadFile(FILE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    void ReadFile(String file_name) throws Exception {
        map.clear();
        String slag=null;
        Scanner scan = new Scanner(new File(file_name));
        scan.useDelimiter("`");
        scan.next();
        String temp = scan.next();
        String[] part = temp.split("\n");
        size_map=0;
        while(scan.hasNext()) {
            List<String> meaning = new ArrayList<String>();
            slag = part[1].trim();
            temp = scan.next();
            part = temp.split("\n");
            if (map.containsKey(slag)) {
                meaning = map.get(slag);
            }

            //If slag have more than 1 mean
            if (part[0].contains("|")) {
                System.out.println(part[0]);
                String[] d = (part[0]).split("\\|");
                for (int ii = 0; ii < d.length; ii++)
                    System.out.println(d[ii]);
                Collections.addAll(meaning, d);
                size_map += d.length - 1;
            } else {
                meaning.add(part[0]);
            }
            map.put(slag, meaning);
            size_map++;
        }
        scan.close();
    }
}
