package de.oose.locationservice.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vividsolutions.jts.geom.Coordinate;

import de.oose.locationservice.Location;

@RunWith(MockitoJUnitRunner.class)
public class LocationBeanTest {

    @Mock
    private LocationServiceClient locationServiceClient;

    @InjectMocks
    private LocationBean locationBean = new LocationBean();

    private Location location = new Location(new Coordinate(0.1, 0.2), 3.5,
            Instant.ofEpochSecond(9876));

    @Before
    public void setUp() {
        when(locationServiceClient.getLocationFromService())
                .thenReturn(location);
        locationBean.loadLocation();
    }

    @Test
    public void testGetHeading() {
        assertEquals(3.5, locationBean.getHeading(), 0.0001);
    }

    @Test
    public void testGetTime() {
        assertEquals(Instant.ofEpochSecond(9876), locationBean.getTime());
    }

    @Test
    public void testGetPosition() {
        assertEquals(new Coordinate(0.1, 0.2), locationBean.getPosition());
    }

    @Test
    public void testNull() {
        when(locationServiceClient.getLocationFromService())
                .thenReturn(null);
        locationBean.loadLocation();
        assertNull(locationBean.getHeading());
        assertNull(locationBean.getTime());
        assertNull(locationBean.getPosition());

    }

}
