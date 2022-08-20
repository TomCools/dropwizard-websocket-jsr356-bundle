package be.tomcools.dropwizard.websocket.handling;

import be.tomcools.dropwizard.websocket.WebsocketConfiguration;
import io.dropwizard.jetty.MutableServletContextHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler.Context;
import org.eclipse.jetty.websocket.server.WebSocketUpgradeFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WebsocketContainerInitializerTest {
    
    private MutableServletContextHandler servletContextHandler = mock(MutableServletContextHandler.class);
    private Context servletContext = mock(Context.class);
    private WebsocketConfiguration configuration = new WebsocketConfiguration();
    private Server server = mock(Server.class, RETURNS_DEEP_STUBS);

    @Mock
    private WebSocketUpgradeFilter upgradeFilter;

    @InjectMocks
    private WebsocketContainerInitializer sut;

    @Before
    public void init() {
        servletContextHandler.setAttribute(WebSocketUpgradeFilter.class.getName(), upgradeFilter);
        when(servletContextHandler.getServletContext()).thenReturn(servletContext);
        when(servletContextHandler.getServer()).thenReturn(server);
    }

    @Test
    public void canConstructServerContainerForServletHandlerContext() {
        WebsocketContainer container = sut.initialize(configuration, servletContextHandler);

        assertNotNull(container);
    }

    @Test(expected = IllegalStateException.class)
    public void whenSomethingGoesWrongDuringInitializeThrowsIllegalStateException() {
        when(servletContextHandler.getServer()).thenThrow(RuntimeException.class);
    
        sut.initialize(configuration, servletContextHandler);
    }
}
