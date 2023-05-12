package backend.application.async.impl;

import backend.application.async.api.Executable;
import backend.application.async.api.Notificationable;
import backend.application.async.api.Updatable;

public class Notifier implements Notificationable {

    private Executable executor;

    private Updatable updater;

    @Override
    public void assignExecutor(Executable executor) {
        this.executor = executor;
    }

    @Override
    public void assignUpdater(Updatable updater) {
        this.updater = updater;
    }

    @Override
    public void notificate(String clientID, String value) {
        executor.execute(() -> {
            updater.update(clientID, value);
        });
    }

}
