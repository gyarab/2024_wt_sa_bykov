//C++ version of Java project

#include <iostream>
#include <math.h>

class Shape {
public:
    virtual double_t countArea() const = 0;
};

std::ostream& operator<<(std::ostream& a_stream, const Shape& a_shape) {
    a_stream << "Area of " << typeid(a_shape).name() << ": " << a_shape.countArea() << "\n";
    return a_stream;
}

class Rectangle : public Shape {
public:
    double_t a, b;

    Rectangle(double_t a_a, double_t a_b) : a(a_a), b(a_b) {}

    virtual double_t countArea() const override {
        return this->a*this->b;
    }

    virtual ~Rectangle() {}
};

class Triangle : public Shape {
public:
    double_t a, b, c;

    Triangle(double_t a_a, double_t a_b, double_t a_c) : a(a_a), b(a_b), c(a_c) {}

    virtual double_t countArea() const override {
        //https://en.wikipedia.org/wiki/Heron%27s_formula
        double_t s = (this->a+this->b+this->c)*0.5; //semiperimeter - 1/2 of perimeter
        return std::sqrt(s*(s-this->a)*(s-this->b)*(s-this->c));
    }

    virtual ~Triangle() {}
};

class Circle : public Shape {
public:
    double_t radius;

    Circle(double_t a_radius) : radius(a_radius) {}

    virtual double_t countArea() const override {
        return M_PI*std::pow(this->radius, 2);
    }

    virtual ~Circle() {}
};

int main() {
    Shape* s1 = new Circle(5);
    Shape* s2 = new Rectangle(5, 10);
    Shape* s3 = new Triangle(6, 6, 11);

    std::cout << *s1 << "\n" << *s2 << "\n" << *s3 << "\n";

    delete s1;
    delete s2;
    delete s3;
}