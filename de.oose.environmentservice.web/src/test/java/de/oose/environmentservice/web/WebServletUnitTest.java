package de.oose.environmentservice.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;

import com.vividsolutions.jts.geom.Coordinate;

import de.oose.environmentservice.Environment;
import de.oose.environmentservice.EnvironmentService;
import de.oose.locationservice.Location;
import de.oose.locationservice.LocationService;

/*
 * Test that WebServlet builds model correctly
 */
@RunWith(MockitoJUnitRunner.class)
public class WebServletUnitTest {

	@InjectMocks
	private WebServlet webServlet = new WebServlet();

	@Mock
	private EnvironmentService environmentService;

	@Mock
	private LocationService locationService;

	private Location location = new Location(new Coordinate(1.0, 2.0), 23.3, Instant.ofEpochSecond(230));
	private Environment env = new Environment("Deutschland", "deutsch", "super");

	@Test
	public void testWithNullLocationResponse() {
		// when(environmentServiceClient.getEnvironment(1.0,
		// 2.0)).thenReturn(null);
		when(locationService.getLocation()).thenReturn(null);

		ExtendedModelMap model = new ExtendedModelMap();
		String view = webServlet.view(model);

		assertNull(model.get("location"));
		assertNull(model.get("environment"));
		// assertEquals("version info file is missing", model.get("version"));

		assertEquals("environmentView", view);

	}

	@Test
	public void testWithLocationAndWithoutEnvironment() {
		when(locationService.getLocation()).thenReturn(location);
		when(environmentService.getEnvironment(location.getPosition().y, location.getPosition().x)).thenReturn(null);

		ExtendedModelMap model = new ExtendedModelMap();
		String view = webServlet.view(model);

		assertSame(location, model.get("location"));
		assertNull(model.get("environment"));
		// assertEquals("version info file is missing", model.get("version"));

		assertEquals("environmentView", view);

	}

	@Test
	public void testWithLocationAndEnvironment() {
		when(locationService.getLocation()).thenReturn(location);
		when(environmentService.getEnvironment(location.getPosition().y, location.getPosition().x)).thenReturn(env);

		ExtendedModelMap model = new ExtendedModelMap();
		String view = webServlet.view(model);

		assertSame(location, model.get("location"));
		assertSame(env, model.get("environment"));
		// assertEquals("version info file is missing", model.get("version"));

		assertEquals("environmentView", view);
	}

}
