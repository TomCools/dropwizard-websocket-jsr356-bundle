package be.tomcools.dropwizard.websocket.handling;


import org.eclipse.jetty.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import io.dropwizard.jetty.MutableServletContextHandler;

public class WebsocketContainerInitializer {
    public void initialize(MutableServletContextHandler contextHandler,
                           JakartaWebSocketServletContainerInitializer.Configurator configurator) {
        try {
            JakartaWebSocketServletContainerInitializer.configure(contextHandler, configurator);
        } catch (Exception e) {
            throw new IllegalStateException("Could not initialize contexthandler to enable Websockets", e);
        }
    }
}
