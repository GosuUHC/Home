package backend.infrastructure.out.timer;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import backend.application.interfaces.async.timer.TimerStorable;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TimerStore implements TimerStorable {

    private Map<String, Runnable> callbacks = new ConcurrentHashMap<String, Runnable>();

    @Override
    public void put(String clientID, Runnable callback) {
        callbacks.put(clientID, callback);
    }

    @Override
    public void remove(String clientID) {
        callbacks.remove(clientID);
    }

    @Override
    public Collection<Runnable> values() {
        return callbacks.values();
    }

}
