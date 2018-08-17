package de.oose.environmentservice.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.time.Instant;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.vividsolutions.jts.geom.Coordinate;

import de.oose.locationservice.Location;

/*
 * Test calling the rest service for location. This includes message conversion with custom types like Coordinate!
 * This is, why the template is taken from configured context
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = App.class)
public class LocationServiceClientIntegrationTest {

	@Inject
	private RestTemplate restTemplate;

	private String baseUrl = "http://someServer:9000/test/";
	private final LocationServiceClient client = new LocationServiceClient();
	private MockRestServiceServer mockServer;

	@Before
	public void setUp() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
		client.setRestTemplate(restTemplate);
		client.setLocationServiceUrl(baseUrl);
	}

	@Test
	public void testRestCall() {
		Location location = new Location(new Coordinate(0.4, 0.34), 0.0, Instant.parse("2014-08-28T11:55:20.845Z"));
		mockServer.expect(requestTo(baseUrl + "location/current"))
				.andRespond(withSuccess(
						"{\"position\": \"0.4:0.34\",\"heading\": \"0.0\",\"time\": \"2014-08-28T11:55:20.845Z\"}",
						MediaType.APPLICATION_JSON));
		Location locationFromService = client.getLocation();
		assertEquals(location, locationFromService);

		mockServer.verify();
	}

}
