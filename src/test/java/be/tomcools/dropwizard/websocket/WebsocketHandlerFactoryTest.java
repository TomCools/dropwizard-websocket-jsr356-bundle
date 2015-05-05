package be.tomcools.dropwizard.websocket;

import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.Server;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WebsocketHandlerFactoryTest {
    private final Environment environment = mock(Environment.class, RETURNS_DEEP_STUBS);


    @Mock
    private ServerInstanceCollector serverInstanceCollector;

    @Mock
    private WebsocketContainerInitializer websocketContainerInitializer;

    @Mock
    private Server server;


    @InjectMocks
    private WebsocketHandlerFactory sut;

    @Before
    public void init() {
        when(serverInstanceCollector.findServerInstance(environment)).thenReturn(server);
    }

    @Test
    public void whenCreatingHandlerForEnvironmentPassesEnviromentToServerCollector() {
        sut.forEnvironment(environment);

        verify(serverInstanceCollector).findServerInstance(environment);
    }

    @Test
    public void whenServerInstanceIsRetrievedSetsItOnMutableContext() {
        sut.forEnvironment(environment);

        verify(environment.getApplicationContext()).setServer(server);
    }

    @Test
    public void applicationContextIsPassedToContainerInitializer() {
        sut.forEnvironment(environment);

        verify(websocketContainerInitializer).initialize(environment.getApplicationContext());
    }

    @Test
    public void canCreateWebsocketHandlerForEnvironment() {
        WebsocketHandler websocketHandler = sut.forEnvironment(environment);

        assertThat("WebsocketHandler was null", websocketHandler != null);
    }

}
