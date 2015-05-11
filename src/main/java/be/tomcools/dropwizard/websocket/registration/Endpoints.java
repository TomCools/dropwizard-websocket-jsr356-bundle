package be.tomcools.dropwizard.websocket.registration;

import com.google.common.base.Optional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Endpoints implements Iterable<Endpoint> {
    private Set<Endpoint> endpoints = new HashSet<>();

    public void add(Endpoint endpoint) {
        endpoints.add(endpoint);
    }

    public Optional<Endpoint> endpointForPath(String path) {
        for (Endpoint endpoint : endpoints) {
            if (endpoint.getPath().equals(path)) {
                return Optional.fromNullable(endpoint);
            }
        }
        return Optional.absent();
    }

    @Override
    public Iterator<Endpoint> iterator() {
        return endpoints.iterator();
    }

    public int size() {
        return endpoints.size();
    }

    public boolean isEmpty() {
        return endpoints.isEmpty();
    }
}
