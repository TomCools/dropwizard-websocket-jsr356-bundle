package be.tomcools.dropwizard.websocket.integrationtest;

import be.tomcools.dropwizard.websocket.WebsocketBundle;
import be.tomcools.dropwizard.websocket.integrationtest.annotatedjavaee.PingPongServerEndpoint;
import be.tomcools.dropwizard.websocket.integrationtest.programmaticjavaee.ProgrammaticServerEndpoint;
import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.websocket.server.ServerEndpointConfig;
import java.util.concurrent.CountDownLatch;

public class IntegrationTestApplication extends Application<IntegrationConfiguration> {
    private WebsocketBundle websocket = new WebsocketBundle();
    CountDownLatch initLatch = new CountDownLatch(1);

    @Override
    public void initialize(Bootstrap<IntegrationConfiguration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.addBundle(websocket);
    }

    @Override
    public void run(IntegrationConfiguration configuration, Environment environment) throws Exception {
        //Annotated endpoint
        websocket.addEndpoint(PingPongServerEndpoint.class);

        //programmatic endpoint
        ServerEndpointConfig serverEndpointConfig = ServerEndpointConfig.Builder.create(ProgrammaticServerEndpoint.class, "/programmatic").build();
        websocket.addEndpoint(serverEndpointConfig);

        // healthcheck to keep output quiet
        environment.healthChecks().register("healthy", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        });

        initLatch.countDown();
    }
}
