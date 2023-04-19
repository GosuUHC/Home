package backend.model.interfaces.async;

public interface INotificationsCounterAsync {
    String nextAsync(INotificationsUpdaterAsync updater);

    void nextAndUpdate(INotificationsUpdaterAsync updater, String name);
}
