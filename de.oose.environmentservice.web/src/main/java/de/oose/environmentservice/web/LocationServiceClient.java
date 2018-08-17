package de.oose.environmentservice.web;

import java.io.File;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import de.oose.locationservice.Location;
import de.oose.locationservice.LocationService;

/**
 * Just a demo. Use feign as in EnvironmentServiceClient whenever possible.
 * 
 * @author FelixH
 *
 */
@Component
public class LocationServiceClient implements LocationService {

	@Value("${locationservice.url}")
	private String locationServiceUrl;

	@Inject
	private RestTemplate template;

	@Override
	public Location getLocation() {

		try {
			Location locationFromJson = template.getForObject(locationServiceUrl + "/location/current", Location.class);
			return locationFromJson;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	void setRestTemplate(RestTemplate template) {
		this.template = template;
	}

	void setLocationServiceUrl(String locationServiceUrl) {
		this.locationServiceUrl = locationServiceUrl;
	}

	public void withWarings() {

		File f = new File("test");

	}
}
