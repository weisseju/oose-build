package de.oose.locationservice.web;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.transport.local.LocalConduit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.vividsolutions.jts.geom.Coordinate;

import de.oose.locationservice.Location;

public class CxfServiceClientTest {

	private final static String ENDPOINT_ADDRESS = "local://location";
	private static Server server;

	@BeforeClass
	public static void initialize() throws Exception {
		startServer();
	}

	private static void startServer() throws Exception {
     JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
     
         
     List<Object> providers = new ArrayList<Object>();
     providers.add(new JacksonJaxbJsonProvider());
     sf.setProviders(providers);
     
     sf.setResourceClasses(LocationResource.class);
     sf.setResourceProvider(LocationResource.class,
                            new SingletonResourceProvider(new LocationResource(), true));
     sf.setAddress(ENDPOINT_ADDRESS);
 
     server = sf.create();
}

	@AfterClass
	public static void destroy() throws Exception {
		server.stop();
		server.destroy();
	}

	@Ignore("Fix ee stuff!")
	@Test
	public void testGet() {

		CxfServiceClient cxfServiceClient = new CxfServiceClient();
		
		// process injection
		cxfServiceClient.setLocationServiceUrl(ENDPOINT_ADDRESS);
		cxfServiceClient.createClient();

		WebClient.getConfig(cxfServiceClient.getTarget()).getRequestContext()
				.put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);

		Location locationFromService = cxfServiceClient
				.getLocationFromService();
		assertEquals(0.123, locationFromService.getHeading(), 0.0001);
	}

	public static class LocationResource {

		@Path("location/current")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Location getLocation() {
			return new Location(new Coordinate(0.4, 0.34), 0.123, Instant.now());
		}
	}

}