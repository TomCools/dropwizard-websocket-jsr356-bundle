package be.tomcools.dropwizard.websocket.integrationtest.annotatedjavaee;

import jakarta.websocket.*;

@ClientEndpoint
public class PingPongClientEndpoint {
    @OnOpen
    public void open(Session session) {
    }

    @OnClose
    public void close(Session session) {
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        if (!message.equals("pong")) {
            throw new IllegalArgumentException("Invalid message received: " + message);
        }
    }
}
