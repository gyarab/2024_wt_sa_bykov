import java.util.*;

public class Main {
    private static boolean processBoolean(String input) {
        //procesovani atributu cloveka
        if(input.compareTo("ano") == 0) return true;
        else if(input.compareTo("ne") == 0) return false;
        else throw new RuntimeException("Nevalidni vstup! (attribut cloveka "+input+")");
    }
    public static void main(String[] args) {
        Scanner vstup = new Scanner(System.in);
        List<Clovek> lidi = new ArrayList<Clovek>();

        for(int i = 0; i < 5; i++) {
            String typCloveka = vstup.next();
            String jmenoCloveka = vstup.next();
            boolean attributCloveka;
            int informaceCloveka = -1;

            //vytvareni zviratka
            if(typCloveka.compareTo("clovek") == 0) {
                lidi.add(new Clovek(jmenoCloveka));
            } else if(typCloveka.compareTo("dite") == 0) {
                attributCloveka = processBoolean(vstup.next());
                if(attributCloveka) {
                    //chodi do skoly - chceme cislo
                    informaceCloveka = vstup.nextInt();
                }
                lidi.add(new Dite(jmenoCloveka, attributCloveka, informaceCloveka));
            } else if(typCloveka.compareTo("dospely") == 0) {
                attributCloveka = processBoolean(vstup.next());
                lidi.add(new Dospely(jmenoCloveka, attributCloveka));
            } else if(typCloveka.compareTo("duchodce") == 0) { 
                informaceCloveka = vstup.nextInt();
                lidi.add(new Duchodce(jmenoCloveka, informaceCloveka));
            } else { 
                vstup.close(); throw new RuntimeException("Nevalidni vstup! (typ zviratka "+typCloveka+")");
            }
        }

        vstup.close(); //zabranit leak (nejsme v C kde je to povoleny)

        //soucty davek a platu
        int soucetDavek = 0;
        int soucetPlatu = 0;
        for(Clovek typek : lidi) {
            //pokud neni ten typ - neni definovano - ignorovat
            if(typek instanceof DostavaDavky) {
                soucetDavek += ((DostavaDavky)typek).vyskaDavky();
            } 
            if(typek instanceof Zamestnanec) {
                soucetPlatu += ((Zamestnanec)typek).vyskaPlatu();
            }
        }

        //plat piseme prvni
        System.out.println(String.format("%d %d", soucetPlatu, soucetDavek));
    }
};