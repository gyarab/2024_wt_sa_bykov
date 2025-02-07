import java.math.BigInteger;
import java.util.concurrent.*;

class Main {
    public static BigInteger sqrt(BigInteger num) {
        for(BigInteger i = BigInteger.ONE; i.compareTo(num) < 0; i = i.add(BigInteger.ONE)) {
            if(i.multiply(i).compareTo(num) >= 0) {
                return i;
            }
        }
        return BigInteger.ZERO;
    }

    //TODO fix

    public static BigInteger hsqrt(BigInteger num) {
        BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
        BigInteger halfPoint = num.divide(two);
        BigInteger i = halfPoint;
        System.out.println(halfPoint);
        for(;;) {
           if(halfPoint.multiply(halfPoint).compareTo(num) < 0) {
                halfPoint = halfPoint.divide(two);
                i = halfPoint.add(i);
                //System.out.println(halfPoint);
           }
           else if(halfPoint.multiply(halfPoint).compareTo(num) > 0) {
                halfPoint = halfPoint.divide(two);
                i = i.subtract(halfPoint);
                System.out.println(halfPoint);
           }
           else { return halfPoint; }
        }
        //return BigInteger.ZERO;
    }

    //newtons method
    public static BigInteger nsqrt(BigInteger num) {
        return BigInteger.ZERO;
    }

    public static void main(String[] args) {
        BigInteger num = new BigInteger("1000");

        long start = System.nanoTime();
        BigInteger val = Main.sqrt(num);
        long end = System.nanoTime();
        System.out.println(String.format("Base time: %d ns (%d)", (end-start)/1000, val));

        start = System.nanoTime();
        val = Main.hsqrt(num);
        end = System.nanoTime();
        System.out.println(String.format("Half time: %d ns (%d)", (end-start)/1000, val));

        System.out.println(val);

        assert(num.multiply(num).compareTo(val) <= 0);
        assert(num.add(BigInteger.ONE).multiply(num.add(BigInteger.ONE)).compareTo(val) >= 0);
    }
}