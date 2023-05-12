package backend.infrastructure.out.controller;

import java.util.Date;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;
// import jakarta.enterprise.context.ApplicationScoped;

@Startup
@Singleton
public class Timer {

    @Resource
    private TimerService timerService;

    @PostConstruct
    public void start() {
        timerService.createIntervalTimer(new Date(), 5000, new TimerConfig());
    }

    @Timeout
    public void timeout() {
        Messages.sendAll("Subscribe to our socials !");
    }

}
