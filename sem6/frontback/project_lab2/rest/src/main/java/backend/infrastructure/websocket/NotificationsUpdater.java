package backend.infrastructure.websocket;

import backend.application.interfaces.async.INotificationsUpdaterAsync;

public class NotificationsUpdater implements INotificationsUpdaterAsync {

    @Override
    public void update(int value, String clientName) {
        Notification.send(clientName, String.valueOf(value));
    }

}
