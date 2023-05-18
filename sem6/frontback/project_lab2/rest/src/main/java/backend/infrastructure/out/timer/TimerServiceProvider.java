package backend.infrastructure.out.timer;

import java.util.Date;

import backend.application.interfaces.async.timer.TimerServiceable;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;

@Singleton
public class TimerServiceProvider implements TimerServiceable {

    private Runnable timeOutRunnable;

    @Resource
    private TimerService timerService;

    @PostConstruct
    public void createTimer() {
        timerService.createIntervalTimer(new Date(), 5000, new TimerConfig());
    }

    @Timeout
    public void onTimeout() {
        if (timeOutRunnable != null) {
            timeOutRunnable.run();
        }
    }

    @Override
    public void timeout(Runnable thread) {
        this.timeOutRunnable = thread;
    }

}

// package backend.infrastructure.out.timer;

// import backend.application.interfaces.async.TimerServiceable;
// import jakarta.ejb.Schedule;
// import jakarta.ejb.Singleton;

// @Singleton
// public class TimerServiceProvider implements TimerServiceable {

// private Runnable timeOutRunnable;

// @Override
// @Schedule(second = "*/5", minute = "*", hour = "*")
// public void createTimer() {
// if (timeOutRunnable != null) {
// timeOutRunnable.run();
// }
// }

// @Override
// public void timeout(Runnable thread) {
// this.timeOutRunnable = thread;
// }

// }
