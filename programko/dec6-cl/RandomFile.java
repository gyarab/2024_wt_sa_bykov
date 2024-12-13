import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomFile {
    public static ArrayList<Integer> mergeSortMerge(ArrayList<Integer> a, ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        while(left.size() > 0 && right.size() > 0) {
            //if left smaller of equal
            if(left.get(0).compareTo(right.get(0)) < 0) {
                result.add(left.get(0));
                left.remove(0);
            }
            else {
                result.add(right.get(0));
                right.remove(0);
            }
        }
        result.addAll(left);
        left.clear();
        result.addAll(right);
        right.clear();

        return result;
    }

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> a) {
        if(a.size() <= 1) return a;

        ArrayList<Integer> left = new ArrayList<Integer>(), right = new ArrayList<Integer>();
        
        int middlePoint = a.size()/2; //truncates
        for(int i = 0; i < middlePoint; i++) { 
            left.add(a.get(i));
        }
        for(int i = middlePoint; i < a.size(); i++) {
            right.add(a.get(i));
        }

        left = mergeSort(left);
        right = mergeSort(right);

        a = mergeSortMerge(a, left, right);
        return a;
    }

    public static void main(String[] args) throws Exception {
        File fo = new File("randomNumbers.txt");
        FileWriter fw = new FileWriter(fo);

        final int RANDOM_NUMBER_AMOUNT = 1000000;
        final int RANDOM_MIN = 10;
        final int RANDOM_MAX = 200;
        final double RANDOM_UPDATE_MESSAGES = RANDOM_NUMBER_AMOUNT/100.0;
        
        for(int i = 0; i < RANDOM_NUMBER_AMOUNT; i++) {
            if(i % RANDOM_UPDATE_MESSAGES == 0) {
                System.out.println(String.format("%f %%", ((double)i/RANDOM_NUMBER_AMOUNT)*100));
            }
            //bound not inclusive
            fw.write(Integer.toString(RANDOM_MIN+ThreadLocalRandom.current().nextInt(RANDOM_MAX-RANDOM_MIN+1)));
            fw.write('\n');
        }

        fw.close();

        //load and sort file
        //HW number 1

        System.out.println("Sorting...");

        File funsort = new File("randomNumbers.txt");
        FileReader unsort = new FileReader(funsort);

        //read
        ArrayList<Integer> values = new ArrayList<Integer>();

        Scanner s = new Scanner(unsort);
        while(s.hasNextLine()) {
            values.add(Integer.parseInt(s.nextLine()));
        }
        
        //sort using merge sort

        values = mergeSort(values);

        s.close();
        unsort.close();

        //write back

        System.out.println("Writing sorted values...");

        File fsort = new File("randomNumbers-sort.txt");
        FileWriter sort = new FileWriter(fsort);

        for(Integer i : values) {
            sort.write(Integer.toString(i));
            sort.write('\n');
        }

        sort.close();

        //split into odd and even
        //HW number 2

        System.out.println("Splitting...");

        File fodd = new File("randomNumbers-odd.txt");
        FileWriter odd = new FileWriter(fodd);
        File feven = new File("randomNumbers-even.txt");
        FileWriter even = new FileWriter(feven);

        File finput = new File("randomNumbers.txt");
        FileReader input = new FileReader(finput);

        //read values - if odd write into one, if even into another
        
        Scanner sinput = new Scanner(input);
        while(sinput.hasNextLine()) {
           int a = Integer.parseInt(sinput.nextLine());
           if(a % 2 == 0) {
            even.write(Integer.toString(a));
            even.write('\n');
           }
           else {
            odd.write(Integer.toString(a));
            odd.write('\n');
           }
        }
        sinput.close();
        odd.close();
        even.close();
    }
}
