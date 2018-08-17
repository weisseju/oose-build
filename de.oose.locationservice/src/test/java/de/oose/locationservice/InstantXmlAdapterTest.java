package de.oose.locationservice;

import static org.junit.Assert.assertEquals;

import java.time.Instant;

import org.junit.Test;

public class InstantXmlAdapterTest {

    private InstantXmlAdapter adapter = new InstantXmlAdapter();

    @Test
    public void testToString() throws Exception {

        assertEquals("2014-09-25T10:15:30.120Z",
                adapter.marshal(Instant.parse("2014-09-25T10:15:30.12Z")));
        assertEquals("2014-09-25T10:15:30Z",
                adapter.marshal(Instant.parse("2014-09-25T10:15:30.00Z")));

    }

    @Test
    public void testFromString() throws Exception {

        assertEquals(Instant.parse("2014-09-25T10:15:30.12Z"),
                adapter.unmarshal("2014-09-25T10:15:30.120Z"));

        assertEquals(Instant.parse("2014-09-25T10:15:30.00Z"),
                adapter.unmarshal("2014-09-25T10:15:30Z"));
        assertEquals(Instant.parse("2014-09-25T10:15:30.00Z"),
                adapter.unmarshal("2014-09-25T10:15:30.000Z"));

    }
}
