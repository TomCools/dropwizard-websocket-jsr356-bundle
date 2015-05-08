package be.tomcools.dropwizard.websocket.handling;

import be.tomcools.dropwizard.websocket.registration.Endpoint;
import be.tomcools.dropwizard.websocket.registration.EndpointType;
import be.tomcools.dropwizard.websocket.registration.Endpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WebsocketContainerTest {

    @Mock
    private ServerContainer container;

    @InjectMocks
    private WebsocketContainer sut;

    @Test
    public void canConstructWebsocketContainerWithServercontainer() {
        new WebsocketContainer(container);
    }

    @Test
    public void whenRegisterEndpointsIsCalledAddsEndpointsToContainer() throws DeploymentException {
        Endpoints endpoints = new Endpoints();
        endpoints.add(new Endpoint(Object.class, EndpointType.JAVA_SERVER_ENDPOINT));
        endpoints.add(new Endpoint(String.class, EndpointType.JAVA_SERVER_ENDPOINT));

        sut.registerEndpoints(endpoints);

        verify(container, times(2)).addEndpoint(any(Class.class));
    }

    @Test
    public void whenNoEndpointsWhereRegisteredDoesNotAddAnythingToTheContainer() throws DeploymentException {
        Endpoints endpoints = new Endpoints();

        sut.registerEndpoints(endpoints);

        verify(container, never()).addEndpoint(any(Class.class));
    }

    @Test
    public void whenDeploymentExceptionOccursWhenTryingToLoadAClassStillAddsOthers() throws DeploymentException {
        Endpoints endpoints = new Endpoints();
        endpoints.add(new Endpoint(Object.class, EndpointType.JAVA_SERVER_ENDPOINT));
        endpoints.add(new Endpoint(String.class, EndpointType.JAVA_SERVER_ENDPOINT));
        doThrow(DeploymentException.class).when(container).addEndpoint(Object.class);

        sut.registerEndpoints(endpoints);

        verify(container, times(2)).addEndpoint(any(Class.class));
    }
}
