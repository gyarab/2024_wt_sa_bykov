import java.util.*;

public class Main {
    /*
    public static int gcd(int a, int b) {
        int u = a;
        int w = b;
        
        while(u != w) {
            if(u > w) { u -= w; }
            else { w -= u; }
        }

        return u;
    }
    */

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
        final int AMOUNT = 10000000;

        long startTime = System.nanoTime();
        int n = Eratosthenes(AMOUNT);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("--");
        System.out.println(n);
        System.out.println(String.format("%d m %d s %d ms", (duration / 1000) / 60, duration / 1000, duration));
        /*
        int n = 0;
        try {
            Scanner s = new Scanner(System.in);
            n = s.nextInt();
            s.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        */

        /*
        System.out.println(gcd(10, 1));

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