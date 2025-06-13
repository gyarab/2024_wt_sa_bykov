package pisemka;

import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket sping = new ServerSocket(9998);
            ServerSocket spong = new ServerSocket(9999);

            sping.accept();
            System.out.println("pInG");
            System.out.flush();
            sping.close();

            spong.accept();
            System.out.println("PiNg");
            System.out.flush();
            spong.close();
        } catch(Exception e) {
            System.out.println("PING ERROR");
            System.out.flush();
        }
    }
}
