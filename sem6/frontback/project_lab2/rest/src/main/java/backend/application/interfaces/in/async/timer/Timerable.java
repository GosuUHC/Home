package backend.application.interfaces.in.async.timer;

import java.util.Collection;

import backend.application.interfaces.out.async.Executable;
import backend.application.interfaces.out.async.timer.TimerServiceable;
import backend.application.interfaces.out.async.timer.TimerStorable;

public interface Timerable {
    public void onTimeout();

    public void addListener(String clientID, Runnable callback);

    public void removeListener(String clientID);

    public Collection<Runnable> getListeners();

    public void assignExecutor(Executable executor);

    public void assignTimerService(TimerServiceable timerService);

    public void assignStore(TimerStorable store);
}
