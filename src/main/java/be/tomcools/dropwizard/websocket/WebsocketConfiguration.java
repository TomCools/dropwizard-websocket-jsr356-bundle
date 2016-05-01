package be.tomcools.dropwizard.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebsocketConfiguration {

    @JsonProperty
    private Integer maxTextMessageBufferSize;
    @JsonProperty
    private Long asyncSendTimeout;
    @JsonProperty
    private Long maxSessionIdleTimeout;
    @JsonProperty
    private Integer maxBinaryMessageBufferSize;


    public Long getAsyncSendTimeout() {
        return asyncSendTimeout;
    }

    public Integer getMaxTextMessageBufferSize() {
        return maxTextMessageBufferSize;
    }

    public Long getMaxSessionIdleTimeout() {
        return maxSessionIdleTimeout;
    }

    public Integer getMaxBinaryMessageBufferSize() {
        return maxBinaryMessageBufferSize;
    }

}
