package prog_lang.lab1.MainTest;

import java.util.Scanner;

import prog_lang.lab1.geometry2d.Circle;
import prog_lang.lab1.geometry2d.Rectangle;
import prog_lang.lab1.geometry3d.geometry3d;
import prog_lang.lab1.Exceptions.Ex.Exc2D;
import prog_lang.lab1.Exceptions.Ex.Exc3D;

public class MainTest {
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);
        double r;
        double a, b;
        double h;

        System.out.println("Enter r(circle radius)");
        r = in.nextDouble();
        System.out.println("Enter a, b(rectangle sides):");
        a = in.nextDouble();
        b = in.nextDouble();

        try {
            Circle circ = new Circle("Kryg", r);
            Rectangle rect = new Rectangle("Pryamoygolnik", a, b);
            circ.show();
            rect.show();

            int t;
            System.out.println("Do you want to create cylinders?\n0-no 1-yes");
            t = in.nextInt();
            if (t == 1) {

                int z = -1;
                while (z != 0) {
                    System.out.println("What cylinder do you want to create?\n1-circle 2-rectangle 0-exit");
                    z = in.nextInt();
                    switch (z) {
                        case 1: {
                            System.out.println("CircleCylinder with r from ellipse");
                            System.out.println("Enter height:");
                            h = in.nextDouble();
                            geometry3d CYL = new geometry3d(circ, h);
                            CYL.show();

                        }
                            break;
                        case 2: {
                            System.out.println("RectangleCylinder with a=a and b=h from rectangle!");
                            geometry3d CYL = new geometry3d(rect, b);
                            CYL.show();
                        }
                            break;
                        default: {
                            break;
                        }
                    }

                }
            } else
                System.out.println("See you next time!");
            in.close();
        } catch (Exc2D exc2d) {
            exc2d.show();
        } catch (Exc3D exc3d) {
            exc3d.Out();
        }

    }

}
