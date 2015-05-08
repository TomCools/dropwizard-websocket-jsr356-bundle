package be.tomcools.dropwizard.websocket.registration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Endpoints implements Iterable<Endpoint> {
    private Set<Endpoint> endpoints = new HashSet<>();

    public void add(Endpoint endpoint) {
        endpoints.add(endpoint);
    }

    @Override
    public Iterator<Endpoint> iterator() {
        return endpoints.iterator();
    }

    public int size() {
        return endpoints.size();
    }
}
