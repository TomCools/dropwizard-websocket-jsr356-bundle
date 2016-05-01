package be.tomcools.dropwizard.websocket;

import be.tomcools.dropwizard.websocket.handling.WebsocketContainer;
import be.tomcools.dropwizard.websocket.handling.WebsocketContainerInitializer;
import be.tomcools.dropwizard.websocket.registration.EndpointRegistration;
import io.dropwizard.setup.Environment;

import javax.websocket.server.ServerEndpointConfig;

public class WebsocketHandler {
    private EndpointRegistration endpointRegistration;
    private final WebsocketConfiguration configuration;
    private Environment environment;
    private WebsocketContainerInitializer containerInitializer;

    public WebsocketHandler(WebsocketConfiguration configuration, Environment environment) {
        this(configuration, environment, new EndpointRegistration(), new WebsocketContainerInitializer());
    }

    public WebsocketHandler(WebsocketConfiguration configuration, Environment environment, EndpointRegistration endpointRegistration, WebsocketContainerInitializer containerInitializer) {
        this.configuration = configuration;
        this.environment = environment;
        this.endpointRegistration = endpointRegistration;
        this.containerInitializer = containerInitializer;
    }

    public void addEndpoint(Class<?> endpointClass) {
        this.endpointRegistration.add(endpointClass);
    }

    public void addEndpoint(ServerEndpointConfig serverEndpointConfig) {
        this.endpointRegistration.add(serverEndpointConfig);
    }

    public void initialize() {
        WebsocketContainer serverContainer = containerInitializer.initialize(configuration, environment.getApplicationContext());
        serverContainer.registerEndpoints(endpointRegistration.getRegisteredEndpoints());
    }


}
