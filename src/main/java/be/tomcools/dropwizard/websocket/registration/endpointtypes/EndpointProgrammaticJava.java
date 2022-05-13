package be.tomcools.dropwizard.websocket.registration.endpointtypes;

import be.tomcools.dropwizard.websocket.registration.Endpoint;
import jakarta.websocket.server.ServerEndpointConfig;

public class EndpointProgrammaticJava extends Endpoint {
    private final ServerEndpointConfig config;

    public EndpointProgrammaticJava(ServerEndpointConfig config) {
        super(config.getEndpointClass(), EndpointType.JAVA_PROGRAMMATIC_ENDPOINT, config.getPath());
        this.config = config;
    }

    public ServerEndpointConfig getConfig() {
        return config;
    }


}
