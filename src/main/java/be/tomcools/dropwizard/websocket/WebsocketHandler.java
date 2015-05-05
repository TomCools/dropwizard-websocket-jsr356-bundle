package be.tomcools.dropwizard.websocket;

import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DeploymentException;

public class WebsocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketHandler.class);
    private final ServerContainer container;

    public WebsocketHandler(ServerContainer container) {
        this.container = container;
    }

    public void addEndpoint(Class<?> endpointClass) {
        //TODO implement checks on Class; (has annotation/interface... log parameters)
        try {
            container.addEndpoint(endpointClass);
        } catch (DeploymentException e) {
            LOGGER.error("Could not add websocket endpoint {} to the deployment.", endpointClass, e);
        }
    }
}
