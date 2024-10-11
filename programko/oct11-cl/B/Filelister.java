package B;
import java.io.*;

public class Filelister {
    public static int getChildren(File root) {
        int amountOfFiles = 0;

        if(root.listFiles() == null) { return 0; }
        for(File name : root.listFiles()) {
            if(name.isFile()) System.out.println(name.getAbsolutePath());
            else amountOfFiles += getChildren(name);
        }

        return amountOfFiles;
    }

    public static void main(String[] args) {
        getChildren(new File("Z:/"));
    }
}
