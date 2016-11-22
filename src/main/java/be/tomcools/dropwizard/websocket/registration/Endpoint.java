package be.tomcools.dropwizard.websocket.registration;

import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
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

    public void setEndpointClass(Class<?> endpointClass) {
        this.endpointClass = endpointClass;
    }

    public EndpointType getType() {
        return type;
    }

    public void setType(EndpointType type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
