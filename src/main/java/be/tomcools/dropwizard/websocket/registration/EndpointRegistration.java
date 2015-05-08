package be.tomcools.dropwizard.websocket.registration;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

public class EndpointRegistration {
    private Endpoints endpoints = new Endpoints();

    public void add(Class<?> endpointClass) {
        //TODO implement checks on Class; (has annotation/interface... log parameters)

        Endpoint endpoint = Endpoint.builder()
                .endpointClass(endpointClass)
                .type(EndpointType.JAVA_ANNOTATED_ENDPOINT)
                .path(determineAnnotatedEndpointPath(endpointClass))
                .build();

        endpoints.add(endpoint);
    }

    // move to sort of ruleEngine
    private String determineAnnotatedEndpointPath(Class<?> endpointClass) {
        if (endpointClass.isAnnotationPresent(ServerEndpoint.class)) {
            return endpointClass.getAnnotation(ServerEndpoint.class).value();
        } else {
            throw new IllegalArgumentException(String.format("@ServerEndpoint annotation not found on Websocket-class: '%s'"));
        }
    }

    public Endpoints getRegisteredEndpoints() {
        return endpoints;
    }
}
