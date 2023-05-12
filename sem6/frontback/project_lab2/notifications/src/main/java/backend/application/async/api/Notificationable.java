package backend.application.async.api;

public interface Notificationable {
    public void assignExecutor(Executable executor);

    public void assignUpdater(Updatable updater);

    public void notificate(String clientID, String value);
}
