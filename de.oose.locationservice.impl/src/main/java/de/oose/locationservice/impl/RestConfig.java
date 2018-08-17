package de.oose.locationservice.impl;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

@ApplicationPath("location")
public class RestConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(LocationServiceImpl.class);
		return s;
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> objs = new HashSet<Object>(1);
		objs.add(new JacksonJaxbJsonProvider());
		return objs;
	}

}