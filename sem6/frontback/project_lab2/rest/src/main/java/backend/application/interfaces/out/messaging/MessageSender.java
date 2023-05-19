package backend.application.interfaces.out.messaging;

public interface MessageSender {
    public void sendAll(String message);

    public void send(String clientID, String value);
}
