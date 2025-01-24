import java.math.BigInteger;
import java.util.*;

public class Main {
    //euclid's original
    public static int gcdOriginal(int a, int b) {
        int u = a;
        int w = b;
        
        while(u != w) {
            if(u > w) { u -= w; }
            else { w -= u; }
        }

        return u;
    }

    public static BigInteger gcdOriginalBigInt(BigInteger a, BigInteger b) {
        //BigInteger is stupid
        BigInteger u = BigInteger.ZERO;
        u = u.add(a);
        BigInteger w = BigInteger.ZERO;
        w = w.add(b);

        while(u.compareTo(w) == 0) {
            if(u.compareTo(w) > 0) { u.subtract(w); }
            else { w.subtract(u); }
        }
        return u;
    }

    public static BigInteger gcdMod(BigInteger a, BigInteger b) {
       BigInteger u = BigInteger.ZERO;
       u = u.add(a);
       BigInteger w = BigInteger.ZERO;
       w = w.add(b);

       System.out.println(String.format("a is %d, b is %d", u, w));

       while(true) {
          if(u.compareTo(w) < 0) {
            //swap
            BigInteger t = BigInteger.ZERO;
            t = t.add(w);
            w = BigInteger.ZERO;
            w = w.add(u);
            u = BigInteger.ZERO;
            u = u.add(t);
          }
          if(w.compareTo(BigInteger.ZERO) == 0) { break; }
          u = u.mod(w);

         System.out.println(String.format("a is %d, b is %d", u, w));
        }
       return u;
    }

    public static int sum(int a) {
        int t = a;
        int r = 0;
        do {
            r += t % 10;
            t /= 10;
        }
        while(t >= 1);
        return r;
    }

    //all prime numbers are 6k +/- 1 -> IMPLEMENT LATER

    public static boolean isPrimeEasy(long a) {
        if(a == 2 || a == 3) return true;
        if(a % 2 == 0 || a == 1) return false;

        long sqrta = (long)Math.sqrt(a);
        for(long i = 3; i <= sqrta; i += 2) {
            if(a % i == 0) return false;
        }
        return true;
    }

    //https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
    public static int Eratosthenes(int n) {
        ArrayList<Boolean> b = new ArrayList<Boolean>(Collections.nCopies(n, true));
        b.set(0, false); //0, 1
        b.set(1, false);
        
        int v = (int)Math.sqrt(n);
        for(int i = 2; i <= v; i++) {
            if(b.get(i)) {
                for(int j = i*i; j < n; j+=i) {
                    b.set(j, false);
                }
            }
        }

        int a = 0;
        for(int i = 0; i < n; i++) {
            if(b.get(i)) {
                //System.out.println(i);
                a++;
            }
        }

        return a;
    }

    //TODO add atkin

    public static void main(String[] args) {
        BigInteger n = BigInteger.ZERO;
        BigInteger m = BigInteger.ZERO;
        try {
            Scanner s = new Scanner(System.in);
            n = s.nextBigInteger();
            m = s.nextBigInteger();
            s.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        BigInteger shrt = gcdOriginalBigInt(m, n);
        System.out.println(String.format("RESULT - %d/%d", n.divide(shrt), m.divide(shrt)));
        BigInteger shrt2 = gcdMod(m, n);
        System.out.println(String.format("RESULT2 - %d/%d", n.divide(shrt2), m.divide(shrt2)));

        final int AMOUNT = 10_000_000;

        long startTime = System.nanoTime();
        int nAmount = Eratosthenes(AMOUNT);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("--");
        System.out.println(nAmount);
        System.out.println(String.format("%d m %d s %d ms", (duration / 1000) / 60, duration / 1000, duration));

        /*
        //perf measure https://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java

        final int AMOUNT = 1000;

        long startTime = System.nanoTime();
       // for(int i = 0; i < AMOUNT; i++) {
       //     sum(i);
       // }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println(String.format("sum %d ms", duration));

        startTime = System.nanoTime();
        for(int i = 0; i < 10; i++) {
            gcd(i, i);
        }
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;
        System.out.println(String.format("gcd %d ms", duration));
        */
    }
}