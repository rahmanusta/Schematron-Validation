package com.kodcu;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by usta on 23.12.2013.
 */
public class App {

    public static void main(String[] args) throws Exception {

        URI baseUri = null;
        if (args.length == 2)
             baseUri = UriBuilder.fromUri(args[0]).port(Integer.parseInt(args[1])).build();
        else if (args.length == 1)
            baseUri = UriBuilder.fromUri("http://localhost").port(Integer.parseInt(args[0])).build();
        else
            baseUri = UriBuilder.fromUri("http://localhost").port(9090).build();

        ResourceConfig config = new ResourceConfig(SchematronResource.class);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);

        server.start();

        Thread.currentThread().join();

    }


}
