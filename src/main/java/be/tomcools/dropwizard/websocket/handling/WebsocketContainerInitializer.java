package be.tomcools.dropwizard.websocket.handling;


import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;

public class WebsocketContainerInitializer {
    public void initialize(ServletContextHandler contextHandler,
                           JakartaWebSocketServletContainerInitializer.Configurator configurator) {
        try {
            JakartaWebSocketServletContainerInitializer.configure(contextHandler, configurator);
        } catch (Exception e) {
            throw new IllegalStateException("Could not initialize context handler to enable Websockets", e);
        }
    }
}
