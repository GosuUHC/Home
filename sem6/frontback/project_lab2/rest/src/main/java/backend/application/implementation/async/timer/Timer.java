package backend.application.implementation.async.timer;

import java.util.Collection;

import backend.application.interfaces.async.Executable;
import backend.application.interfaces.async.timer.TimerServiceable;
import backend.application.interfaces.async.timer.TimerStorable;
import backend.application.interfaces.async.timer.Timerable;

public class Timer implements Timerable {

    private Executable executor;

    private TimerServiceable timerService;

    private TimerStorable store;

    @Override
    public void onTimeout() {
        for (Runnable callback : store.values()) {
            executor.execute(callback::run);
        }
    }

    @Override
    public void addListener(String clientID, Runnable callback) {
        store.put(clientID, callback);
    }

    @Override
    public void removeListener(String clientID) {
        store.remove(clientID);
    }

    @Override
    public Collection<Runnable> getListeners() {
        return store.values();
    }

    @Override
    public void assignExecutor(Executable executor) {
        this.executor = executor;
    }

    @Override
    public void assignTimerService(TimerServiceable timerService) {
        this.timerService = timerService;
        this.timerService.timeout(this::onTimeout);
    }

    @Override
    public void assignStore(TimerStorable store) {
        this.store = store;
    }

}
