package de.oose.locationservice.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import de.oose.locationservice.Location;

public class LocationServiceSystemTest {

	private String locationServiceUrl = "http://localhost:8085/de.oose.locationservice.impl";
	private WebTarget webTarget;

	@Test
	public void systemTest() {
		Client client = ClientBuilder.newClient().register(JacksonJaxbJsonProvider.class);
		webTarget = client.target(locationServiceUrl);

		Location location = webTarget.path("location").path("current").request(MediaType.APPLICATION_JSON).get(Location.class);
		
		String json = webTarget.path("location").path("current").request(MediaType.APPLICATION_JSON).get(String.class);
		
		

	}
}
