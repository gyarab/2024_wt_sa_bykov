package B;

class Trida {
    public static void main(String[] args) {
        Zvire z1 = new Cap();
        Zvire z2 = new Kocka();
        Zvire z3 = new Pes();
        Zvire z4 = new Clovek();

        Zvire c1 = new Profesor(); //profesor a student jsou potomci cloveka
        Zvire c2 = new Student();

        z1.oMne();
        z2.oMne();
        z3.oMne();
        z4.oMne();

        c1.oMne();
        c2.oMne();
    }
}