package be.tomcools.dropwizard.websocket.registration;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class Endpoints implements Iterable<Endpoint> {
    private final Set<Endpoint> endpointList = new HashSet<>();

    public void add(Endpoint endpoint) {
        endpointList.add(endpoint);
    }

    public Optional<Endpoint> endpointForPath(String path) {
        for (Endpoint endpoint : endpointList) {
            if (endpoint.getPath().equals(path)) {
                return Optional.of(endpoint);
            }
        }
        return Optional.empty();
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
