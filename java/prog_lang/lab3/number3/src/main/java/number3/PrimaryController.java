package number3;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class PrimaryController {
    boolean isFilling = false;
    boolean isPaused = false;
    Thread t = new MyThreadT();
    int progress = 0;

    @FXML
    Button startLoopBtn = new Button();

    @FXML
    Button pauseLoopBtn = new Button();

    @FXML
    Button stopLoopBtn = new Button();

    @FXML
    ProgressBar progressBar = new ProgressBar();

    @FXML
    Label progressLabel = new Label();

    public void startFill() {
        if (!isFilling) {
            isFilling = true;
            t.start();
        } else {
            progress = 0;
            t.interrupt();
            t = new MyThreadT();
            t.start();
        }

    }

    public void pauseFill() {
        if (!isPaused) {
            pauseLoopBtn.setText("Продолжить");
            
            isPaused = true;
        } else {
            isPaused = false;
            t.resume();
        }

    }

    public void stopFill() {
        progress = 0;
        t.interrupt();
        progressBar.setProgress(0);
        progressLabel.setText("");
    }

    class MyThreadT extends Thread {

        public void run() {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(20);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            progress++;
                            updateProgress(progress);
                        }

                    });

                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        public void updateProgress(int i) {
            progressLabel.setText("Итерация:" + i);
            progressBar.setProgress(i / 1000.);
        }
    }
}
