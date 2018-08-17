package de.oose.locationservice;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class InstantXmlAdapter extends XmlAdapter<String, Instant> {

    @Override
    public Instant unmarshal(String v) throws Exception {
        TemporalAccessor parsed = DateTimeFormatter.ISO_INSTANT.parse(v);
        return Instant.from(parsed);
    }

    @Override
    public String marshal(Instant v) throws Exception {
        return v.toString();
    }

}
