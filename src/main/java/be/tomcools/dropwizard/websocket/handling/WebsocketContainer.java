package be.tomcools.dropwizard.websocket.handling;

import be.tomcools.dropwizard.websocket.WebsocketConfiguration;
import be.tomcools.dropwizard.websocket.registration.Endpoint;
import be.tomcools.dropwizard.websocket.registration.Endpoints;
import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointProgrammaticJava;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.server.ServerContainer;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class WebsocketContainer {

    private final ServerContainer serverContainer;

    public WebsocketContainer(WebsocketConfiguration configuration, ServerContainer serverContainer) {
        this.serverContainer = serverContainer;

        Optional.ofNullable(configuration.getMaxSessionIdleTimeout())
                .ifPresent(this.serverContainer::setDefaultMaxSessionIdleTimeout);

        Optional.ofNullable(configuration.getAsyncSendTimeout())
                .ifPresent(this.serverContainer::setAsyncSendTimeout);

        Optional.ofNullable(configuration.getMaxBinaryMessageBufferSize())
                .ifPresent(this.serverContainer::setDefaultMaxBinaryMessageBufferSize);

        Optional.ofNullable(configuration.getMaxTextMessageBufferSize())
                .ifPresent(this.serverContainer::setDefaultMaxTextMessageBufferSize);
    }

    public void registerEndpoints(final Endpoints endpoints) {
        final Endpoints succesfullyAdded = new Endpoints();

        for (Endpoint endpoint : endpoints) {
            try {
                register(endpoint);
                succesfullyAdded.add(endpoint);
            } catch (DeploymentException e) {
                log.error("Could not add websocket endpoint {} to the deployment.", endpoint, e);
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

        log.info(endpointsAdded.toString());

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
