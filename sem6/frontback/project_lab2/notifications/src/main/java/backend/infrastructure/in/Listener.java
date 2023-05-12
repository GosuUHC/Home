package backend.infrastructure.in;

import backend.application.async.api.Notificationable;
import backend.infrastructure.builder.Built;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

@MessageDriven(mappedName = "jms/queueToNotifications")
public class Listener implements MessageListener {

    @Inject
    @Built
    Notificationable app;

    @Override
    public void onMessage(Message message) {
        try {
            String login = message.getStringProperty("login");
            String value = message.getStringProperty("value");
            app.notificate(login, value);
        } catch (Exception e) {

        }

    }

}
