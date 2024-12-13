import java.util.*;
import java.io.*;

public class Main {
    final static int AMOUNT_PEOPLE = 100;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        //alo possible to save ArrayList directly - will have everything

        {
            ArrayList<Clovek> lidi = new ArrayList<Clovek>();
            for(int i = 0; i < AMOUNT_PEOPLE; i++) {
                lidi.add(Clovek.randomPerson());
            }

            FileOutputStream fos = new FileOutputStream("lidi.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(Integer.valueOf(lidi.size()));
            for(Clovek c : lidi) {
                oos.writeObject(c);
            }

            oos.close();

            FileOutputStream fos2 = new FileOutputStream("lidiVArrayi.dat");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(lidi);
            oos2.close();
        }

        // reload

        {
            ArrayList<Clovek> mojeLidi = new ArrayList<Clovek>();
            FileInputStream fis = new FileInputStream("lidiVArrayi.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
           
            /*
            int amount = (Integer)ois.readObject();
            for(int i = 0; i < amount; i++) {
                mojeLidi.add((Clovek)ois.readObject());
            }
            */

            mojeLidi = (ArrayList<Clovek>)ois.readObject();
            
            ois.close();
            fis.close();

            for(Clovek c : mojeLidi) {
                System.out.println(c);
            }

        

        }
    }
}