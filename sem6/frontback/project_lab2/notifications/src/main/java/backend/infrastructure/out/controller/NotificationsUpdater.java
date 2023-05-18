package backend.infrastructure.out.controller;

import backend.application.async.api.Updatable;

public class NotificationsUpdater implements Updatable {

    @Override
    public void update(String clientID, String value) {
        Notifications.send(clientID, value);
    }

}
// NEED SOME CHANGES!!