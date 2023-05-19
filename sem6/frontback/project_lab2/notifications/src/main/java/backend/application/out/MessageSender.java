package backend.application.out;

public interface MessageSender {
    public void sendAll(String message);

    public void send(String clientID, String value);
}
