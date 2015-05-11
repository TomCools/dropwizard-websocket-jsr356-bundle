package be.tomcools.dropwizard.websocket.registration;

import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Endpoint {
    protected Class<?> endpointClass;
    protected EndpointType type;
    protected String path;

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

    public String toLogString() {
        return String.format("\tGET\t\t%s (%s)", path, endpointClass.getName());
    }
}
