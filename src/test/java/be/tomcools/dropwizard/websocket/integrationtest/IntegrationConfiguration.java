package be.tomcools.dropwizard.websocket.integrationtest;

import be.tomcools.dropwizard.websocket.WebsocketBundleConfiguration;
import be.tomcools.dropwizard.websocket.WebsocketConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class IntegrationConfiguration extends Configuration implements WebsocketBundleConfiguration {

    @Valid
    @NotNull
    @JsonProperty
    private final WebsocketConfiguration websocketConfiguration = new WebsocketConfiguration();

    @Override
    public WebsocketConfiguration getWebsocketConfiguration() {
        return websocketConfiguration;
    }
}
