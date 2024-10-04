
public class Rectangle extends Shape {
    private double a;
    private double b;

    Rectangle(double parameterA, double parameterB) throws Exception {
        this.a = parameterA;
        this.b = parameterB;

        this.validate();
    }

    void setA(double parameterA) throws Exception {
        this.a = parameterA;
        this.validate();
    }
    void setB(double parameterB) throws Exception {
        this.b = parameterB;
        this.validate();
    }
    double getA() {
        return this.a;
    }
    double getB() {
        return this.b;
    }

    void validate() throws Exception {
        if(this.a <= 0 || this.b <= 0) throw new Exception("Invalid a or b!");
    }

    double countArea() {
        return this.a*this.b;
    }

    double countPerimeter() {
        return (2*this.a)+(2*this.b);
    }
}
