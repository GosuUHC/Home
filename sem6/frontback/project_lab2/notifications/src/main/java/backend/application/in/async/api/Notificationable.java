package backend.application.in.async.api;

import backend.application.out.Executable;
import backend.application.out.Updatable;

public interface Notificationable {
    public void assignExecutor(Executable executor);

    public void assignUpdater(Updatable updater);

    public void notifyUser(String clientID, String value);
}
