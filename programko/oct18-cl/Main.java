import java.util.*;

public class Main {
    //bubble sort
    //https://en.wikipedia.org/wiki/Bubble_sort#Pseudocode_implementation
    
    public static void bubbleSort(ArrayList<Clovek> a) {
        boolean swapInIteration = false;
        while(!swapInIteration) {
            swapInIteration = false;
            for (int i = 1; i < a.size(); i++) {
                if(a.get(i-1).compareTo(a.get(i)) > 0) {
                    Clovek temp = a.get(i-1);
                    a.set(i-1, a.get(i));
                    a.set(i, temp);
                    swapInIteration = true;
                }
            }
        }
    }

    /*
    a = 00010001 hodnoty
    b = 11110001

    a = a^b; b = a^b; a = b^a

    a = 11100000

    b = 00010001 vyhadza to
    a = 11110001  

    XOR swap https://stackoverflow.com/questions/8862136/is-there-a-built-in-swap-function-in-c
    */

    //insertion sort
    //https://en.wikipedia.org/wiki/Insertion_sort

    public static void main(String[] args) {
        ArrayList<Clovek> a = new ArrayList<Clovek>();

        a.add(new Zamestnanec("profesor", "lana", 50000));
        a.add(new Zamestnanec("mario", "oGurik", 40000));
        a.add(new Zamestnanec("soudruh", "jenicek", 10000));

        /*
        Scanner s = new Scanner(System.in);
        for(int i = 0; i < 3; i++) {
            a.add(new Zamestnanec(s.next(), s.next(), s.nextInt()));
        }
        s.close();
        */

        //a.sort(null);
        bubbleSort(a);

        System.out.println(a);
    }
}
