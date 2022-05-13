package be.tomcools.dropwizard.websocket.registration;

import be.tomcools.dropwizard.websocket.registration.endpointtypes.EndpointType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
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
}
