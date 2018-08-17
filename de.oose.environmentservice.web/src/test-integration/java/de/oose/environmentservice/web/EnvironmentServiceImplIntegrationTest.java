package de.oose.environmentservice.web;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

import de.oose.environmentservice.Environment;
import de.oose.environmentservice.EnvironmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE,classes = {App.class,EnvironmentServiceImplIntegrationTest.TestConfiguration.class})
public class EnvironmentServiceImplIntegrationTest {

	@Inject
	private EnvironmentService environmentService;
	
	@ClassRule
	public static WireMockRule wireMockRule =new WireMockRule(wireMockConfig().dynamicPort());
	
	
	//using dynamic ports requires to configure the ribbon server list accordingly    
    public static class TestConfiguration {
    	
        @Bean
        public ServerList<Server> ribbonServerList() {
            return new StaticServerList<>(new Server("localhost", wireMockRule.port()));
        }
    }

	@Test
	public void testError() {

		Environment testEnv = new Environment("?", "?", "?");

		stubFor(get(urlPathMatching("/de.oose.environmentservice.impl/environment"))
				.withQueryParam("lat", equalTo("0.1")).withQueryParam("lon", equalTo("0.22"))
				.withHeader("Accept", equalTo(MediaType.APPLICATION_JSON_VALUE))
				.willReturn(aResponse().withStatus(500)));

		Environment environment = environmentService.getEnvironment(0.1, 0.22);

		assertEquals(testEnv, environment);

	}

	@Test
	public void testDelay() {

		Environment testEnv = new Environment("?", "?", "?");

		stubFor(get(urlPathMatching("/de.oose.environmentservice.impl/environment"))
				.withQueryParam("lat", equalTo("0.1")).withQueryParam("lon", equalTo("0.22"))
				.withHeader("Accept", equalTo(MediaType.APPLICATION_JSON_VALUE))
				.willReturn(aResponse().withFixedDelay(500).withStatus(200)
						.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).withBody(
								"{\"country\": \"Deutschland\",\"language\": \"deutsch\",\"weather\": \"super\"}")));

		Environment environment = environmentService.getEnvironment(0.1, 0.22);

		assertEquals(testEnv, environment);
	}

	@Test
	public void testSuccess() {

		Environment testEnv = new Environment("Deutschland", "deutsch", "super");

		stubFor(get(urlPathMatching("/de.oose.environmentservice.impl/environment"))
				.withQueryParam("lat", equalTo("0.1")).withQueryParam("lon", equalTo("0.22"))
				.withHeader("Accept", equalTo(MediaType.APPLICATION_JSON_VALUE))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withBody("{\"country\": \"Deutschland\",\"language\": \"deutsch\",\"weather\": \"super\"}")));

		Environment environment = environmentService.getEnvironment(0.1, 0.22);

		assertEquals(testEnv, environment);
	}

}
