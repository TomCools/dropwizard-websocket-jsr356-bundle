package be.tomcools.dropwizard.websocket.integrationtest.annotatedjavaee;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint("/pingpong")
public class PingPongServerEndpoint {
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
    public void handleMessage(String message, Session session) throws IOException {
        if (!message.equals("ping")) {
            throw new IllegalArgumentException("Invalid message received: " + message);
        }
        session.getBasicRemote().sendText("pong");
    }
}
