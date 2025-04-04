import java.net.ServerSocket;
import java.util.ArrayList;

public class Socketer {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 1; i < 65535; i++) {
            try {
                ServerSocket ss = new ServerSocket(i);
                ss.close();
            }
            catch(Exception e) {
                a.add(i);
            }
        }
        
        System.out.println(String.format("Closed ports (total %d): ", a.size()));
        for(int i : a) System.out.print(String.format("%d, ", i));
        System.out.println("Others are open.");
    }
}