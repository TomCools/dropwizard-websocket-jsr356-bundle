package be.tomcools.dropwizard.websocket.registration;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Endpoint {
    private Class<?> endpointClass;
    private EndpointType type;
    private String path;


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
