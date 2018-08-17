package de.oose.locationservice;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.vividsolutions.jts.geom.Coordinate;

public class CoordinateXmlAdapter extends XmlAdapter<String, Coordinate> {

	@Override
	public Coordinate unmarshal(String v) throws Exception {
		String[] strings = v.split(":");

		return new Coordinate(Double.parseDouble(strings[0]),
				Double.parseDouble(strings[1]));
	}

	@Override
	public String marshal(Coordinate v) throws Exception {

		return v.x + ":" + v.y;
	}
}
