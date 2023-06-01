Websocket (JSR 356) bundle for Dropwizard
==========
[![Latest Build](https://github.com/TomCools/dropwizard-websocket-jsr356-bundle/actions/workflows/github-build-maven.yml/badge.svg?branch=main)](https://github.com/TomCools/dropwizard-websocket-jsr356-bundle/actions/workflows/github-build-maven.yml)
[![Latest Release](https://img.shields.io/maven-central/v/be.tomcools/dropwizard-websocket-jsr356-bundle)](http://mvnrepository.com/artifact/be.tomcools/dropwizard-websocket-jsr356-bundle)
[![License](https://img.shields.io/badge/License-Apache%202-blue.svg)](https://github.com/TomCools/dropwizard-websocket-jee7-bundle/blob/master/LICENSE)

*Adding some Websocket-magic to Dropwizard.*

This repository contains a Bundle to be used with Dropwizard.
For more information about Dropwizard, please visit: [dropwizard.io](http://www.dropwizard.io).

The goal of the bundle was to add support for the JSR 356 Websocket specification.
For some reason, Dropwizard doesn't support this out of the box.

How does the bundle work?
---

The baseline for this version is *Java 11*, which is also the baseline for Dropwizard 4.

Add the maven dependency: 

    <dependency>
      <groupId>be.tomcools</groupId>
      <artifactId>dropwizard-websocket-jsr356-bundle</artifactId>
      <version>4.0.0</version>
    </dependency>
    
This project used to contain an implementation for Dropwizard 2.x, based on Java 8.
While we are deprecating that and no longer supporting it, it's still available.

    <dependency>
      <groupId>be.tomcools</groupId>
      <artifactId>dropwizard-websocket-jee7-bundle</artifactId>
      <version>2.1.6</version>
    </dependency>

Add the WebsocketBundle object to the Application.class and add the Endpoint classes you want to load:

    public class IntegrationTestApplication extends Application<IntegrationTestConfiguration> {
        private WebsocketBundle websocket = new WebsocketBundle();
    
        @Override
        public void initialize(Bootstrap<IntegrationTestConfiguration> bootstrap) {
            super.initialize(bootstrap);
            bootstrap.addBundle(websocket);
        }
    
        @Override
        public void run(IntegrationTestConfiguration configuration, Environment environment) throws Exception {
            //Annotated endpoint
            websocket.addEndpoint(PingPongServerEndpoint.class);
    
            //programmatic endpoint
            ServerEndpointConfig serverEndpointConfig = ServerEndpointConfig.Builder.create(ProgrammaticServerEndpoint.class, "/programmatic").build();
            websocket.addEndpoint(serverEndpointConfig);
        }
    }

Start your server. During startup, all registered Websocket-endpoints will be logged in the same way other resources are logged.

    INFO  [2017-05-16 18:18:04,230] be.tomcools.dropwizard.websocket.handling.WebsocketContainer: Registered websocket endpoints: 
    
    	GET		/programmatic (be.tomcools.dropwizard.websocket.integrationtest.programmaticjavaee.ProgrammaticServerEndpoint)
    	GET		/pingpong (be.tomcools.dropwizard.websocket.integrationtest.annotatedjavaee.PingPongServerEndpoint)
    

Configuration
---
Websocket default configuration can be overriden using the WebsocketBundleConfiguration Interface on the Configuration class.
When the interface is not used, the configuration will not be overridden.

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

WebsocketConfiguration:
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


    
Need help? Found an issue? Want extra functionality?
---
Please report any issues you find. I will frequently check and update if required.
Please use following guidelines when posting:

* Check existing issues to see if it has been addressed already;
* In issues include the Bundle-version as well as the version of Dropwizard you are using;
* Add a description of how someone else can reproduce the problem and add the stacktrace if possible.
