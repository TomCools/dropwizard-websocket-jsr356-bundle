package be.tomcools.dropwizard.websocket.registration;

public class Endpoint {
    private Class<?> endpointClass;
    private EndpointType type;

    public Endpoint(Class<?> endpointClass, EndpointType type) {
        this.endpointClass = endpointClass;
        this.type = type;
    }

    public Class<?> getEndpointClass() {
        return endpointClass;
    }

    @Override
    public String toString() {
        return "Endpoint{" +
                "Class=" + endpointClass.getCanonicalName() +
                ", Type=" + type +
                '}';
    }
}
