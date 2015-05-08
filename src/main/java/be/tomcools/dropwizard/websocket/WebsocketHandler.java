package be.tomcools.dropwizard.websocket;

import be.tomcools.dropwizard.websocket.handling.WebsocketContainer;
import be.tomcools.dropwizard.websocket.handling.WebsocketContainerInitializer;
import be.tomcools.dropwizard.websocket.registration.EndpointRegistration;
import io.dropwizard.setup.Environment;

public class WebsocketHandler {
    private EndpointRegistration endpointRegistration;
    private Environment environment;
    private WebsocketContainerInitializer containerInitializer;

    public WebsocketHandler(Environment environment) {
        this(environment, new EndpointRegistration(), new WebsocketContainerInitializer());
    }

    public WebsocketHandler(Environment environment, EndpointRegistration endpointRegistration, WebsocketContainerInitializer containerInitializer) {
        this.environment = environment;
        this.endpointRegistration = endpointRegistration;
        this.containerInitializer = containerInitializer;
    }

    public void addEndpoint(Class<?> endpointClass) {
        this.endpointRegistration.add(endpointClass);
    }

    public void initialize() {
        WebsocketContainer serverContainer = containerInitializer.initialize(environment.getApplicationContext());
        serverContainer.registerEndpoints(endpointRegistration.getRegisteredEndpoints());
    }
}
