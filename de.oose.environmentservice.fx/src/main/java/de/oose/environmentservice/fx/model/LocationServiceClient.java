package de.oose.environmentservice.fx.model;

import org.springframework.web.client.RestTemplate;

import de.oose.locationservice.Location;

public class LocationServiceClient {

	private String url;
	private RestTemplate template;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTemplate(RestTemplate template) {
		this.template = template;
	}

	public Location getLocationFromService() {

		try {
			Location locationFromJson = template.getForObject(url
					+ "/location/current", Location.class);
			return locationFromJson;
		} catch (RuntimeException e) {
			return null;
		}
	}
}
