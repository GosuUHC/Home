package backend.application.implementation.async;

import java.util.concurrent.ConcurrentHashMap;

import backend.application.interfaces.async.INotificationsCounterAsync;
import backend.application.interfaces.async.INotificationsUpdaterAsync;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.concurrent.ManagedExecutorService;

@Startup
@Singleton
public class NotificationsCounter implements INotificationsCounterAsync {

    private final static ConcurrentHashMap<String, Integer> mapNamesCounters = new ConcurrentHashMap<>();
    private Integer initialCount = 0;
    private INotificationsUpdaterAsync updaterAsync;

    @Resource
    private ManagedExecutorService mExecutorService;

    @Override
    public void assignUpdater(INotificationsUpdaterAsync updater) {
        this.updaterAsync = updater;
    }

    @Override
    public void nextAndUpdate(String clientName) {
        mExecutorService.execute(() -> {
            if (!mapNamesCounters.containsKey(clientName)) {
                mapNamesCounters.put(clientName, initialCount);
            }

            Integer value = mapNamesCounters.get(clientName);
            updaterAsync.update(++value, clientName);
            mapNamesCounters.put(clientName, value++);
        });
    }

}
