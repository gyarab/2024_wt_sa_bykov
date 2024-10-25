public class Dite extends Clovek implements DostavaDavky {
    protected int skolniRocnik;

    //chodiDoSkoly - pokud skolni rocnik 0 nechodi! (setrime misto trosku)
    public boolean chodiDoSkoly() {
        return this.skolniRocnik != 0;
    }

    //pokud chodiDoSkoly je false, skolniRocnik se ignoruje
    public Dite(String jmeno, boolean chodiDoSkoly, int skolniRocnik) {
        super(jmeno);
        if(!chodiDoSkoly) this.skolniRocnik = 0;
        else this.skolniRocnik = skolniRocnik;
    }
    
    public int dejSkolniRocnik() {
        return this.skolniRocnik;
    }

    public int vyskaDavky() {
        return this.skolniRocnik*100;
    }
}
