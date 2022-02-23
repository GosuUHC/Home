package prog_lang.lab1.MainTest;
import java.util.Scanner;

import prog_lang.lab1.Flat.Circle;
import prog_lang.lab1.Flat.Ellipse;
import prog_lang.lab1.Flat.Rectangle;
import prog_lang.lab1.Flat.Square;
import prog_lang.lab1.Flat.Triangle;
import prog_lang.lab1.Volumetric.Volumetric;
//так много, тк лучше их все указывать 
public class MainTest{
public static void main(String[] args) throws Exception {

    Scanner in = new Scanner(System.in);
    double x, y;
    double a, b, c;
    double a1, b1;
    double h;
    
    System.out.println("Enter x, y(ellipse diagonals(x will be circle radius)");
    x=in.nextDouble(); y=in.nextDouble();
    System.out.println("Enter a, b, c(triangle):");
    a=in.nextDouble(); b=in.nextDouble(); c=in.nextDouble();
    System.out.println("Enter a, b(rectangle sides):");
    a1=in.nextDouble(); b1=in.nextDouble();
    try{
    Ellipse el = new Ellipse("Elipz", x, y);
    Circle circ = new Circle("Kryg", x);
    Triangle tri = new Triangle("Treygolnik", a, b, c);
    Rectangle rect = new Rectangle("Pryamoygolnik", a1, b1);
    Square sq = new Square("kvadrat", a1);
   
    
    el.show();
    circ.show();
    tri.show();
    rect.show();
    sq.show();

    
    int t;
    System.out.println("Do you want to create cylinders?\n0-no 1-yes");
    t=in.nextInt();
    if(t==1){

        int z=-1;
        while(z!=0){
            System.out.println("What cylinder do you want to create?\n1-circle 2-triangle 3-square 0-exit");
            z=in.nextInt();
            switch(z)
            {
                case 1: {
                    System.out.println("CircleCylinder with r from ellipse");
                    System.out.println("Enter height:");
                    h=in.nextDouble();
                    Volumetric CYL = new Volumetric(circ, h);
                    CYL.show();

                }break;
                case 2: {
                    System.out.println("TriangleCylinder with a, b, c from triangle");
                    System.out.println("Enter height:");
                    h=in.nextDouble();
                    Volumetric CYL = new Volumetric(tri, h);
                    CYL.show();
                }break;
                case 3: {
                    System.out.println("RectangleCylinder with a=a and b=h from rectangle!");
                     Volumetric CYL = new Volumetric(rect, b1);
                    CYL.show();
                }break;
                default:{
                    break;
                }
            }

        }
    }
    else System.out.println("See you next time!");
    in.close();
}catch(IllegalArgumentException exc){
    System.out.println(exc.getMessage());
}

    }

}
