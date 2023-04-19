package backend.model.implementation.async;

import java.util.concurrent.ConcurrentHashMap;

import backend.model.interfaces.async.INotificationsCounterAsync;
import backend.model.interfaces.async.INotificationsUpdaterAsync;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
public class NotificationsCounter implements INotificationsCounterAsync {

    private final static ConcurrentHashMap<String, Integer> mapNamesCounters = new ConcurrentHashMap<>();
    private Integer initialCount = 0;

    @Asynchronous
    @Override
    public void nextAndUpdate(INotificationsUpdaterAsync updater, String name) {
        if (!mapNamesCounters.containsKey(name)) {
            mapNamesCounters.put(name, initialCount);
        }
        
        Integer value = mapNamesCounters.get(name);
        updater.update(++value);
        mapNamesCounters.put(name, value++);
    }

    @Override
    public String nextAsync(INotificationsUpdaterAsync updater) {
        return "";
    }

}
