
public class Main {
    public static void main(String[] args) throws Exception {
        Shape s = new Circle(5);
        System.out.println(s); //we override ToString for simplicity
        s = new Rectangle(5, 10);
        System.out.println(s);
        s = new Triangle(6, 6, 11);
        System.out.println(s);
    }
}
