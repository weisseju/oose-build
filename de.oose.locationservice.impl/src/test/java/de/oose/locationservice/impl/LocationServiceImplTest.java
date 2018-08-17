package de.oose.locationservice.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;

import de.oose.locationservice.Location;

public class LocationServiceImplTest {

    private final LocationServiceImpl serviceImpl = new LocationServiceImpl();
    private Instant fixedInstant = Instant.now();
    private Clock testClock = Clock.fixed(fixedInstant, ZoneId.systemDefault());

    @Test
    public void testTime() throws Exception {

        serviceImpl.setClock(testClock);
        Instant time = serviceImpl.getTime();

        assertSame(fixedInstant, time);

    }

    @Test
    public void testGetLocation() throws Exception {

        serviceImpl.setClock(testClock);

        Location location = serviceImpl.getLocation();

        assertEquals(
                new Location(new Coordinate(0.4, 0.34), 0.0, fixedInstant),
                location);
    }
}
