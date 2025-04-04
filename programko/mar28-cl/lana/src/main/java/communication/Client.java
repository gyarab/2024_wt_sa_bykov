package communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final String ip = "192.168.81.19";
    public static final int port = 10000;
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        while(true) {
            String bi = scan.nextLine();
            
            if(bi.isEmpty()) break;

            try {
                Socket sc = new Socket(ip, port);
                System.out.println("Connected");
        
                BufferedReader rd = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));

                wr.write(bi);
                wr.write("\r\n");
                wr.flush();

                String s = rd.readLine();
                //if(s == null) break;
                System.out.println(s);

                sc.close();
            }
            catch(Exception e) {
                System.out.println(String.format("Error: %s", e.getMessage()));
            }
        }
        scan.close();
    }
}
