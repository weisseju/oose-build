package de.oose.locationservice.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.Instant;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vividsolutions.jts.geom.Coordinate;

import de.oose.locationservice.Location;

@Named("location")
@RequestScoped
public class LocationBean implements Serializable {

	private static final long serialVersionUID = 3482820934751795609L;
	@Inject
	private transient LocationServiceClient locationServiceClient;

	private transient Location location;

	@PostConstruct
	public void loadLocation() {
		location = locationServiceClient.getLocationFromService();
	}

	public Coordinate getPosition() {
		return location == null ? null : location.getPosition();
	}

	public Double getHeading() {
		return location == null ? null : location.getHeading();
	}

	public Instant getTime() {
		return location == null ? null : location.getTime();
	}

	public String getVersion() {
		String version;
		InputStream versionStream = this.getClass().getClassLoader().getResourceAsStream("version.txt");

		if (versionStream == null) {
			version = "version info file is missing";
		} else {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(versionStream))) {
				version = reader.readLine();
			} catch (IOException e) {
				version = "error reading version info";
			}
		}
		return version;

	}

	void setLocationServiceClient(LocationServiceClient locationServiceClient) {
		this.locationServiceClient = locationServiceClient;
	}
}
