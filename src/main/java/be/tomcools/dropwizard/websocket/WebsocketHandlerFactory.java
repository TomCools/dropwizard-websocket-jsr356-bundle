package be.tomcools.dropwizard.websocket;

import io.dropwizard.jetty.MutableServletContextHandler;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class WebsocketHandlerFactory {
    private ServerInstanceCollector serverCollector = new ServerInstanceCollector();

    public WebsocketHandler forEnvironment(Environment environment) {
        MutableServletContextHandler applicationContext = environment.getApplicationContext();
        Server server = serverCollector.findServerInstance(environment);

        applicationContext.setServer(server);

        return null;
    }
}
