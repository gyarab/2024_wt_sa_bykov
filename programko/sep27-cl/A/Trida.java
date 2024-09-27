package A;

class Trida {
    public static void main(String[] args) {
        Trojuhelnik t = new Trojuhelnik(5, 4);
        System.out.println(t.getSideC());
        System.out.println(t.getArea());
        System.out.println(t.getCircumference());

        Ctverec c = new Ctverec(4);
        System.out.println(c.getArea());
        System.out.println(c.getCircumference());

        //polymorphism
        Rohaty r = new Trojuhelnik(5, 4);
        System.out.println(r.getArea());

    }
}