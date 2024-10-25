public class Zamestnanec extends Clovek {
    private final int plat;

    Zamestnanec(String name, String surname, int plat) {
        super(name, surname);
        this.plat = plat;
    }

    int getPlat() {
        return this.plat;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.name).append(" ").append(this.surname).append(" (plat ").append(plat).append(")").toString();
    }
}
