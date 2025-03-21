import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;
import java.util.Formatter.BigDecimalLayoutForm;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        while(true) {
            BigInteger bi = scan.nextBigInteger();
            
            if(bi.equals(BigInteger.ZERO)) break;

            //land 192.168.56.1 9999
            try {
                Socket sc = new Socket("192.168.56.1", 8005);
                System.out.println("Connected");
        
                BufferedReader rd = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));

                wr.write(bi.toString(10));
                wr.write("\r\n");
                wr.flush();

                String s = rd.readLine();
                if(s == null) break;
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
