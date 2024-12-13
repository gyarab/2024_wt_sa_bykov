import java.util.*;
import java.io.*;

public class Main {
    public static void lanaSort(ArrayList<Integer> parameterList) {
        //System.out.println(parameterList); //DEBUG line

        //for each id
        for(int mainI = 0; mainI < parameterList.size(); mainI++) {
            //find min value
            int minId = Integer.MAX_VALUE;
            int minValue = Integer.MAX_VALUE;
            for(int minFinderI = mainI; minFinderI < parameterList.size(); minFinderI++) {
                if(parameterList.get(minFinderI) < minValue) {
                    minValue = parameterList.get(minFinderI);
                    minId = minFinderI;
                }
            }

            //swap
            int tempValue = parameterList.get(mainI);
            parameterList.set(mainI, minValue);
            parameterList.set(minId, tempValue);
        }

        //complexity n*n = O(n2)
        //for N elements in array - loop for min element {O(n)} and swap {O(1)}
    }

    public static void printArrayWeird(ArrayList<Integer> parameterList) throws RuntimeException {
        //assume always even
        if(parameterList.size() % 2 != 0) {
            throw new RuntimeException();
        }
        int halfSize = parameterList.size()/2;
        for(int i = 0; i < halfSize; i++) {
            System.out.println(parameterList.get(parameterList.size()-i-1));
            System.out.println(parameterList.get(i));
        }
    }
    public static void main(String[] args) throws Exception {
        try {
            File f = new File("vstup.txt");
            FileReader fr = new FileReader(f);
            Scanner s = new Scanner(fr);

            ArrayList<Integer> numbers = new ArrayList<Integer>();

            //scanner more efficient than reading by char
            while(s.hasNextLine()) {
                numbers.add(Integer.parseInt(s.nextLine()));
            }

            fr.close();
            s.close();

            lanaSort(numbers);

            printArrayWeird(numbers);

        } catch(FileNotFoundException e) {
            System.out.println("File could not be opened");
        } catch(NumberFormatException e) {
            System.out.println("Input NaN!");
        } catch(NullPointerException e) {
            System.out.println("Invalid filepath!");
        } catch(RuntimeException e) {
            System.out.println("Invalid size - program assumes even amount of numbers.");
        }
    }
}