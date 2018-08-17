package de.oose.locationservice.impl;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class RestConfigTest {
	
	@Test
	public void configContainsEnvironmentServiceImplementation() {
		Set<Class<?>> configClasses = new RestConfig().getClasses();
		assertTrue(configClasses.contains(LocationServiceImpl.class));
	}
}
