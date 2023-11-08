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

import java.text.SimpleDateFormat;
import java.util.Date;

public class Slang {
    private TreeMap<String, List<String>> map = new TreeMap<>();
    private static Slang obj = new Slang();
    private int size_map;
    private String FILE="slang.txt";
    private String ORIGINAL_FILE = "original-slang.txt";

    private String HISTORY_FILE = "searching-history.txt";

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

    void SaveFile(String file_name) {
        try {
            PrintWriter pw = new PrintWriter(new File(file_name));
            StringBuilder sb = new StringBuilder();

            sb.append("Slag`Meaning\n");
            String s[][] = new String[map.size()][3];
            Set<String> keySet = map.keySet();
            Object[] keyArray = keySet.toArray();
            for (int i = 0; i < map.size(); i++) {
                Integer in = i + 1;
                s[i][0] = in.toString();
                s[i][1] = (String) keyArray[i];
                List<String> meaning = map.get(keyArray[i]);
                sb.append(s[i][1] + "`" + meaning.get(0));
                for (int j = 1; j < meaning.size(); j++) {
                    sb.append("|" + meaning.get(j));
                }
                sb.append("\n");
            }
            pw.write(sb.toString());
            pw.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String[][] Get_Data() {
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

    public void  Save_History(String slang, String definition)
    throws  Exception{
        File f = new File(HISTORY_FILE);
        FileWriter fr = new FileWriter(f,true);

        //Save the date you search it
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        String date = dateFormat.format(currentDate);
        fr.write(date + "`" + slang + "`" + definition + "\n");
        fr.close();
    }

    public String[][] Read_History() {
        List<String> dates = new ArrayList<>();
        List<String> slangs = new ArrayList<>();
        List<String> definitions = new ArrayList<>();

        try {
            Scanner scan = new Scanner(new File(HISTORY_FILE));
            scan.useDelimiter("`");
            String date = scan.next();
            String temp = scan.next();
            String[] parts = scan.next().split("\n");
            dates.add(date);
            slangs.add(temp);
            definitions.add(parts[0]);
            while (scan.hasNext()) {
                date =parts[1];
                temp = scan.next();
                parts = scan.next().split("\n");
                dates.add(date);
                slangs.add(temp);
                definitions.add(parts[0]);
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int size = slangs.size();
        String result[][] = new String[size][3];
        for(int i=0;i<size;i++) {
            result[size-i-1][0] = dates.get(i);
            result[size-i-1][1] = slangs.get(i);
            result[size-i-1][2] = definitions.get(i);
        }
        return  result;
    }

    public int Clear_History() {
        File f = new File(HISTORY_FILE);
        if(f.exists()) {
            if(f.delete()) {
                return 0;
            } else return 1;
        } else return 2;
    }

    public boolean Check_Slang(String key) {
        for (String keyIro : map.keySet()) {
            if (keyIro.equals(key))
                return true;
        }
        return false;
    }

    public  void Delete(String slang, String defin) {
        List<String> defin_list = map.get(slang);
        int index = defin_list.indexOf(defin);
        if(defin_list.size() == 1) {
            map.remove(slang);
        } else {
            defin_list.remove(index);
            map.put(slang,defin_list);
        }
        size_map--;
        this.SaveFile(FILE);
    }

    public void Add_New(String slang, String definition) {
        List<String> defin_list = new ArrayList<>();
        defin_list.add(definition);
        size_map++;
        map.put(slang,defin_list);
        this.SaveFile(FILE);
    }

    public void Overwrite(String slang, String definition) {
        List<String> defin_list = map.get(slang);
        defin_list.set(0,definition);
        map.put(slang,defin_list);
        this.SaveFile(FILE);
    }

    public void Duplicate(String slang, String definition) {
        List<String> defin_list = map.get(slang);
        defin_list.add(definition);
        size_map++;
        map.put(slang,defin_list);
        this.SaveFile(FILE);
    }

    public void Reset() {
        try {
            ReadFile(ORIGINAL_FILE);
            this.SaveFile(FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] Random() {
        int random_number = Random_Num(0,map.size()-1);
        String random_slang[] = new String[2];
        int index = 0;
        for (String slang:map.keySet()) {
            if(index==random_number) {
                random_slang[0] = slang;
                random_slang[1] = map.get(slang).get(0);
                return  random_slang;
            }
            index++;
        }
        return null;
    }

    public static int Random_Num(int minimum, int maximum) {
        return minimum + (int) (Math.random() * (maximum - minimum + 1));
    }

    public String[] Quiz(int type) {
        String ques[] = new String[6];
        if(type==0) { //Slang quiz
            String[] slang = this.Random();
            ques[0]=slang[0];
            ques[1]=slang[1];
            int rand_pos = Random_Num(2,5);
            ques[rand_pos] = ques[1];
            for(int i=2;i<=5;i++) {
                if(i!=rand_pos) {
                    String[] wrong_ans = this.Random();
                    while(wrong_ans[0] == ques[0]) {
                        wrong_ans = this.Random();
                    }
                    ques[i]=wrong_ans[1];
                }
            }
        }
        if(type==1) { //Definition quizz
            String[] slang = this.Random();
            ques[0]=slang[1];
            ques[1]=slang[0];
            int rand_pos = Random_Num(2,5);
            ques[rand_pos] = ques[1];
            for(int i=2;i<=5;i++) {
                if(i!=rand_pos) {
                    String[] wrong_ans = this.Random();
                    while(wrong_ans[0] == ques[1]) {
                        wrong_ans = this.Random();
                    }
                    ques[i]=wrong_ans[0];
                }
            }
        }
        return  ques;
    }
}
