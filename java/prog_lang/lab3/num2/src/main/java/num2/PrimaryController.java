package num2;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.application.Platform;
public class PrimaryController {

    @FXML
    Button loopBtn1 = new Button();
    @FXML
    Button loopBtn2 = new Button();

    public void infLoop1() {
        loopBtn1.setText("Итерация:0");
        new MyThreadT().start();
        loopBtn1.setDisable(true);
    }

    public void infLoop2(){
        loopBtn2.setText("Итерация:0");
        new MyThreadR();
        loopBtn2.setDisable(true);
    }

    class MyThreadT extends Thread {
        int count1 = 0;

        public void run() {
            while (true) {
                try {
                    System.out.println("Thread x");
                    Thread.sleep(1000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            count1++;
                            SetBtnText1();
                        }
                    });
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void SetBtnText1(){
            loopBtn1.setText("Итерация:" + count1);
        }
    }
    class MyThreadR implements Runnable {
        int count2 = 0;
        Thread t;
        public MyThreadR(){
            t = new Thread(this);
            t.start();
        }
        public void run() {
            while (true) {
                try {
                    System.out.println("Thread y");
                    Thread.sleep(1000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run(){
                            count2++;
                            SetBtnText2();
                        }
                    });
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void SetBtnText2(){
            loopBtn2.setText("Итерация:" + count2);
        }
    }
    
}

