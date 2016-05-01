package be.tomcools.dropwizard.websocket;

import be.tomcools.dropwizard.websocket.handling.ServerFactoryWrapper;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.server.ServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.websocket.server.ServerEndpointConfig;

public class WebsocketBundle implements ConfiguredBundle<WebsocketBundleConfiguration> {
    private WebsocketHandlerFactory handlerFactory = new WebsocketHandlerFactory();

    private WebsocketHandler handler;

    public WebsocketBundle() {
    }

    public void addEndpoint(Class<?> aClass) {
        handler.addEndpoint(aClass);
    }

    public void addEndpoint(ServerEndpointConfig serverEndpointConfig) {
        handler.addEndpoint(serverEndpointConfig);
    }

    public void run(WebsocketBundleConfiguration configuration, Environment environment) {
        handler = handlerFactory.forEnvironment(configuration.getWebsocketConfiguration(), environment);
        ServerFactory serverFactory = ((Configuration)configuration).getServerFactory();
        ServerFactoryWrapper factoryWrapper = new ServerFactoryWrapper(serverFactory, handler);
        ((Configuration)configuration).setServerFactory(factoryWrapper);
    }

    @Override
    public void initialize(Bootstrap bootstrap) {
        //This method is not used because no initialization logic is required.
    }
}
