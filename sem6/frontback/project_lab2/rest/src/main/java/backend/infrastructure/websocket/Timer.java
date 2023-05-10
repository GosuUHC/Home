package backend.infrastructure.websocket;

import java.util.Date;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;

@Startup
@Singleton
public class Timer {

    @Resource
    private TimerService timerService;

    @PostConstruct
    public void start() {
        timerService.createIntervalTimer(new Date(), 10000, new TimerConfig());
    }

    @Timeout
    public void timeout() {
        Notification.sendAll("TIMER 10 SEC TEST");
    }

}
