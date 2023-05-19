package backend.application.interfaces.out.async.timer;

public interface TimerServiceable {
    public void createTimer();

    public void timeout(Runnable thread);
}
