package de.oose.locationservice.web;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import de.oose.locationservice.Location;
import de.oose.locationservice.web.config.ConfigurationValue;

/**
 * Service Client fuer Tomcat 7 mit cxf 2.x.
 */
@Default
@ApplicationScoped
public class CxfServiceClient implements LocationServiceClient {

	@ConfigurationValue
	@Inject
	private String locationServiceUrl;

	private WebClient target;

	@PostConstruct
	void createClient() {
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJaxbJsonProvider());
		target = WebClient.create(locationServiceUrl, providers).accept("application/json")
				.type("application/json").path("/location/current");
		
		System.out.println("Created client for " + locationServiceUrl);
		
	}

	@Override
	public Location getLocationFromService() {

		Location locationFromJson = target.get(Location.class);
		return locationFromJson;
	}

	void setLocationServiceUrl(String locationServiceUrl) {
		this.locationServiceUrl = locationServiceUrl;
	}

	WebClient getTarget() {
		return target;
	}
}
