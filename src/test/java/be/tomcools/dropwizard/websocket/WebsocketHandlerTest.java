package be.tomcools.dropwizard.websocket;

import be.tomcools.dropwizard.websocket.handling.WebsocketContainerInitializer;
import be.tomcools.dropwizard.websocket.registration.EndpointRegistration;
import be.tomcools.dropwizard.websocket.registration.Endpoints;
import io.dropwizard.core.Configuration;
import io.dropwizard.core.setup.Environment;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.ServerEndpointConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class WebsocketHandlerTest {

    private final Environment environment = mock(Environment.class, RETURNS_DEEP_STUBS);
    private final WebsocketBundleConfiguration configuration = (WebsocketBundleConfiguration)mock(Configuration.class,
            withSettings().defaultAnswer(RETURNS_DEEP_STUBS).extraInterfaces(WebsocketBundleConfiguration.class));

    @Mock
    private EndpointRegistration endpointRegistration;

    @Mock
    private WebsocketConfiguration wsConfiguration;

    @Mock
    private WebsocketContainerInitializer containerInitializer;

    @Mock
    private Endpoints endpoints;

    @InjectMocks
    private WebsocketHandler sut;

    @BeforeEach
    public void init() {
        lenient().when(endpointRegistration.getRegisteredEndpoints()).thenReturn(endpoints);
    }

    @Test
    public void canConstructHandlerWithEnvironment() {
        new WebsocketHandler(configuration.getWebsocketConfiguration(), environment);
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

        verify(containerInitializer).initialize(environment.getApplicationContext(), sut);   
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
