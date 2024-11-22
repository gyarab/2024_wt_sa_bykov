import java.util.Random;

import javax.management.RuntimeErrorException;

public class Clovek implements Comparable<Clovek> {
    private String krestni;
    private String prijmeni;  
    private static int sortMode = 0;  

    public static final int SORT_NONE = 0;
    public static final int SORT_FIRSTNAME = 1;
    public static final int SORT_LASTNAME = 2;

    public static void setSortMode(int setSortMode) {
        Clovek.sortMode = setSortMode;
    }

    private static final String[] names = {
        "Jiri", "Jan", "Petr", "Josef", "Pavel", "Martin", "Tomas", "Jaroslav", "Miroslav", "Zdenek",
    };
    private static final String[] names2 = {
        "Jana", "Marie", "Eva", "Hana", "Anna", "Lenka", "Katerina", "Lucie", "Vera", "Alena"
    };
    private static final String[] surnames = {
        "Novak", "Svoboda", "Novotny", "Dvorak", "Cerny", "Prochazka", "Kucera", "Vesely", "Horak", "Nemec"
    };
    private static final String[] surnames2 = {
        "Novakova", "Svobodova", "Novotna", "Dvorakova", "Cerna", "Prochazkova", "Kucerova", "Vesela", "Horakova", "Nemcova"
    };

    private static final Random rand = new Random();

    public static Clovek randomPerson() {
        if(Math.random() >= 0.5) {
            return new Clovek(names[rand.nextInt(0, names.length)], surnames[rand.nextInt(0, surnames.length)]);
        }
        else {
            return new Clovek(names2[rand.nextInt(0, names2.length)], surnames2[rand.nextInt(0, surnames2.length)]);
        }
    }


    public Clovek(String krestni, String prijmeni) {
        this.krestni = krestni;
        this.prijmeni = prijmeni;
    }

    public int compareTo(Clovek other) {
        if(Clovek.sortMode == SORT_NONE) return 0;

        switch(Clovek.sortMode) {
            case(SORT_FIRSTNAME):
                return this.krestni.compareTo(other.krestni);
            case(SORT_LASTNAME):
                return this.prijmeni.compareTo(other.prijmeni);
            default:
                throw new RuntimeErrorException(null, "Set sorting mode!");
        }
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.krestni).append(" ").append(this.prijmeni).toString();
    }
}
