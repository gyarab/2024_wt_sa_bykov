import java.util.*;

public class Main {
    public static Shape getLargestByArea(List<Shape> parameterListOfShapes) {
        Shape largestShape = parameterListOfShapes.get(0);
        for(Shape shape : parameterListOfShapes) {
            if(shape.countArea() > largestShape.countArea()) {
                largestShape = shape;
            }
            //System.out.println(shape); //we override ToString for simplicity
        }
        return largestShape;
    }

    public static Shape getLargestByPerimeter(List<Shape> parameterListOfShapes) {
        Shape largestShape = parameterListOfShapes.get(0);
        for(Shape shape : parameterListOfShapes) {
            if(shape.countPerimeter() > largestShape.countPerimeter()) {
                largestShape = shape;
            }
            //System.out.println(shape); //we override ToString for simplicity
        }
        return largestShape;
    }


    public static void main(String[] args) throws Exception {
        ArrayList<Shape> list = new ArrayList<Shape>();
        list.add(new Rectangle(6, 10));
        list.add(new Triangle(6, 6, 11));
        list.add(new Circle(5));
        
        System.out.println("Largest by area - "+getLargestByArea(list));
        System.out.println("Largest by perimeter - "+getLargestByPerimeter(list));

        for(Shape shape : list) {
            System.out.println("Other - "+shape);
        }
    }
}
