import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Clovek> lidi = new ArrayList<Clovek>();
        for(int i = 0; i < 10; i++) {
            lidi.add(Clovek.randomPerson());
        }

        System.out.println(lidi);

        Clovek.setSortMode(Clovek.SORT_NONE);
        lidi.sort(null);

        System.out.println(lidi);

        Clovek.setSortMode(Clovek.SORT_FIRSTNAME);
        lidi.sort(null);

        System.out.println(lidi);

        Clovek.setSortMode(Clovek.SORT_LASTNAME);
        lidi.sort(null);

        System.out.println(lidi);
    }
}