//pisemka

import java.util.Scanner;

public class A {
    public static int toDecimal(char[] number) {
        int formulaValues[] = new int[number.length];
        //flip
        for(int i = 0; i < formulaValues.length; i++) {
            formulaValues[formulaValues.length-1-i] = number[i] == '1' ? 1 : 0;
        }

        //debug
        //for(int i = 0; i < formulaValues.length; i++) { System.out.println(formulaValues[i]); }

        int x = 2; //bind x to two so we represent binary number

        //horner schema - in int, decimal not required (we multiply with powers of 2)
        int b = formulaValues[formulaValues.length-1];
        for (int i = formulaValues.length-2; i >= 0; i--) {
            b = b * x + formulaValues[i];
        }

        return b;
    }

    public static void main(String[] args) {
        //automated testing
        for(int i = 0; i < 1000; i++) {
            int val = (int)(Math.random()*100000.0);
            int result = toDecimal(Integer.toBinaryString(val).toCharArray());
            if(result != val) {
                System.out.println(String.format("System failed at number %d - as binary %s - result %d", val, Integer.toBinaryString(val), result));
                System.exit(-1);
            }
        }

        //input and function call
        Scanner sc = new Scanner(System.in);
        String vstup = sc.next();
        //validation
        for(int i = 0; i < vstup.length(); i++) {
            if(vstup.charAt(i) != '0' && vstup.charAt(i) != '1') {
                System.out.println("Invalid input!");
                System.exit(-1);
            }
        }
        System.out.println(toDecimal(vstup.toCharArray()));
        sc.close();
    }
}