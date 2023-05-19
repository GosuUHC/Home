package backend.application.in.async.impl;

import backend.application.in.async.api.Notificationable;
import backend.application.out.Executable;
import backend.application.out.Updatable;

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
    public void notifyUser(String clientID, String value) {
        executor.execute(() -> updater.update(clientID, value));
    }

}
