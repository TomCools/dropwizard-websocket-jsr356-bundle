package be.tomcools.dropwizard.websocket.handling;

import be.tomcools.dropwizard.websocket.WebsocketHandler;
import io.dropwizard.jetty.MutableServletContextHandler;
import jakarta.servlet.ServletException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.servlet.WebSocketUpgradeFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WebsocketContainerInitializerTest {

    private MutableServletContextHandler servletContextHandler = mock(MutableServletContextHandler.class);
    private Server server = mock(Server.class, RETURNS_DEEP_STUBS);

    @Mock
    private WebSocketUpgradeFilter upgradeFilter;

    @InjectMocks
    private WebsocketContainerInitializer sut;

    @Mock
    private WebsocketHandler webSocketHandler;

    @Before
    public void init() {
        servletContextHandler.setAttribute(WebSocketUpgradeFilter.class.getName(), upgradeFilter);
        when(servletContextHandler.getServer()).thenReturn(server);
    }

    @Test(expected = IllegalStateException.class)
    public void whenSomethingGoesWrongDuringInitializeThrowsIllegalStateException() {
        when(servletContextHandler.getServer()).thenThrow(ServletException.class);
        sut.initialize(servletContextHandler, webSocketHandler);
    }
}
