package backend.infrastructure.out.controller;

import java.util.concurrent.ConcurrentHashMap;

import backend.application.interfaces.in.async.timer.Timerable;
import backend.application.interfaces.out.messaging.MessageSender;
import backend.infrastructure.builder.Built;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/messages/{clientName}")
public class Messages implements MessageSender {

    @Inject
    @Built
    Timerable timer;

    private final static ConcurrentHashMap<String, Session> mapNamesSessions = new ConcurrentHashMap<>();

    @Override
    public void sendAll(String message) {
        for (Session sess : mapNamesSessions.values()) {
            if (sess.isOpen()) {
                sess.getAsyncRemote().sendText(message);
            }
        }
    }

    @Override
    public void send(String clientName, String value) {
        if (clientName != null) {
            Session session = mapNamesSessions.get(clientName);
            session.getAsyncRemote().sendText(value);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("clientName") String clientName) {
        mapNamesSessions.put(clientName, session);
        timer.addListener(clientName, () -> this.send(clientName, "Subscribe to our socials!"));
    }

    @OnClose
    public void onClose(Session session, @PathParam("clientName") String clientName) {
        mapNamesSessions.remove(clientName);
        timer.removeListener(clientName);
    }

}
