package be.tomcools.dropwizard.websocket.registration;

import com.google.common.base.Optional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Endpoints implements Iterable<Endpoint> {
    private Set<Endpoint> endpointList = new HashSet<>();

    public void add(Endpoint endpoint) {
        endpointList.add(endpoint);
    }

    public Optional<Endpoint> endpointForPath(String path) {
        for (Endpoint endpoint : endpointList) {
            if (endpoint.getPath().equals(path)) {
                return Optional.fromNullable(endpoint);
            }
        }
        return Optional.absent();
    }

    @Override
    public Iterator<Endpoint> iterator() {
        return endpointList.iterator();
    }

    public int size() {
        return endpointList.size();
    }

    public boolean isEmpty() {
        return endpointList.isEmpty();
    }
}
