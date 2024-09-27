package A;

public class Ctverec extends Rohaty {
    public Ctverec(double sideA) {
        super.sideA = sideA;
    }

    public double getArea() {
        return sideA*sideA;
    }
    public double getCircumference() {
        return 4*sideA;
    }
}
