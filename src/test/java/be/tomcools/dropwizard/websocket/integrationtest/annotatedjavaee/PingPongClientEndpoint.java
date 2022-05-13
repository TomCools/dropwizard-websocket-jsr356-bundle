package be.tomcools.dropwizard.websocket.integrationtest.annotatedjavaee;

import jakarta.websocket.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;

@ClientEndpoint
@Slf4j
public class PingPongClientEndpoint {

    Session session = null;
    private MessageHandler handler;

    public PingPongClientEndpoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    public void addMessageHandler(MessageHandler msgHandler) {
        this.handler = msgHandler;
    }

    @OnMessage
    public void processMessage(String message) {
       log.info("Client: Received message: " + message);
        if(handler != null) {
            handler.handleMessage(message);
        }
    }

    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            log.error("Couldn't send message", ex);
        }
    }


    public interface MessageHandler {

        void handleMessage(String message);
    }
}
