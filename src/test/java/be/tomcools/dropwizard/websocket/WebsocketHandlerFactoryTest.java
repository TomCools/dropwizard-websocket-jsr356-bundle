package be.tomcools.dropwizard.websocket;

import io.dropwizard.setup.Environment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class WebsocketHandlerFactoryTest {
    private final Environment environment = mock(Environment.class, RETURNS_DEEP_STUBS);

    @InjectMocks
    private WebsocketHandlerFactory sut;

    @Test
    public void whenCreatingHandlerForEnvironmentReturnsWebsocketHandler() {
        WebsocketHandler websocketHandler = sut.forEnvironment(environment);

        assertThat(websocketHandler, notNullValue());
    }
}
