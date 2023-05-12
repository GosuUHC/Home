package backend.infrastructure.out.controller;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/messages/{clientName}")
public class Messages {

    private final static ConcurrentHashMap<String, Session> mapNamesSessions = new ConcurrentHashMap<>();

    public static void sendAll(String message) {
        for (Session sess : mapNamesSessions.values()) {
            if (sess.isOpen()) {
                try {
                    sess.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void send(String clientName, String value) {
        if (clientName != null) {
            Session session = mapNamesSessions.get(clientName);
            session.getAsyncRemote().sendText(value);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("clientName") String clientName) {
        mapNamesSessions.put(clientName, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("clientName") String clientName) {
        mapNamesSessions.remove(clientName);
    }

}
