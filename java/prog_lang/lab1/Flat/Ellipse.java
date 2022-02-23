package prog_lang.lab1.Flat;

public class Ellipse implements Flat.Shape{
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
