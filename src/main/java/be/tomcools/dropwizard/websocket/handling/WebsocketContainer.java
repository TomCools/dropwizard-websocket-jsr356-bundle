package be.tomcools.dropwizard.websocket.handling;

import be.tomcools.dropwizard.websocket.registration.Endpoint;
import be.tomcools.dropwizard.websocket.registration.Endpoints;
import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointProgrammaticJava;
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
        StringBuilder endpointsAdded = new StringBuilder("Registered websocket endpointtypes: ")
                .append(System.lineSeparator())
                .append(System.lineSeparator());

        for (Endpoint endpoint : endpoints) {
            try {
                register(endpoint);
                endpointsAdded.append(endpoint.toLogString()).append(System.lineSeparator());
            } catch (DeploymentException e) {
                LOGGER.error("Could not add websocket endpoint {} to the deployment.", endpoint, e);
            }
        }

        LOGGER.info(endpointsAdded.toString());
    }

    private void register(Endpoint endpoint) throws DeploymentException {
        switch (endpoint.getType()) {
            case JAVA_ANNOTATED_ENDPOINT:
                serverContainer.addEndpoint(endpoint.getEndpointClass());
                break;
            case JAVA_PROGRAMMATIC_ENDPOINT:
                serverContainer.addEndpoint(((EndpointProgrammaticJava) endpoint).getConfig());
                break;
            default:
                throw new DeploymentException(String.format("No registering logic has been defined for endpoint type: %s", endpoint.getType()));
        }
    }
}
