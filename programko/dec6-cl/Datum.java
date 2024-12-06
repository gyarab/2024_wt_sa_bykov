public class Datum implements Comparable<Datum> {
    private int den;
    private int mesic;
    private int rok;

    public Datum(int den, int mesic, int rok) {
        this.den = den;
        this.mesic = mesic;
        this.rok = rok;
    }

    public int getDen() {
        return den;
    }
    public void setDen(int den) {
        this.den = den;
    }
    public int getMesic() {
        return mesic;
    }
    public void setMesic(int mesic) {
        this.mesic = mesic;
    }
    public int getRok() {
        return rok;
    }
    public void setRok(int rok) {
        this.rok = rok;
    }

    @Override
    public int compareTo(Datum o) {
        if(this.rok == o.rok) {
            if(this.mesic == o.mesic) {
                return this.den - o.den;
            }
            return this.mesic - o.mesic;
        }
        return this.rok - o.rok;
    }
}
