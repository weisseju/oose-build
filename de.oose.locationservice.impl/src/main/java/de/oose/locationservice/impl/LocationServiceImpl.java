package de.oose.locationservice.impl;

import java.time.Clock;
import java.time.Instant;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vividsolutions.jts.geom.Coordinate;

import de.oose.locationservice.LocationService;
import de.oose.locationservice.Location;

@Path("current")
public class LocationServiceImpl implements LocationService {

	private Clock clock = Clock.systemDefaultZone();

	public Instant getTime() {
		return clock.instant();
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Location getLocation() {
		return new Location(new Coordinate(0.4, 0.34), 0.0, getTime());
	}


}
