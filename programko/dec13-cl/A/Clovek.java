import java.io.*;
import java.util.*;

public class Clovek implements Comparable<Clovek>, Serializable {
    private String krestni;
    private String prijmeni;
    private int rokNarozeni;

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
            return new Clovek(names[rand.nextInt(0, names.length)], surnames[rand.nextInt(0, surnames.length)], rand.nextInt(1924, 2024));
        }
        else {
            return new Clovek(names2[rand.nextInt(0, names2.length)], surnames2[rand.nextInt(0, surnames2.length)], rand.nextInt(1924, 2024));
        }
    }


    public Clovek(String krestni, String prijmeni, int rokNarozeni) {
        this.krestni = krestni;
        this.prijmeni = prijmeni;
        this.rokNarozeni = rokNarozeni;
    }

    public int compareTo(Clovek other) {
       if(!this.prijmeni.toLowerCase().equals(other.prijmeni.toLowerCase())) {
            return this.prijmeni.toLowerCase().compareTo(other.prijmeni.toLowerCase());
        } else if(!this.krestni.toLowerCase().equals(other.krestni.toLowerCase())) {
            return this.krestni.toLowerCase().compareTo(other.krestni.toLowerCase());
        } else if(this.rokNarozeni != other.rokNarozeni) {
            return this.rokNarozeni - other.rokNarozeni;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.krestni).append(" ").append(this.prijmeni).append(", nar. ").append(String.valueOf(this.rokNarozeni)).toString();
    }    
}