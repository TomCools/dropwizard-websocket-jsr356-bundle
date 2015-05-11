package be.tomcools.dropwizard.websocket.registration;

import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class Endpoint {
    protected Class<?> endpointClass;
    protected EndpointType type;
    protected String path;

    protected Endpoint(Class<?> endpointClass, EndpointType type, String path) {
        this.endpointClass = endpointClass;
        this.type = type;
        this.path = path;
    }

    public Class<?> getEndpointClass() {
        return endpointClass;
    }

    public EndpointType getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Endpoint{" +
                "endpointClass=" + endpointClass +
                ", path='" + path + '\'' +
                '}';
    }

    public final String toLogString() {
        return String.format("\tGET\t\t%s (%s)", getPath(), getEndpointClass().getName());
    }
}
