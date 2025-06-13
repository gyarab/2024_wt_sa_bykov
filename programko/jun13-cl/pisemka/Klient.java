package pisemka;

import java.net.*;

public class Klient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 9999);
            System.out.println("pOnG");
            System.out.flush();  
            s.close();
            Thread.sleep(2000);
            s = new Socket("127.0.0.1", 9998);
            System.out.println("PoNg");
            System.out.flush();  
            s.close();
        } catch(Exception e) {
            System.out.println("PONG");
            System.out.flush();
        }
    }
}
