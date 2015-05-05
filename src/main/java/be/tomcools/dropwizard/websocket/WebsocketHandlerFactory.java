package be.tomcools.dropwizard.websocket;

import io.dropwizard.jetty.MutableServletContextHandler;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;

public class WebsocketHandlerFactory {
    private ServerInstanceCollector serverCollector = new ServerInstanceCollector();
    private WebsocketContainerInitializer containerInitializer = new WebsocketContainerInitializer();

    public WebsocketHandler forEnvironment(Environment environment) {
        MutableServletContextHandler applicationContext = environment.getApplicationContext();
        Server server = serverCollector.findServerInstance(environment);

        applicationContext.setServer(server);

        ServerContainer container = containerInitializer.initialize(applicationContext);

        return new WebsocketHandler(container);
    }
}
