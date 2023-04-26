package be.tomcools.dropwizard.websocket.registration;

import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointProgrammaticJava;
import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointType;
import jakarta.websocket.Endpoint;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.ServerEndpointConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EndpointRegistrationTest {

    private EndpointRegistration registration = new EndpointRegistration();

    @Test
    public void canAddAndReturnRegisteredEndpoints() {
        registration.add(AnnotatedEndpoint.class);

        Endpoints endpoints = registration.getRegisteredEndpoints();

        assertThat(endpoints.size(), equalTo(1));
    }

    @Test
    public void addingAnnotatedEndpointCorrectlyRegistersIt() {
        registration.add(AnnotatedEndpoint.class);

        Endpoints endpoints = registration.getRegisteredEndpoints();

        for (be.tomcools.dropwizard.websocket.registration.Endpoint endpoint : endpoints) {
            assertThat(endpoint.getEndpointClass().getCanonicalName(), is(AnnotatedEndpoint.class.getCanonicalName()));
            assertThat(endpoint.getPath(), is("/chat"));
            assertThat(endpoint.getType(), is(EndpointType.JAVA_ANNOTATED_ENDPOINT));
        }
    }

    @Test
    public void addingProgrammaticEndpointCorrectlyRegistersIt() {
        ServerEndpointConfig config = ServerEndpointConfig.Builder.create(ProgrammaticEndpoint.class, "/path").build();
        registration.add(config);

        Endpoints endpoints = registration.getRegisteredEndpoints();

        for (be.tomcools.dropwizard.websocket.registration.Endpoint endpoint : endpoints) {
            assertThat(endpoint.getEndpointClass().getCanonicalName(), is(ProgrammaticEndpoint.class.getCanonicalName()));
            assertThat(endpoint.getPath(), is("/path"));
            assertThat(endpoint.getType(), is(EndpointType.JAVA_PROGRAMMATIC_ENDPOINT));
            assertThat(((EndpointProgrammaticJava) endpoint).getConfig(), is(config));
        }
    }

    @Test
    public void ifSuppliedClassDoesnotHaveServerEndpointAnnotationThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                registration.add(Object.class));
    }

    @Test
    public void addingTwoEndpointsWithSamePathCausesException() {
        registration.add(AnnotatedEndpoint.class);
        Assertions.assertThrows(IllegalStateException.class,
                () -> registration.add(AnnotatedEndpoint.class));

    }

    @ServerEndpoint("/chat")
    class AnnotatedEndpoint {
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

    class ProgrammaticEndpoint extends Endpoint {
        @Override
        public void onOpen(Session session, EndpointConfig endpointConfig) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
