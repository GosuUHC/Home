package backend.application.interfaces.async.timer;

import java.util.Collection;

public interface TimerStorable {
    public void put(String clientID, Runnable callback);

    public void remove(String clientID);

    public Collection<Runnable> values();
}
