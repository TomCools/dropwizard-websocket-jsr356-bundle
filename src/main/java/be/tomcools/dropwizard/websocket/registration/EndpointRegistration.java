package be.tomcools.dropwizard.websocket.registration;

import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointAnnotatedJava;
import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointProgrammaticJava;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

public class EndpointRegistration {
    private Endpoints endpoints = new Endpoints();

    public void add(Class<?> endpointClass) {
        //TODO implement checks on Class; (has annotation/interface)

        String endpointPath = determineAnnotatedEndpointPath(endpointClass);

        Endpoint endpoint = new EndpointAnnotatedJava(endpointClass, endpointPath);

        endpoints.add(endpoint);
    }

    public void add(ServerEndpointConfig serverEndpointConfig) {
        //TODO implement checks on Class; (has annotation/interface)

        Endpoint endpoint = new EndpointProgrammaticJava(serverEndpointConfig);

        endpoints.add(endpoint);
    }

    // move to sort of ruleEngine
    private String determineAnnotatedEndpointPath(Class<?> endpointClass) {
        if (endpointClass.isAnnotationPresent(ServerEndpoint.class)) {
            return endpointClass.getAnnotation(ServerEndpoint.class).value();
        } else {
            throw new IllegalArgumentException(String.format("@ServerEndpoint annotation not found on Websocket-class: '%s'", endpointClass));
        }
    }

    public Endpoints getRegisteredEndpoints() {
        return endpoints;
    }
}
