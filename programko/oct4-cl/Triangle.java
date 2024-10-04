
public class Triangle extends Shape {
    //a - left side, b - bottom, c - right side
    private double a, b, c;

    Triangle(double paramA, double paramB, double paramC) throws Exception {
        this.a = paramA;
        this.b = paramB;
        this.c = paramC;

        this.validate();
    }

    //getters and setters generated with VSCode
    public double getA() {
        return a;
    }
    public void setA(double a) throws Exception {
        this.a = a;
        this.validate();
    }
    public double getB() {
        return b;
    }
    public void setB(double b) throws Exception {
        this.b = b;
        this.validate();
    }
    public double getC() {
        return c;
    }
    public void setC(double c) throws Exception {
        this.c = c;
        this.validate();
    }

    void validate() throws Exception {
        //triangle inequality or sides = 0
        if(this.a + this.b <= this.c || this.a <= 0 || this.b <= 0 || this.c <= 0) throw new RuntimeException("Invalid data!");
    }

    double countArea() {
        //https://en.wikipedia.org/wiki/Heron%27s_formula
        double s = (this.a+this.b+this.c)*0.5; //semiperimeter - 1/2 of perimeter
        return Math.sqrt(s*(s-this.a)*(s-this.b)*(s-this.c));
    }
}
