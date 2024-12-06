import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        File f = new File("jozin-z-bazin-pl.txt");
        FileReader fr = new FileReader(f);
        
        File fo = new File("jozin-z-bazin-pl-out.txt");
        FileWriter fw = new FileWriter(fo);
    
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> letters = new HashMap<Character, Integer>();
        while(true) {
            int ctemp = fr.read();
            if(ctemp == -1) { break; }
            char c = (char)ctemp;
            sb.append(c);
            fw.write(c);
            c = Character.toLowerCase(c);
            if(Character.isWhitespace(c)) { continue; }
            if(letters.containsKey(c)) {
                letters.put(c, letters.get(c)+1);
            }
            else {
                letters.put(c, 0);
            }
        }
        String str = sb.toString();
        System.out.println(str);
        System.out.println(str.length());
        System.out.println(letters);

        int valueMax = 0;
        char key = 0;
        for(Character k : letters.keySet()) {
            if(letters.get(k) > valueMax) {
                key = k;
                valueMax = letters.get(k);
            }
        }
        System.out.println(String.format("Key %c --- amount %d", key, valueMax));
        
        fr.close();
        fw.close();

        //read vraci int - 4 byty je pismenko
    }
}