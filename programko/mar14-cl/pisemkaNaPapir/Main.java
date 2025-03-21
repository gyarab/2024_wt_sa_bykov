package pisemkaNaPapir;
public class Main {
    public static boolean isPrime(long num) {
        if(num == 2 || num == 3) return true;
        if(num == 1) return false;

        for(long i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    public static boolean isSymmetric(long num) {
        String s = String.valueOf(num);
        //chybka - trunc neni pro long...
        for(int i = 0; i < s.length()/2; i++) {
            ///chybka - u length bez zavorek
            if(s.charAt(i) != s.charAt(s.length()-1-i)) return false;
        }
        return true;
    }

    static class DalsiPrvocislo extends Thread {
        long start;

        public DalsiPrvocislo(long paramStart) {
            this.start = paramStart;
        }

        @Override
        public void run() {
            if(this.start >= 1000-1) return;

            long n = this.start+1;
            while(!isPrime(n) || !isSymmetric(n)) { n++; }
            System.out.println(n);

            DalsiPrvocislo p = new DalsiPrvocislo(n);
            p.start();
        }
    }
    

    public static void main(String[] args) {
        DalsiPrvocislo p = new DalsiPrvocislo(1);
        p.start();
    }
}