package de.oose.locationservice.impl;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class WebServiceTest extends org.glassfish.jersey.test.JerseyTest {

    @Override
    public Application configure() {
        return new ResourceConfig(LocationServiceImpl.class)
                .register(JacksonJaxbJsonProvider.class);
    }

    @Override
    protected void configureClient(ClientConfig config) {

        super.configureClient(config);

        config.register(JacksonJaxbJsonProvider.class);

    }

    @Test
    public void testHelloWorld() {

        String responseMsg = target("/current/").request().get(String.class);
        assertTrue(responseMsg
                .startsWith(
                     "{\"position\":\"0.4:0.34\",\"heading\":0.0,\"time\":\""));
    }

}
