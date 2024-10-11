package A;
public class Main {
    public static void main(String[] args) {
        ElektrickySpotrebic e1 = new Elektrobus();
        System.out.println(e1);
        ElektrickySpotrebic e2 = new Lokomotiva();
        System.out.println(e2);

        IDelaZvuk z = new Pes();
        System.out.println(z.silaZvuku());
        IDelaZvuk z1 = new Elektrobus();
        System.out.println(z1.silaZvuku());
        IDelaZvuk z2 = new Lokomotiva();
        System.out.println(z2.silaZvuku());
        
    }
}
