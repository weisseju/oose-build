package de.oose.environmentservice.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.oose.environmentservice.Environment;
import de.oose.environmentservice.EnvironmentService;
import de.oose.locationservice.Location;
import de.oose.locationservice.LocationService;

@Controller
public class WebServlet {

	@Inject
	private LocationService locationServiceClient;

	@Inject
	private EnvironmentService environmentServiceClient;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Model model) {

		Location location = locationServiceClient.getLocation();
		Environment environment = location == null ? null
				: environmentServiceClient.getEnvironment(location.getPosition().y, location.getPosition().x);

		model.addAttribute("location", location);
		model.addAttribute("environment", environment);

		String version;
		InputStream versionStream = this.getClass().getClassLoader().getResourceAsStream("version.txt");

		if (versionStream == null) {
			version = "version info file is missing";
		} else {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(versionStream))) {
				version = reader.readLine();
			} catch (IOException e) {
				version = "error reading version info";
			}
		}
		model.addAttribute("version", version);

		return "environmentView";
	}

	public void setLocationServiceClient(LocationServiceClient locationServiceClient) {
		this.locationServiceClient = locationServiceClient;
	}

	public void setEnvironmentServiceClient(EnvironmentService environmentServiceClient) {
		this.environmentServiceClient = environmentServiceClient;
	}
}
