import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import javax.print.CancelablePrintJob;

public class Sqrt {
    //precision to 2^-100
    static final int PRECISION = 100;
    public static BigDecimal csqrt(BigDecimal num) {
        BigDecimal upper = num;
        BigDecimal lower = BigDecimal.ZERO;
        for(;;) {
            BigDecimal j = upper.add(lower).divide(BigDecimal.TWO);
            if(j.multiply(j).subtract(num).abs().compareTo(BigDecimal.ONE.divide(BigDecimal.TWO.pow(PRECISION))) <= 0) {
                break;
            }
            if(j.multiply(j).compareTo(num) >= 0) upper = j; //number too high - move upper bound
            else lower = j; //number too low
        }
        return upper.add(lower).divide(BigDecimal.TWO);
    }

    public static double func1(double x) {
        return 0.25*x*x*x*x*x - 2*x*x*x*x - 3*x*x*x + 2*x*x + 2*x;
    }

    //TODO fix later
    public static void polyValue() {
        ArrayList<BigDecimal> r = new ArrayList<>();

        //for(int a = 0; a < 50; a++) {
            BigDecimal num = BigDecimal.valueOf(0.0);
            BigDecimal upper = BigDecimal.valueOf(1.5);
            BigDecimal lower = BigDecimal.valueOf(0.57);
            System.out.println(String.format("from %f to %f", lower, upper));

            for(int i = 0; i < 100; i++) {
                BigDecimal j = upper.add(lower).divide(BigDecimal.TWO);
                if(BigDecimal.valueOf(func1(j.doubleValue())).compareTo(num) >= 0) {
                   System.out.println(String.format("high %f - %f (med %f val %f)", lower, upper, j, BigDecimal.valueOf(func1(j.doubleValue()))));
                    upper = j; //number too high - move upper bound
                }
                else {
                    System.out.println(String.format("low %f - %f (med %f val %f)", lower, upper, j, BigDecimal.valueOf(func1(j.doubleValue()))));
                    lower = j; //number too low
                }
            }
            double v = func1(upper.add(lower).divide(BigDecimal.TWO).doubleValue());
            if(v < 0.2 && v > -0.2) {
                System.out.println(v);
                r.add(upper.add(lower).divide(BigDecimal.TWO));
            }
        // }       

        System.out.println(r);
    }

    public static void main(String[] args) {
        byte b1 = 9;
        //replace chars before bytes
        String s = String.format("%8s", Integer.toBinaryString(b1)).replace(' ', '0');
        System.out.println(s);

        System.out.println(csqrt(BigDecimal.valueOf(2.0)));
        //polyValue();
    }
}