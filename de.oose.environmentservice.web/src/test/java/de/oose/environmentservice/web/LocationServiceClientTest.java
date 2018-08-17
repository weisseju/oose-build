package de.oose.environmentservice.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.vividsolutions.jts.geom.Coordinate;

import de.oose.locationservice.Location;

/**
 * Test LocationServiceClient with mocked RestTemplate
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LocationServiceClientTest {

	private static final String URL = "protocol://some/url";
	private static final String EXPECTED_URL = URL + "/location/current";
	private final LocationServiceClient client = new LocationServiceClient();

	@Mock
	private RestTemplate template;

	@Before
	public void setUp() {
		client.setRestTemplate(template);
		client.setLocationServiceUrl(URL);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testLocationIsNullWhenTemplateThrows() throws Exception {

		when(template.getForObject(EXPECTED_URL, Location.class)).thenThrow(RuntimeException.class);

		assertEquals(null, client.getLocation());

	}

	@Test
	public void getLocationRequestsCurrentLocationFromGivenUrl() throws Exception {
		Location location = new Location(new Coordinate(42, 180), 0, Instant.now());

		when(template.getForObject(EXPECTED_URL, Location.class)).thenReturn(location);

		assertEquals(location, client.getLocation());
	}

}
