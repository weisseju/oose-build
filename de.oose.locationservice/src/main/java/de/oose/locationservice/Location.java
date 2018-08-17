package de.oose.locationservice;

import java.time.Instant;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.vividsolutions.jts.geom.Coordinate;

@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Location {

    @XmlElement(name = "position")
    @XmlJavaTypeAdapter(CoordinateXmlAdapter.class)
    private Coordinate position;

    @XmlElement(name = "heading")
    private double heading;

    @XmlElement(name = "time")
    @XmlJavaTypeAdapter(InstantXmlAdapter.class)
    private Instant time;

}
