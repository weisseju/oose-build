package de.oose.environmentservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.oose.environmentservice.Environment;
import de.oose.environmentservice.EnvironmentService;
import de.oose.environmentservice.impl.country.Country;
import de.oose.environmentservice.impl.country.CountryRepository;

@RestController
@RequestMapping("/environment")
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	private CountryRepository countryRepository;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public Environment getEnvironment(@RequestParam("lat") double latitude,
			@RequestParam("lon") double longitude) {

		Environment environment = new Environment();
		environment.setCountry(getCountry(latitude, longitude));
		environment.setWeather("superwetter at lat " + latitude + " lon "
				+ longitude);
		environment.setLanguage(getLanguage(environment.getCountry()));

		return environment;
	}

	private String getLanguage(String countryName) {

		Country country = countryRepository.findOne(countryName);

		return country != null ? country.getLanguage()
				: "What language do they speak in " + countryName + "?";
	}

	private String getCountry(double latitude, double longitude) {

		return "Deutschland";
	}

}
