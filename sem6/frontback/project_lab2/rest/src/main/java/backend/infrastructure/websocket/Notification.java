package backend.infrastructure.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/notifications/{clientName}")
public class Notification {
    private final static ConcurrentHashMap<String, Session> mapNamesSessions = new ConcurrentHashMap<>();

    public static void sendAll(String message) {
        try {
            for (Session sess : mapNamesSessions.values()) {
                if (sess.isOpen()) {
                    sess.getBasicRemote().sendText(message);
                }
            }
        } catch (IOException ioe) {
        }
    }

    public static void send(String clientName, String value) {
        try {
            if (clientName != null && !clientName.equals("admin")) {
                Session session = mapNamesSessions.get(clientName);
                session.getBasicRemote().sendText(value);
            }
        } catch (IOException ioe) {
        }
    }

    @OnOpen
    public void connectionOpened(Session session, @PathParam("clientName") String clientName) {
        mapNamesSessions.put(clientName, session);
    }

    @OnClose
    public void connectionClosed(Session session, @PathParam("clientName") String clientName) {
        mapNamesSessions.remove(clientName);
    }

}
