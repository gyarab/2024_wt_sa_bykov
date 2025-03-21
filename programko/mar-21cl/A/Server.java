import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static boolean isPrime6k1(BigInteger num) {
        if(num.equals(BigInteger.TWO) || num.equals(BigInteger.TWO.add(BigInteger.ONE))) return true;
        if(num.equals(BigInteger.ONE)) return false;

        //all primes are 6k+-1
        if(!(
            (num.subtract(BigInteger.ONE).mod(BigInteger.valueOf(6)).compareTo(BigInteger.ZERO) == 0) ||
            (num.add(BigInteger.ONE).mod(BigInteger.valueOf(6)).compareTo(BigInteger.ZERO) == 0)
        )) {
            return false;
        }

        for(BigInteger i = BigInteger.TWO; i.compareTo(num.sqrt()) < 0; i = i.add(BigInteger.ONE))
            if(num.mod(i).equals(BigInteger.ZERO)) return false;
        return true;
    }

    /*
    //old
    public static boolean isPrime(BigInteger num) {
        if(num.equals(BigInteger.TWO) || num.equals(BigInteger.TWO.add(BigInteger.ONE))) return true;
        if(num.equals(BigInteger.ONE)) return false;
        for(BigInteger i = BigInteger.TWO; i.compareTo(num.sqrt()) < 0; i = i.add(BigInteger.ONE))
            if(num.mod(i).equals(BigInteger.ZERO)) return false;
        return true;
    }
    */

    public static BigInteger nextPrime(BigInteger num) {
        if(num.equals(BigInteger.ONE)) return BigInteger.TWO; //the ONE even prime
        BigInteger n = num.add(BigInteger.ONE);
        //even - increment
        if(n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) n = n.add(BigInteger.ONE);
        while(!isPrime6k1(n)) n = n.add(BigInteger.TWO); //go only by odd numbers
        return n;
    }
    public static void main(String[] args) throws Exception {
        /*
        Scanner s = new Scanner(System.in);
        BigInteger num = s.nextBigInteger();
        s.close();
        System.out.println(nextPrime(num));
        */

        Scanner sc = new Scanner(System.in);
        ServerSocket ss = new ServerSocket(8005);
        while(true) {
            if(System.in.available() > 0) {
                if(sc.nextLine().equals("END")) break;
            }

            System.out.println("Listening at 8005");
            Socket s = ss.accept();
            System.out.println("Accepted");
            BufferedReader rd = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            try {
                BigInteger bi = BigInteger.valueOf(Long.parseLong(rd.readLine()));
                System.out.println(String.format("Got %d", bi));
                bi = nextPrime(bi);

                wr.write(bi.toString(10));
                wr.write("\r\n");
                wr.flush();

                System.out.println(String.format("Returning %d", bi));
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