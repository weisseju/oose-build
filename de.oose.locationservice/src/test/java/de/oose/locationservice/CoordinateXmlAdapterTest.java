package de.oose.locationservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;

public class CoordinateXmlAdapterTest {

    private final CoordinateXmlAdapter adapter = new CoordinateXmlAdapter();

    @Test
    public void testToString() throws Exception {

        assertEquals("12.0:42.0", adapter.marshal(new Coordinate(12.0, 42.0)));
        assertEquals("0.0:17.0", adapter.marshal(new Coordinate(0.0, 17.0)));
        assertEquals("-1.0:-2.0", adapter.marshal(new Coordinate(-1.0, -2.0)));
        assertEquals("4.0:-7.12345",
                adapter.marshal(new Coordinate(4.0, -7.12345)));
    }

    @Test
    public void fromString() throws Exception {
        assertEquals(new Coordinate(12.0, 42.0),
                     adapter.unmarshal("12.0:42.0"));
        assertEquals(new Coordinate(0.0, 17.0), adapter.unmarshal("0.0:17.0"));
        assertEquals(new Coordinate(-1.0, -2.0), 
                     adapter.unmarshal("-1.0:-2.0"));
        assertEquals(new Coordinate(4.0, -7.12345),
                adapter.unmarshal("4.0:-7.12345"));
    }

}
