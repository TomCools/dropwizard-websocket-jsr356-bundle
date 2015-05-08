package be.tomcools.dropwizard.websocket.registration;

public class EndpointRegistration {
    private Endpoints endpoints = new Endpoints();

    public void add(Class<?> endpointClass) {
        //TODO implement checks on Class; (has annotation/interface... log parameters)

        Endpoint endpoint = new Endpoint(endpointClass, EndpointType.JAVA_SERVER_ENDPOINT);

        endpoints.add(endpoint);
    }

    public Endpoints getRegisteredEndpoints() {
        return endpoints;
    }
}
