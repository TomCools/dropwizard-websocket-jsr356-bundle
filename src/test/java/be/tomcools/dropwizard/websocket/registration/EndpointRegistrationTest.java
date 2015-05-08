package be.tomcools.dropwizard.websocket.registration;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EndpointRegistrationTest {

    private EndpointRegistration registration = new EndpointRegistration();

    @Test
    public void canAddAndReturnRegisteredEndpoints() {
        registration.add(Object.class);
        registration.add(String.class);

        Endpoints endpoints = registration.getRegisteredEndpoints();

        assertThat(endpoints.size(), equalTo(2));
    }
}
