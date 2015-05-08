package be.tomcools.dropwizard.websocket.registration;

import org.junit.Test;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EndpointRegistrationTest {

    private EndpointRegistration registration = new EndpointRegistration();

    @Test
    public void canAddAndReturnRegisteredEndpoints() {
        registration.add(TestEndpoint.class);

        Endpoints endpoints = registration.getRegisteredEndpoints();

        assertThat(endpoints.size(), equalTo(1));
    }

    @Test
    public void addingTheSameEndpointTwiceOnlyAddsItOnce() {
        registration.add(TestEndpoint.class);
        registration.add(TestEndpoint.class);

        Endpoints endpoints = registration.getRegisteredEndpoints();

        assertThat(endpoints.size(), equalTo(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifSuppliedClassDoesnotHaveServerEndpointAnnotationThrowsIllegalArgumentException() {
        registration.add(Object.class);
    }

    @ServerEndpoint("/chat")
    class TestEndpoint {
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
}
