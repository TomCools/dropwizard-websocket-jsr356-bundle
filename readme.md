Websocket (JSR 356) bundle for Dropwizard
==========
[![Build Status](https://travis-ci.org/TomCools/dropwizard-websocket-jee7-bundle.png?branch=master)](https://travis-ci.org/TomCools/dropwizard-websocket-jee7-bundle)

*Adding some Websocket-magic to Dropwizard.*

This repository contains a Bundle to be used with Dropwizard.
For more information about Dropwizard, please visit: [dropwizard.io](http://www.dropwizard.io).

The goal of the bundle was to add support for the JSR 356 Websocket specification.
(Lookup since when Jetty supports this functionality).

How does the bundle work?
---
Add the maven dependency: 

    <dependency>
      <groupId>be.tomcools</groupId>
      <artifactId>dropwizard-websocket-jee7-bundle</artifactId>
      <version>1.0.0</version>
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

    INFO  [2015-05-16 18:18:04,230] be.tomcools.dropwizard.websocket.handling.WebsocketContainer: Registered websocket endpoints: 
    
    	GET		/programmatic (be.tomcools.dropwizard.websocket.integrationtest.programmaticjavaee.ProgrammaticServerEndpoint)
    	GET		/pingpong (be.tomcools.dropwizard.websocket.integrationtest.annotatedjavaee.PingPongServerEndpoint)
    

    
Need help? Found an issue? Want extra functionality?
---
Please report any issues you find. I will frequently check and update if required.
Please use following guidelines when posting:

* Check existing issues to see if it has been addressed already;
* In issues include the Bundle-version as well as the version of Dropwizard you are using;
* Add a description of how someone else can reproduce the problem and add the stacktrace if possible.