package be.tomcools.dropwizard.websocket;

import be.tomcools.dropwizard.websocket.handling.WebsocketContainer;
import be.tomcools.dropwizard.websocket.handling.WebsocketContainerInitializer;
import be.tomcools.dropwizard.websocket.registration.EndpointRegistration;
import be.tomcools.dropwizard.websocket.registration.Endpoints;
import io.dropwizard.jetty.MutableServletContextHandler;
import io.dropwizard.setup.Environment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class WebsocketHandlerTest {

    private final Environment environment = mock(Environment.class, RETURNS_DEEP_STUBS);

    @Mock
    private EndpointRegistration endpointRegistration;

    @Mock
    private WebsocketContainerInitializer containerInitializer;

    @Mock
    private WebsocketContainer container;

    @Mock
    private Endpoints endpoints;

    @InjectMocks
    private WebsocketHandler sut;

    @Before
    public void init() {
        when(containerInitializer.initialize(any(MutableServletContextHandler.class))).thenReturn(container);
        when(endpointRegistration.getRegisteredEndpoints()).thenReturn(endpoints);
    }

    @Test
    public void canConstructHandlerWithEnvironment() {
        new WebsocketHandler(environment);
    }

    @Test
    public void whenAddAnnotatedEndpointIsCalledPassesObjectToEndpointRegistration() {
        sut.addEndpoint(TestEndpoint.class);

        verify(endpointRegistration).add(TestEndpoint.class);
    }

    @Test
    public void whenAddProgrammaticEndpointIsCalledPassesObjectToEndpointRegistration() {
        ServerEndpointConfig config = ServerEndpointConfig.Builder.create(TestEndpoint.class, "/path").build();
        sut.addEndpoint(config);

        verify(endpointRegistration).add(config);
    }

    @Test
    public void whenInitializeIsCalled_InitializesWebsocketContainer() {
        sut.initialize();

        verify(containerInitializer).initialize(environment.getApplicationContext());
    }

    @Test
    public void whenInitializeIsCalled_addsRegisteredEndpointsToWebsocketContainer() {
        sut.initialize();

        verify(container).registerEndpoints(endpoints);
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
