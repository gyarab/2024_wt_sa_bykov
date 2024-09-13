/*
 * JAVA MEDIAN CALCULATOR 
 * Code authored on the 13.9.2024
 * (c) Martin Bykov 2024
 * All rights reserved.
 */

import java.util.*;

public class MedianCalculator {
    //implementation of insertion sort
    //https://en.wikipedia.org/wiki/Insertion_sort
    public static List<Integer> sort(List<Integer> atosort) {
        for(int i = 1; i < atosort.size(); i++) {
            int j = i;
            while(j > 0 && atosort.get(j-1) > atosort.get(j)) {
                int k = atosort.get(j);
                atosort.set(j, atosort.get(j-1));
                atosort.set(j-1, k);
                j = j-1;
            }
        }
        return atosort;
    }

    //validates if list sorted (true) or not (false)
    public static boolean validate(List<Integer> asorted) {
        int last = Integer.MIN_VALUE;
        for(int i = 0; i < asorted.size(); i++) {
            if(asorted.get(i) < last) return false;
            else last = asorted.get(i);
        }
        return true;
    }

    //main method
    public static void main(String[] args) throws Exception {
        System.out.println("Java Median Calculator");

        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split(",|;|/|\\");

        List<Integer> ints = new ArrayList<Integer>();
        for(int i = 0; i < input.length; i++) {
            ints.add(Integer.parseInt(input[i]));
        }

        int old_int_list_length = ints.size();
        ints = MedianCalculator.sort(ints);

        System.out.print("Sorted list: ");
        System.out.println(ints);

        assert ints.size() == old_int_list_length; //check if we failed miserably with sorting (different length)

        //check if failed (but not too miserably)
        if(!MedianCalculator.validate(ints)) throw new RuntimeException("String not sorted!");

        if(ints.size() % 2 == 0) {
            double medianid = Math.floor(ints.size()/2);
            Integer val1 = ints.get((int)medianid);
            Integer val2 = ints.get((int)medianid+1);
            Double value = (double)(val1+val2)/2; //avg of 2 middle ones
            System.out.println(String.format("Avg of 2 in middle: (%d,%d): %1.2f \nFirst value: %d", val1, val2, value, val1));
        }
        else {
            System.out.println(ints.get(ints.size()/2));
        }
    }
}
