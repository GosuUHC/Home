package backend.infrastructure.out.controller;

import backend.application.out.MessageSender;
import backend.application.out.Updatable;
import jakarta.inject.Inject;

public class NotificationsUpdater implements Updatable {

    @Inject
    MessageSender messageSender;

    @Override
    public void update(String clientID, String value) {
        messageSender.send(clientID, value);
    }

}