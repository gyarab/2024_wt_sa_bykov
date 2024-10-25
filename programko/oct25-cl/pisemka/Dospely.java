public class Dospely extends Clovek implements Zamestnanec {
    protected boolean pracuje;

    public Dospely(String jmeno, boolean pracuje) {
        super(jmeno);
        this.pracuje = pracuje;
    }

    public int vyskaPlatu() {
        return this.pracuje ? 45854 : 0;
    }
}
