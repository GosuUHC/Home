package backend.infrastructure.out.interconnector;

import backend.application.interfaces.out.messaging.Interconnectable;
import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Message;
import jakarta.jms.Queue;

public class Interconnector implements Interconnectable {

    @Resource(mappedName = "jms/conFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/queueToNotifications")
    private Queue queue;

    @Override
    public void notifyUser(String login, String value) {
        try {
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();
            Message message = context.createMessage();

            message.setStringProperty("login", login);
            message.setStringProperty("value", value);

            producer.send(queue, message);
        } catch (Exception e) {

        }
    }

}
