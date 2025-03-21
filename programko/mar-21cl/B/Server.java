import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;

public class Server {
    static final int port = 8005;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ServerSocket ss = new ServerSocket(port);
        while(true) {
            if(System.in.available() > 0) {
                if(sc.nextLine().equals("END")) break;
            }

            System.out.println(String.format("Listening at %d", port));
            Socket s = ss.accept();
            System.out.println("Accepted");
            BufferedReader rd = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            try {
                String str = rd.readLine();
                wr.write(str);
                wr.write(str);
                wr.write("\r\n");
                wr.flush();
            }
            catch(Exception e) {
                System.out.println(String.format("Error: %s", e.getMessage()));
            }
            finally {
                s.close();
                System.out.println("Closed");
            }
        }

        sc.close();
        ss.close();
    }
}