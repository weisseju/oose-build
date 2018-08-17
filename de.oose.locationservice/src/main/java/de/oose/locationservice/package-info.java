@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(type = Instant.class,
                value = InstantXmlAdapter.class),
        @XmlJavaTypeAdapter(type = Coordinate.class,
                value = CoordinateXmlAdapter.class) })
package de.oose.locationservice;

import java.time.Instant;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import com.vividsolutions.jts.geom.Coordinate;

