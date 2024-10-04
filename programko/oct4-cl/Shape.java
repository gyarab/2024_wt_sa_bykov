
public abstract class Shape {
    abstract double countArea();

    abstract void validate() throws Exception;

    @Override
    public String toString() {
        return "Area of "+this.getClass().getSimpleName()+" is "+this.countArea();
    }
}