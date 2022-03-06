package prog_lang.lab1.Exception;
import java.io.FileWriter;
import java.io.IOException;

import prog_lang.lab1.geometry2d.geometry2d.Figure;;
public class Ex extends Exception{
    class Exc2D{
        private int n;
        private String msg;

        public Exc2D(int n, String msg){
            this.n = n;
            this.msg = msg;
        }
        public void show(){
            System.out.println ("Exception in 2D module\n" + "Error code:" + n);
            System.out.println(msg);
        }
    }

    class Exc3D{
        private Figure f;
        private String msg;
        private double height;
        public Exc3D(Figure f, String msg, double height){
            this.f = f; 
            this.msg = msg;
            this.height = height;
        }
        public void Out(){
            System.out.println("Exception in 3D module\nCheck Log3D.txt file for more information");
            try(FileWriter writer = new FileWriter("Log3D.txt", false)){
                writer.write("Something went wrong running 3D geometry module");
                writer.write("Was trying to create with:");
                writer.write("Fugure:" + f + "\nHeight:" + height + "Exception:" + msg);
            }
            catch (IOException ex){
                System.out.println("Could not create a log file");
            }
        }
    }
    

}
