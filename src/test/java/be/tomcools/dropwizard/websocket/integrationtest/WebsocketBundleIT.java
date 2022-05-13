package be.tomcools.dropwizard.websocket.integrationtest;

import be.tomcools.dropwizard.websocket.integrationtest.annotatedjavaee.PingPongClientEndpoint;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(DropwizardExtensionsSupport.class)
public class WebsocketBundleIT {

    private static final DropwizardAppExtension<IntegrationConfiguration> EXT = new DropwizardAppExtension<>(
            IntegrationTestApplication.class,
            ResourceHelpers.resourceFilePath("integration-test-config.yaml")
    );

    @Test
    public void canSendAndReceiveMessageThroughWebsockets() throws Exception {
        URI uri = URI.create(String.format("ws://localhost:%s/pingpong", EXT.getLocalPort()));

        PingPongClientEndpoint client = new PingPongClientEndpoint(uri);
        final AtomicReference<String> received = new AtomicReference<>();
        client.addMessageHandler(received::set);

        client.sendMessage("ping");

        Awaitility.await()
                .untilAsserted(() -> assertThat(received.get()).isEqualTo("pong"));
    }
}
