package backend.application.interfaces.async.timer;

public interface TimerServiceable {
    public void createTimer();

    public void timeout(Runnable thread);
}
