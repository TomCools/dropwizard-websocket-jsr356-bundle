package be.tomcools.dropwizard.websocket.handling;

import be.tomcools.dropwizard.websocket.WebsocketHandler;
import io.dropwizard.server.ServerFactory;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.Server;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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

    @Before
    public void init() {
        when(serverFactory.build(environment)).thenReturn(server);
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
