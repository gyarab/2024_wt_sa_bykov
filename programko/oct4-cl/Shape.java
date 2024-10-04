
public abstract class Shape {
    abstract double countArea();
    abstract double countPerimeter();
    
    abstract void validate() throws Exception;

    @Override
    public String toString() {
        return "Area of "+this.getClass().getSimpleName()+" is "+this.countArea()+", perimeter is "+this.countPerimeter();
    }
}