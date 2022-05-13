package be.tomcools.dropwizard.websocket.integrationtest.programmaticjavaee;


import jakarta.websocket.Endpoint;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.MessageHandler;
import jakarta.websocket.Session;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgrammaticServerEndpoint extends Endpoint {
    @Override
    public void onOpen(final Session session, EndpointConfig endpointConfig) {
        session.addMessageHandler(new MessageHandler.Whole<String>() {

            @Override
            public void onMessage(String name) {
                try {
                    session.getBasicRemote().sendText("Hello");
                } catch (IOException ex) {
                    Logger.getLogger(ProgrammaticServerEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
