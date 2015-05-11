package be.tomcools.dropwizard.websocket.integrationtest;

import be.tomcools.dropwizard.websocket.WebsocketBundle;
import be.tomcools.dropwizard.websocket.integrationtest.annotatedjavaee.PingPongServerEndpoint;
import be.tomcools.dropwizard.websocket.integrationtest.programmaticjavaee.ProgrammaticServerEndpoint;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.websocket.server.ServerEndpointConfig;

public class IntegrationTestApplication extends Application<IntegrationTestConfiguration> {
    private WebsocketBundle websocket = new WebsocketBundle();

    @Override
    public void initialize(Bootstrap<IntegrationTestConfiguration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.addBundle(websocket);
    }

    @Override
    public void run(IntegrationTestConfiguration configuration, Environment environment) throws Exception {
        //Annotated endpoint
        websocket.addEndpoint(PingPongServerEndpoint.class);

        //programmatic endpoint
        ServerEndpointConfig serverEndpointConfig = ServerEndpointConfig.Builder.create(ProgrammaticServerEndpoint.class, "/programmatic").build();
        websocket.addEndpoint(serverEndpointConfig);
    }
}
