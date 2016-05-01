package be.tomcools.dropwizard.websocket;

import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.websocket.server.ServerEndpointConfig;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WebsocketBundleTest {
    private final Environment environment = mock(Environment.class, RETURNS_DEEP_STUBS);
    private final Configuration configuration = mock(Configuration.class,
            withSettings().defaultAnswer(RETURNS_DEEP_STUBS).extraInterfaces(WebsocketBundleConfiguration.class));

    @Mock
    private Bootstrap bootstrap;

    @Mock
    private WebsocketHandlerFactory websocketHandlerFactory;

    @Mock
    private WebsocketHandler websocketHandler;

    @InjectMocks
    private WebsocketBundle sut;

    @Before
    public void init() {
        when(websocketHandlerFactory.forEnvironment(any(WebsocketConfiguration.class),
                eq(environment))).thenReturn(websocketHandler);
    }

    @Test
    public void websocketBundleImplementsConfiguredBundleInterface() {
        assertTrue(ConfiguredBundle.class.isAssignableFrom(WebsocketBundle.class));
    }

    @Test
    public void initializeDoesNotUseTheBootstrapObject() {
        sut.initialize(bootstrap);

        verifyNoMoreInteractions(bootstrap);
    }

    @Test
    public void whenRunIsCalledRetrievesHandlerInstanceFromFactoryForTheSuppliedEnvironment() throws Exception {
        sut.run(configuration, environment);

        verify(websocketHandlerFactory).forEnvironment(((WebsocketBundleConfiguration) configuration).getWebsocketConfiguration(),
                environment);
    }

    @Test
    public void whenAddAnnotatedEndpointIsCalledDelegatesCallToWebsocketHandler() throws Exception {
        Class<?> testClass = this.getClass();
        sut.initialize(bootstrap);
        sut.run(configuration, environment);

        sut.addEndpoint(testClass);

        verify(websocketHandler).addEndpoint(testClass);
    }

    @Test
    public void whenAddProgrammaticEndpointIsCalledDelegatesCallToWebsocketHandler() throws Exception {
        Class<?> testClass = this.getClass();
        sut.initialize(bootstrap);
        sut.run(configuration, environment);

        ServerEndpointConfig config = ServerEndpointConfig.Builder.create(testClass, "/path").build();
        sut.addEndpoint(config);

        verify(websocketHandler).addEndpoint(config);
    }

    @Test
    public void whenConfigurationDoesNotImplementWebsocketConfigurationInterfaceUsesDefaultWebsocketConfiguration() {
        Configuration configuration = mock(Configuration.class,
                withSettings().defaultAnswer(RETURNS_DEEP_STUBS));

        Class<?> testClass = this.getClass();
        sut.initialize(bootstrap);
        sut.run(configuration, environment);

        sut.addEndpoint(testClass);

        verify(websocketHandlerFactory).forEnvironment(WebsocketBundle.DEFAULT_CONFIG, environment);
    }
}
