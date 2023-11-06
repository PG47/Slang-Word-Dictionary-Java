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
    private String FILE="slang.txt";
    private String ORIGINAL_FILE = "original-slang.txt";

    private Slang() {
        try {
            ReadFile(FILE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Slang getInstance() {
        if (obj == null) {
            synchronized (Slang.class) {
                if (obj == null) {
                    obj = new Slang();
                }
            }
        }
        return obj;
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
                System.out.println(part[0]);
                meaning.add(part[0]);
            }
            map.put(slag, meaning);
            size_map++;
        }
        scan.close();
    }

    public String[][] get_Data() {
        String list[][] = new String[size_map][3];
        Set<String> slang_list_set = map.keySet();
        Object[] slang_list = slang_list_set.toArray();
        int index =0;
        int i=0;
        while(i < size_map) {
            list[i][0]= String.valueOf(i);
            list[i][1]= (String) slang_list[index];
            List<String> meanings = map.get(slang_list[index]);
            list[i][2]= meanings.get(0);

            //if a slang word has more than 1 mean
            for(int j=1;j<meanings.size(); j++) {
                i++;
                list[i][0] = String.valueOf(i);
                list[i][1] = (String) slang_list[index];
                list[i][2] = meanings.get(j);
            }
            index++;
            i++;
        }
        return list;
    }

    public String[][] Search_by_Slang(String key) {
        List<String> search_result = map.get(key);
        if(search_result == null) {
            return null;
        }
        int size = search_result.size();
        String list[][] = new String[size][2];
        for(int i=0;i<size;i++) {
            list[i][0]=key;
            list[i][1]=search_result.get(i);
        }
        return list;
    }

    public String[][] Search_by_Definition(String key) {
        List<String> key_list = new ArrayList<>();
        List<String> defin_list = new ArrayList<>();
        for(Entry<String, List<String>> entry:map.entrySet()) {
            List<String> definitions = entry.getValue();
            for(int i=0;i<definitions.size();i++) {
                if (definitions.get(i).toLowerCase().contains(key.toLowerCase())) {
                    key_list.add(entry.getKey());
                    defin_list.add(definitions.get(i));
                }
            }
        }
        //rather all the slang found into a result list
        int size = key_list.size();
        String result[][] = new String[size][2];

        for (int i=0;i<size; i++) {
            result[i][0]=key_list.get(i);
            result[i][1]=defin_list.get(i);
        }
        return result;
    }
}
