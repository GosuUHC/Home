package prog_lang.lab1.geometry2d;

public class Triangle implements geometry2d.Figure{
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
