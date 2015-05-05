package be.tomcools.dropwizard.websocket;

import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class WebsocketHandlerTest {

    @Mock
    private ServerContainer serverContainer;

    @InjectMocks
    private WebsocketHandler sut;

    @Test
    public void canConstructHandlerWithServerContainer() {
        new WebsocketHandler(serverContainer);
    }

    @Test
    public void whenAddEndpointIsCalledPassesObjectToServerContainer() throws DeploymentException {
        sut.addEndpoint(TestEndpoint.class);

        verify(serverContainer).addEndpoint(TestEndpoint.class);
    }

    @ServerEndpoint("/chat")
    class TestEndpoint {
        @OnOpen
        public void open(Session session) {
        }

        @OnClose
        public void close(Session session) {
        }

        @OnError
        public void onError(Throwable error) {
        }

        @OnMessage
        public void handleMessage(String message, Session session) {
        }
    }

}
