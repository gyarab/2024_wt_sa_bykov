public class Clovek implements Comparable<Clovek> {
    protected String name;
    protected String surname;

    public Clovek(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Clovek(Clovek druhy) {
        this.name = druhy.name;
        this.surname = druhy.surname;
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
        /*
        //dle prijmeni
        if(!this.surname.toLowerCase().equals(o.surname.toLowerCase())) {
            return this.surname.toLowerCase().compareTo(o.surname.toLowerCase());
        } else if(!this.name.toLowerCase().equals(o.name.toLowerCase())) {
            return this.name.toLowerCase().compareTo(o.name.toLowerCase());
        } else {
            return 0;
        }
       */
      return this.toString().toLowerCase().compareTo(o.toString().toLowerCase());
    }
}
