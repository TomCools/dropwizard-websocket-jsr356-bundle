package be.tomcools.dropwizard.websocket.handling;

import be.tomcools.dropwizard.websocket.WebsocketHandler;
import io.dropwizard.core.server.ServerFactory;
import io.dropwizard.core.setup.Environment;
import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServerFactoryWrapperTest {
    private final Environment environment = mock(Environment.class, RETURNS_DEEP_STUBS);

    @Mock
    private WebsocketHandler handler;
    @Mock
    private ServerFactory serverFactory;
    @Mock
    private Server server;

    @InjectMocks
    private ServerFactoryWrapper wrapper;

    @BeforeEach
    public void init() {
        lenient().when(serverFactory.build(environment)).thenReturn(server);
    }

    @Test
    public void canConstructWrapperWithHandlerAndServerFactory() {
        new ServerFactoryWrapper(serverFactory, handler);
    }

    @Test
    public void whenBuildIsCalledForEnvironmentCallsBuildOnWrappedObject() {
        wrapper.build(environment);

        verify(serverFactory).build(environment);
    }

    @Test
    public void whenBuildIsCalledSetsBuiltServerObjectOnTheApplicationContext() {
        wrapper.build(environment);

        verify(environment.getApplicationContext()).setServer(server);
    }

    @Test
    public void whenBuildIsCalledSetsBuiltServerObjectOnTheAdminContext() {
        wrapper.build(environment);

        verify(environment.getAdminContext()).setServer(server);
    }

    @Test
    public void whenBuildIsCalledSetsReturnsOriginalServerObject() {
        Server buildServer = wrapper.build(environment);

        assertThat(buildServer, is(server));

    }
}
