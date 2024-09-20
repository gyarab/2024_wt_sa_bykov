import java.util.*;

public class Recaman {
    public static long get(int index) {
        ArrayList<Long> results = new ArrayList<Long>();
        long result = 0;
        //lanuv nestandardni zacatek (ne na 0 ale na 1)
        for(int i = 0; i < index+1; i++) {
            int pos = i+1;
            if(result - pos > 0 && !results.contains(result - pos)) result -= pos;
            else result += pos;

            results.add(result);
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner inputreader = new Scanner(System.in);
        int amount = 0;

        //System.out.println("Input N of numbers to generate from Recaman seq.: ");
        try {
            amount = Integer.parseInt(inputreader.nextLine());
        }
        catch(Exception e) {
            System.out.println(e);
            inputreader.close();
            return;
        }

        inputreader.close();

        for(int i = 0; i < amount; i++) {
            System.out.print(Recaman.get(i));
            System.out.print(" ");
        }
        System.out.println();
    }
}
