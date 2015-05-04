package be.tomcools.dropwizard.websocket;

import io.dropwizard.Bundle;
import io.dropwizard.setup.Environment;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

public class WebsocketBundleTest {
    private final Environment environment = mock(Environment.class, RETURNS_DEEP_STUBS);

    @Test
    public void websocketBundleImplementsBundleInterface() {
        assertTrue(Bundle.class.isAssignableFrom(WebsocketBundle.class));
    }
}
