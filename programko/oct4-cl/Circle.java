
public class Circle extends Shape {
    private double radius;

    Circle(double parameterRadius) throws Exception {
        this.radius = parameterRadius;

        this.validate();
    }

    void setRadius(double parameterRadius) {
        this.radius = parameterRadius;
    }
    double getRadius() {
        return this.radius;
    }

    void validate() throws Exception {
        if(this.radius <= 0) throw new Exception("Invalid radius!");
    }

    double countArea() {
        return Math.PI*Math.pow(this.radius, 2.0);
    }
}