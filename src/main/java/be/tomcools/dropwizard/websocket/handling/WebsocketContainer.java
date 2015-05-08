package be.tomcools.dropwizard.websocket.handling;

import be.tomcools.dropwizard.websocket.registration.Endpoint;
import be.tomcools.dropwizard.websocket.registration.Endpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;

public class WebsocketContainer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketContainer.class);

    private ServerContainer serverContainer;

    public WebsocketContainer(ServerContainer serverContainer) {
        this.serverContainer = serverContainer;
    }

    public void registerEndpoints(Endpoints endpoints) {
        StringBuilder endpointsAdded = new StringBuilder("Registered websocket endpoints: ")
                .append(System.lineSeparator())
                .append(System.lineSeparator());

        for (Endpoint endpoint : endpoints) {
            try {
                serverContainer.addEndpoint(endpoint.getEndpointClass());
                endpointsAdded.append("\t").append(endpoint).append(System.lineSeparator());
            } catch (DeploymentException e) {
                LOGGER.error("Could not add websocket endpoint {} to the deployment.", endpoint, e);
            }
        }

        LOGGER.info(endpointsAdded.toString());
    }
}
