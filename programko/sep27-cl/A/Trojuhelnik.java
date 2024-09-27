package A;

//pravouhly trojuhelnik
public class Trojuhelnik extends Rohaty {
    double sideB;

    public Trojuhelnik(double sideA, double sideB) {
        super.sideA = sideA;
        this.sideB = sideB;
    }

    public double getArea() {
        return super.sideA*this.sideB/2;
    }
    public double getCircumference() {
        return super.sideA+this.sideB + getSideC();
    }
    public double getSideC() {
        return Math.sqrt(Math.pow(super.sideA, 2.0) + Math.pow(this.sideB, 2.0));
    }
}
