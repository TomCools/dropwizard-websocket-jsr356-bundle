package be.tomcools.dropwizard.websocket.integrationtest;

import be.tomcools.dropwizard.websocket.WebsocketBundleConfiguration;
import be.tomcools.dropwizard.websocket.WebsocketConfiguration;
import io.dropwizard.Configuration;

public class IntegrationConfiguration extends Configuration implements WebsocketBundleConfiguration {
    @Override
    public WebsocketConfiguration getWebsocketConfiguration() {
        return null;
    }
}
