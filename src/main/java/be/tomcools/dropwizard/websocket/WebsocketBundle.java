package be.tomcools.dropwizard.websocket;

import be.tomcools.dropwizard.websocket.handling.ServerFactoryWrapper;
import io.dropwizard.core.Configuration;
import io.dropwizard.core.ConfiguredBundle;
import io.dropwizard.core.server.ServerFactory;
import io.dropwizard.core.setup.Environment;

import jakarta.websocket.server.ServerEndpointConfig;

public class WebsocketBundle<T extends Configuration> implements ConfiguredBundle<T> {
    private WebsocketHandlerFactory handlerFactory = new WebsocketHandlerFactory();
    final static WebsocketConfiguration DEFAULT_CONFIG = new WebsocketConfiguration();

    private WebsocketHandler handler;

    public WebsocketBundle() {
    }

    public void addEndpoint(Class<?> aClass) {
        handler.addEndpoint(aClass);
    }

    public void addEndpoint(ServerEndpointConfig serverEndpointConfig) {
        handler.addEndpoint(serverEndpointConfig);
    }

    public void run(T configuration, Environment environment) {
        handler = determineHandler(configuration, environment);
        ServerFactory serverFactory = configuration.getServerFactory();
        ServerFactoryWrapper factoryWrapper = new ServerFactoryWrapper(serverFactory, handler);
        configuration.setServerFactory(factoryWrapper);
    }

    private WebsocketHandler determineHandler(T configuration, Environment environment) {
        if (configuration instanceof WebsocketBundleConfiguration) {
            return handlerFactory.forEnvironment(((WebsocketBundleConfiguration) configuration).getWebsocketConfiguration(), environment);
        } else {
            return handlerFactory.forEnvironment(DEFAULT_CONFIG, environment);
        }
    }

}
