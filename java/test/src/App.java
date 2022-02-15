import java.util.Scanner;

public class App {

interface Shape{
    public void show();
    public double area();
    public String get_name();
}
class Ellipse implements Shape{
    protected double a;
    protected double b;
    protected String name;
    public Ellipse(){}
    public Ellipse( String name, double a, double b){
        if((a>0 && b>0) && (name.length()>0)){
            this.name=name;
            this.a=a;
            this.b=b;
        }
        else {
            throw new IllegalArgumentException("Error creating an ellipse");
        }
        
    }
    public void show(){
        System.out.println("Name:" + this.get_name() + " S=" + this.area());
    }
    public String get_name(){
        return this.name;
    }
    public double area(){
        return Math.PI*a*b;
    }  

}
class Circle extends Ellipse{
        
    public Circle(String name, double a){//maybe super()????
        this.name=name;
        this.a=a;
        this.b=a;
    }
   
}

class Triangle implements Shape{
    protected double a;
    protected double b;
    protected double c;
    protected String name;
    public Triangle(String name, double a, double b, double c){
        if((a>0 && b>0 && c>0 && (a+b)>c && (a+c) >b && (b+c) >a) &&(name.length()>0)){
            this.name=name;
            this.a=a;
            this.b=b;
            this.c=c;
        }
        else{
            throw new IllegalArgumentException("Error creating a triangle");
        }
    }

    public void show(){
        System.out.println("Name:" + this.get_name() + " S=" + this.area());
    }
    public String get_name(){
        return this.name;
    }
    public double area(){
        double p=(a+b+c)/2.;
        return Math.sqrt(p * (p-a) * (p-b) * (p-c));
    }
}
class Rectangle implements Shape{
    protected double a;
    protected double b;
    protected String name;
    public Rectangle(){}
    public Rectangle(String name, double a, double b){
        if((a>0 && b>0)&&(name.length()>0)){
            this.name=name;
            this.a=a;
            this.b=b;
        }
        else{
            throw new IllegalArgumentException("Error creating a Rectangle");
        }

    }
    public void show(){
        System.out.println("Name:" + this.get_name() + " S=" + this.area());
    }
    public String get_name(){
        return this.name;
    }
    public double area(){
        return a*b;
    }
}
class Square extends Rectangle{

    public Square(String name, double a){
        this.name=name;
        this.a=a;
        this.b=a;
    }

}

class Cylinder{
    protected Shape f;

    private double h;

    public Cylinder(Shape f, double h){
        if(h>0){
            
            this.f=f;
            this.h=h;
        }
        else{
            throw new IllegalArgumentException("Error creating a cylinder");
        }
    }
    public double volume(){
        double V=h*f.area();
        if(V>0){
        return V; 
    }
    else{
        throw new IllegalArgumentException("Error calculating cylinder' volume");
    }
    }
    public void show(){
        System.out.println("Name:" + f.get_name() + " V=" + volume() + " Created from " + f.getClass() + " With Area=" + f.area());
    }
    
}

public static void main(String[] args) throws Exception {
    App app=new App();
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
    Ellipse el = app.new Ellipse("Elipz", x, y);
    Circle circ = app.new Circle("Kryg", x);
    Triangle tri = app.new Triangle("Treygolnik", a, b, c);
    Rectangle rect = app.new Rectangle("Pryamoygolnik", a1, b1);
    Square sq = app.new Square("kvadrat", a1);
   
    
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
                    Cylinder CYL = app.new Cylinder(circ, h);
                    CYL.show();

                }break;
                case 2: {
                    System.out.println("TriangleCylinder with a, b, c from triangle");
                    System.out.println("Enter height:");
                    h=in.nextDouble();
                    Cylinder CYL = app.new Cylinder(tri, h);
                    CYL.show();
                }break;
                case 3: {
                    System.out.println("RectangleCylinder with a=a and b=h from rectangle!");
                    Cylinder CYL = app.new Cylinder(rect, b1);
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
