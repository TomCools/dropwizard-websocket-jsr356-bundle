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

    public void registerEndpoints(final Endpoints endpoints) {
        final Endpoints succesfullyAdded = new Endpoints();

        for (Endpoint endpoint : endpoints) {
            try {
                register(endpoint);
                succesfullyAdded.add(endpoint);
            } catch (DeploymentException e) {
                LOGGER.error("Could not add websocket endpoint {} to the deployment.", endpoint, e);
            }
        }
        logRegisteredEndpoints(succesfullyAdded);
    }

    private void logRegisteredEndpoints(Endpoints succesfullyAdded) {
        StringBuilder endpointsAdded = new StringBuilder("Registered websocket endpoints: ")
                .append(System.lineSeparator())
                .append(System.lineSeparator());

        if (succesfullyAdded.isEmpty()) {
            endpointsAdded.append("\tNONE \tNo endpoints were added to the server. Check logs for errors if you registered endpoints.").append(System.lineSeparator());
        } else {
            for (Endpoint endpoint : succesfullyAdded) {
                String endpointLogString = String.format("\tGET\t\t%s (%s)", endpoint.getPath(), endpoint.getEndpointClass().getName());
                endpointsAdded.append(endpointLogString).append(System.lineSeparator());
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
