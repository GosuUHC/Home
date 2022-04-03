package number3;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class PrimaryController {
    boolean isFilling = false;
    Object obj = new Object();
    MyThreadT t = new MyThreadT();
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
        System.out.println("Pause");
        t.changeStateBool();
    }

    public void stopFill() {
        t.interrupt();
        t.updateProgress(0);
    }

    class MyThreadT extends Thread {
        boolean isPaused = false;

        public void run() {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(20);
                    if (isPaused) {
                        synchronized (this) {
                            while (isPaused) {
                                wait();
                            }
                        }
                    } else {

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                progress++;
                                updateProgress(progress);
                            }

                        });
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        public synchronized void changeStateBool() {
            if (isPaused) {
                pauseLoopBtn.setText("Пауза");
            } else {
                pauseLoopBtn.setText("Продолжить");
            }
            this.isPaused = !isPaused;

            notify();
        }

        public void updateProgress(int i) {
            progressLabel.setText("Итерация:" + i);
            progressBar.setProgress(i / 1000.);
        }
    }
}
