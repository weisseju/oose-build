package de.oose.environmentservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import de.oose.environmentservice.Environment;
import de.oose.environmentservice.EnvironmentService;

@Component
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	private EnvironmentServiceClient client;

	@HystrixCommand(fallbackMethod = "getDefaultEnvironment", commandKey = "env", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500") })
	@Override
	public Environment getEnvironment(double latitude, double longitude) {
		return client.getEnvironment(latitude, longitude);
	}

	public Environment getDefaultEnvironment(double latitude, double longitude) {
		return new Environment("?", "?", "?");
	}

}
