package C;

public class Linka implements Comparable<Linka> {
    private int cislo;
    private int pocetVozu;
    private int prumernaKapacitaVozu;

    public Linka(int cislo, int pocetVozu, int prumernaKapacitaVozu) {
        this.cislo = cislo;
        this.pocetVozu = pocetVozu;
        this.prumernaKapacitaVozu = prumernaKapacitaVozu;
    }

    public int getCapacity() {
        return pocetVozu*prumernaKapacitaVozu;
    }

    @Override
    public int compareTo(Linka other) {
        return ((Linka)other).getCapacity()-this.getCapacity();
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Linka ").append(cislo).append(" ma ").append(pocetVozu).append(" vozu s prumernou kapacitou ").append(prumernaKapacitaVozu).toString(); 
    }
}
