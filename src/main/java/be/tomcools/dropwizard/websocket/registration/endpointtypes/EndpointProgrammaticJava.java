package be.tomcools.dropwizard.websocket.registration.endpointtypes;

import be.tomcools.dropwizard.websocket.registration.Endpoint;
import lombok.Builder;
import lombok.Value;

import javax.websocket.server.ServerEndpointConfig;

@Value
@Builder
public class EndpointProgrammaticJava extends Endpoint {
    private ServerEndpointConfig config;

    EndpointProgrammaticJava(Class<?> endpointClass, String path, ServerEndpointConfig config) {
        super(endpointClass, EndpointType.JAVA_PROGRAMMATIC_ENDPOINT, path);
        this.config = config;
    }
}
