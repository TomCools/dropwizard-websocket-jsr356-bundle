package be.tomcools.dropwizard.websocket.registration;


import java.util.*;

public class Endpoints implements Iterable<Endpoint> {
    private final List<Endpoint> endpointList = new ArrayList<>();

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
