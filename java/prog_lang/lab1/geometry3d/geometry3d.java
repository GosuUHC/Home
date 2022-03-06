package prog_lang.lab1.geometry3d;
import prog_lang.lab1.geometry2d.geometry2d;

public class geometry3d {
    
        protected geometry2d.Figure f;
    
        private double h;
    
        public geometry3d(geometry2d.Figure f, double h){
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

