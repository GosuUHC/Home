package prog_lang.lab1.geometry2d;

import prog_lang.lab1.Exceptions.Ex;
import prog_lang.lab1.Exceptions.Ex.Exc2D;

public class Rectangle implements geometry2d.Figure {
    protected double a;
    protected double b;
    protected String name;

    public Rectangle(String name, double a, double b) throws Exc2D {
        Ex e = new Ex();
        if ((a > 0 && b > 0) && (name.length() > 0)) {
            this.name = name;
            this.a = a;
            this.b = b;
        } else {
            throw e.new Exc2D(2, "Rectangle creation fail");
        }

    }

    public void show() {
        System.out.println("Name:" + this.get_name() + " S=" + this.area());
    }

    public String get_name() {
        return this.name;
    }

    public double area() {
        return a * b;
    }
}
