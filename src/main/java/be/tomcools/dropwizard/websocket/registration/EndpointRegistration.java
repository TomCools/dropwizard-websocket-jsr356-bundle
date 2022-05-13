package be.tomcools.dropwizard.websocket.registration;

import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointAnnotatedJava;
import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointProgrammaticJava;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.ServerEndpointConfig;

import java.util.Optional;

public class EndpointRegistration {
    private final Endpoints endpoints = new Endpoints();

    public void add(Class<?> endpointClass) {
        String endpointPath = determineAnnotatedEndpointPath(endpointClass);

        Endpoint endpoint = new EndpointAnnotatedJava(endpointClass, endpointPath);

        addEndpoint(endpoint);
    }

    public void add(ServerEndpointConfig serverEndpointConfig) {
        Endpoint endpoint = new EndpointProgrammaticJava(serverEndpointConfig);

        addEndpoint(endpoint);
    }

    private void addEndpoint(Endpoint endpoint) {
        Optional<Endpoint> existingEndpointWithSamePath = endpoints.endpointForPath(endpoint.getPath());
        if (existingEndpointWithSamePath.isPresent()) {
            throw new IllegalStateException("Registering endpoint " + endpoint + " failed: Another endpoint with the same path already registered: " + existingEndpointWithSamePath.get());
        } else {
            endpoints.add(endpoint);
        }
    }

    // move to sort of ruleEngine
    private String determineAnnotatedEndpointPath(Class<?> endpointClass) {
        if (endpointClass.isAnnotationPresent(ServerEndpoint.class)) {
            return endpointClass.getAnnotation(ServerEndpoint.class).value();
        } else {
            throw new IllegalArgumentException(String.format("@ServerEndpoint annotation not found on Websocket-class: '%s'. Either annotate the class or register it as a programmatic endpoint using ServerEndpointConfig.class", endpointClass));
        }
    }

    public Endpoints getRegisteredEndpoints() {
        return endpoints;
    }
}
