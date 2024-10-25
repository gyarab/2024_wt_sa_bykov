public class Duchodce extends Clovek implements Zamestnanec, DostavaDavky {
    protected int letVDuchodu;   

    public Duchodce(String jmeno, int letVDuchodu) {
        super(jmeno);
        this.letVDuchodu = letVDuchodu;
    }

    public int vyskaDavky() {
        return 1000 + this.letVDuchodu;
    }
    public int vyskaPlatu() {
        return 10000;
    }
}
