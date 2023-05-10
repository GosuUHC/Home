package backend.application.interfaces.async;

public interface INotificationsCounterAsync {
    void nextAndUpdate(String name);

    void assignUpdater(INotificationsUpdaterAsync updater);
}
