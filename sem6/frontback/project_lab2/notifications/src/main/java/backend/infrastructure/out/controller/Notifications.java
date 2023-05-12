package backend.infrastructure.out.controller;

import java.util.concurrent.ConcurrentHashMap;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/notifications/orders/{clientName}")
public class Notifications {

    private final static ConcurrentHashMap<String, Session> mapNamesSessions = new ConcurrentHashMap<>();

    public static void sendAll(String valueJSON) {
        for (Session sess : mapNamesSessions.values()) {
            if (sess.isOpen()) {
                sess.getAsyncRemote().sendText(valueJSON);
            }
        }

    }

    public static void send(String clientName, String valueJSON) {
        if (clientName != null && !clientName.equals("admin")) {
            Session sess = mapNamesSessions.get(clientName);
            if (sess.isOpen()) {
                sess.getAsyncRemote().sendText(valueJSON);
            }
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
