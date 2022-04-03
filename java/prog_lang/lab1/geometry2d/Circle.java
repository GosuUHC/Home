package lab1.geometry2d;

import lab1.Exceptions.Ex;
import lab1.Exceptions.Ex.Exc2D;

public class Circle implements geometry2d.Figure {

    protected double r;
    protected String name;

    public Circle(String name, double r) throws Exc2D {
        Ex e = new Ex();

        if (name.length() > 0 && r >= 0) {
            this.name = name;
            this.r = r;
        } else {
            throw e.new Exc2D(1, "Circle creation fail");
        }

    }

    public void show() {
        System.out.println("Name:" + this.get_name() + " S=" + this.area());
    }

    public String get_name() {
        return this.name;
    }

    public double area() {
        return Math.PI * r * r;
    }
}
