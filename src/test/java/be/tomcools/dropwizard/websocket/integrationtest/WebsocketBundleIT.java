package be.tomcools.dropwizard.websocket.integrationtest;

import be.tomcools.dropwizard.websocket.integrationtest.annotatedjavaee.PingPongClientEndpoint;
import org.junit.Before;
import org.junit.Test;

import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WebsocketBundleIT {
    Thread server;
    @Before
    public void init() throws InterruptedException {
        final IntegrationTestApplication integrationTestApplication = new IntegrationTestApplication();

        server = new Thread(new Runnable() {
            public void run() {
                try {
                    integrationTestApplication.run("server");
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        });
        server.run();

        integrationTestApplication.initLatch.await(7000, TimeUnit.SECONDS);
    }

    @Test
    public void canSendAndReceiveMessageThroughWebsockets() throws IOException, DeploymentException, InterruptedException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        Session session = container.connectToServer(PingPongClientEndpoint.class, URI.create("ws://localhost:8080/pingpong"));
        assertThat(session.isOpen(), is(true));

        sendMessage(session, "ping");

        assertThat(session.isOpen(), is(true));
    }

    private void sendMessage(final Session session, final String message) throws IOException, InterruptedException {

        new Thread(new Runnable() {
            public void run() {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }).run();

        Thread.sleep(2000);
    }
}
