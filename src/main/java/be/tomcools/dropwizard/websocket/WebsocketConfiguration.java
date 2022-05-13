package be.tomcools.dropwizard.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsocketConfiguration {

    @JsonProperty
    private Integer maxTextMessageBufferSize;
    @JsonProperty
    private Long asyncSendTimeout;
    @JsonProperty
    private Long maxSessionIdleTimeout;
    @JsonProperty
    private Integer maxBinaryMessageBufferSize;

}
