public class Clovek implements Comparable<Clovek> {
    protected String name;
    protected String surname;

    public Clovek(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getJmeno() {
        return this.name;
    }
    public String getPrijmeni() {
        return this.name;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.name).append(" ").append(this.surname).toString();
    }

    @Override
    public int compareTo(Clovek o) {
        if(!this.surname.equals(o.surname)) {
            return this.surname.compareTo(o.surname);
        }
        else if(!this.name.equals(o.name)) {
            return this.name.compareTo(o.name);
        }
        else {
            return 0;
        }
    }
}
