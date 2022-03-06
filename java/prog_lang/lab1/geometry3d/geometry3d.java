package prog_lang.lab1.geometry3d;

import prog_lang.lab1.geometry2d.geometry2d;
import prog_lang.lab1.Exceptions.Ex;
import prog_lang.lab1.Exceptions.Ex.Exc3D;

public class geometry3d {

    protected geometry2d.Figure f;

    private double h;

    public geometry3d(geometry2d.Figure f, double h) throws Exc3D {
        Ex e = new Ex();
        if (h > 0 && f != null) {
            this.f = f;
            this.h = h;
        } else {
            throw e.new Exc3D(f, "Error creating cylinder", h);
        }
    }

    public double volume() {
        return h * f.area();
    }

    public void show() {
        System.out.println(
                "Name:" + f.get_name() + " V=" + volume() + " Created from " + f.getClass() + " With Area=" + f.area());
    }

}
