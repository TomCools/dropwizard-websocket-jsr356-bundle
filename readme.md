Websocket (JSR 356) bundle for Dropwizard
==========
*Adding some Websocket-magic to Dropwizard.*

This repository contains a Bundle to be used with Dropwizard.
For more information about Dropwizard, please visit: [dropwizard.io](http://www.dropwizard.io).

The goal of the bundle was to add support for the JSR 356 Websocket specification.
(Lookup since when Jetty supports this functionality).

How does the bundle work?
---
Add the maven dependency: 

    Available after first release

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
            websocket.addEndpoint(PingPongServerEndpoint.class);
        }
    }

Start your server. During startup, all registered Websocket-endpoints will be logged in the same way other resources are logged.

    Example to be added
    
Need help? Found an issue? Want extra functionality?
---
Please report any issues you find. I will frequently check and update if required.
Please use following guidelines when posting:

* Check existing issues to see if it has been addressed already;
* In issues include the Bundle-version as well as the version of Dropwizard you are using;
* Add a description of how someone else can reproduce the problem and add the stacktrace if possible.