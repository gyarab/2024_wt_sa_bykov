import java.util.*;

public class Main {
    public static final String[] names = {
        "Jiri", "Jan", "Petr", "Josef", "Pavel", "Martin", "Tomas", "Jaroslav", "Miroslav", "Zdenek",
    };
    public static final String[] names2 = {
        "Jana", "Marie", "Eva", "Hana", "Anna", "Lenka", "Katerina", "Lucie", "Vera", "Alena"
    };

    public static final String[] surnames = {
        "Novak", "Svoboda", "Novotny", "Dvorak", "Cerny", "Prochazka", "Kucera", "Vesely", "Horak", "Nemec"
    };
    public static final String[] surnames2 = {
        "Novakova", "Svobodova", "Novotna", "Dvorakova", "Cerna", "Prochazkova", "Kucerova", "Vesela", "Horakova", "Nemcova"
    };

    public static final Random rand = new Random();

    public static Clovek randomPerson() {
        if(Math.random() >= 0.5) {
            return new Clovek(names[rand.nextInt(0, names.length)], surnames[rand.nextInt(0, surnames.length)]);
        }
        else {
            return new Clovek(names2[rand.nextInt(0, names2.length)], surnames2[rand.nextInt(0, surnames2.length)]);
        }
    }

    public static boolean validate(List<Clovek> asorted) {
        Clovek last = asorted.get(0);
        for(int i = 1; i < asorted.size(); i++) {
            if(asorted.get(i).compareTo(last) < 0) return false;
            else last = asorted.get(i);
        }
        return true;
    }

    public static ArrayList<Clovek> deepClone(ArrayList<Clovek> source) {
        ArrayList<Clovek> destination = new ArrayList<Clovek>();
        for(Clovek c : source) {
            destination.add(new Clovek(c));
        }
        return destination;
    }

    //bubble sort
    //https://en.wikipedia.org/wiki/Bubble_sort#Pseudocode_implementation
    public static void bubbleSort(ArrayList<Clovek> a) {
        int comparisons = 0, swaps = 0;
        long start = System.nanoTime();

        boolean swapInIteration = false;
        do {
            swapInIteration = false;
            for (int i = 1; i < a.size(); i++) {
                comparisons++;
                if(a.get(i-1).compareTo(a.get(i)) > 0) {
                    Clovek temp = a.get(i-1);
                    a.set(i-1, a.get(i));
                    a.set(i, temp);
                    swapInIteration = true;
                    swaps++;
                }
            }
        }
        while(swapInIteration);

        double duration = (System.nanoTime() - start) / 1000000.0;
        System.out.println(String.format("bubble %f milliseconds, %d comparisons, %d swaps", duration, comparisons, swaps));
    }

    //selection sort
    public static void selectionSort(ArrayList<Clovek> a) {
        int comparisons = 0, swaps = 0;
        long start = System.nanoTime();

        int minid = 0;

        for(int i = 0; i < a.size(); i++) {
            minid = i;

            for(int j = i; j < a.size(); j++) {
                comparisons++;
                if(a.get(j).compareTo(a.get(minid)) < 0) {
                    minid = j;
                }
            }

            if(minid == -1) break; //already sorted

            Clovek temp = a.get(i);
            a.set(i, a.get(minid));
            a.set(minid, temp);
            swaps++;
        }

        double duration = (System.nanoTime() - start) / 1000000.0;
        System.out.println(String.format("selection %f milliseconds, %d comparisons, %d swaps", duration, comparisons, swaps));
    }

    //insertion sort
    //https://en.wikipedia.org/wiki/Insertion_sort
    public static void insertionSort(ArrayList<Clovek> a) {
        int comparisons = 0, swaps = 0;
        long start = System.nanoTime();

        for(int i = 1; i < a.size(); i++) {
            int j = i;
            while(j > 0 && a.get(j-1).compareTo(a.get(j)) > 0) {
                comparisons++;
                Clovek k = a.get(j);
                a.set(j, a.get(j-1));
                a.set(j-1, k);
                j = j-1;
                swaps++;
            }
        }

        double duration = (System.nanoTime() - start) / 1000000.0;
        System.out.println(String.format("insertion %f milliseconds, %d comparisons, %d swaps", duration, comparisons, swaps));
    }

    public static void main(String[] args) {
        ArrayList<Clovek> a = new ArrayList<Clovek>();

        a.add(new Zamestnanec("zdenek", "hrib-smrkovy", 5000000));
        a.add(new Zamestnanec("profesor", "lana", 50000));
        a.add(new Zamestnanec("mario", "oGurik", 40000));
        a.add(new Zamestnanec("soudruh", "jenicek", 10000));
        a.add(new Zamestnanec("strojvedouci", "petr", 50000));
        a.add(new Zamestnanec("pkpic", "vlakvedouci", 40000));
        a.add(new Zamestnanec("PKPIC", "vlakvedouci", 40001));
        a.add(new Zamestnanec("Grzegorz", "Brzeczyszczykiewicz", 5000));
        a.add(new Zamestnanec("Chrzaszczyzewroszycziak", "Lekolodski", 4897));
        a.add(new Zamestnanec("bobik", "mnammnamovy", 30));

        for(int i = 0; i < 990; i++) {
            a.add(randomPerson());
        }

        //System.out.println(a);

        ArrayList<Clovek> bubble = deepClone(a);
        bubbleSort(bubble);

        ArrayList<Clovek> selection = deepClone(a);
        selectionSort(selection);

        ArrayList<Clovek> insertion = deepClone(a);
        insertionSort(insertion);

        //System.out.println(bubble);
        //System.out.println(selection);
        //System.out.println(insertion);

        System.out.println(validate(a));
        System.out.println(validate(bubble));
        System.out.println(validate(selection));
        System.out.println(validate(insertion));
    }
}
