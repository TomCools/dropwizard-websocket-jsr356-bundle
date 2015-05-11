package be.tomcools.dropwizard.websocket;

import be.tomcools.dropwizard.websocket.integrationtest.IntegrationTestApplication;
import be.tomcools.dropwizard.websocket.integrationtest.annotatedjavaee.PingPongClientEndpoint;
import org.junit.Before;
import org.junit.Test;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import java.io.IOException;
import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class WebsocketBundleIT {
    Thread server;
    @Before
    public void init() throws InterruptedException {
        server = new Thread(new Runnable() {
            public void run() {
                try {
                    new IntegrationTestApplication().run("server");
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        });
        server.run();

        Thread.sleep(7000);
    }

    @Test
    public void canSendAndReceiveMessageThroughWebsockets() throws IOException, DeploymentException, InterruptedException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        Session session = container.connectToServer(PingPongClientEndpoint.class, URI.create("ws://localhost:8080/pingpong"));
        assertThat(session.isOpen(), is(true));

        sendMessage(session, "ping");

        assertThat(session.isOpen(), is(true));
        assertThat(server.isAlive(), is(true));
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
