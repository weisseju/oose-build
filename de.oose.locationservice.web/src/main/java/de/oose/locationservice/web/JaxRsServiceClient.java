package de.oose.locationservice.web;



import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.oose.locationservice.Location;
import de.oose.locationservice.web.config.ConfigurationValue;

/*
 * Service Client JaxRs Java EE7 (z.B. Glassfish)
 * Just a stub. Please write a test 
 */
public class JaxRsServiceClient implements LocationServiceClient {

	@ConfigurationValue
	@Inject
	private String locationServiceUrl;

	private WebTarget webTarget;

	@PostConstruct
	private void createClient() {
		Client client = ClientBuilder.newClient();
		webTarget = client.target(locationServiceUrl);
	}

	@Override
	public Location getLocationFromService() {

		Invocation.Builder jsonBuilder = webTarget.path("location")
				.path("current").request(MediaType.APPLICATION_JSON);

		Response jsonResponse = jsonBuilder.get();

		Location locationFromJson = (Location) jsonResponse.getEntity();

		return locationFromJson;
	}
}
