package prog_lang.lab1.Volumetric;
import prog_lang.lab1.Flat.Flat;

public class Volumetric {
    
        protected Flat.Shape f;
    
        private double h;
    
        public Volumetric(Flat.Shape f, double h){
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

