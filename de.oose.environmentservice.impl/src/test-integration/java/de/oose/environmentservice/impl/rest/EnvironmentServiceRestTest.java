package de.oose.environmentservice.impl.rest;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jayway.restassured.http.ContentType;

import de.oose.environmentservice.impl.EnvironmentServiceImpl;
import de.oose.environmentservice.impl.country.Country;
import de.oose.environmentservice.impl.country.CountryRepository;

@RunWith(MockitoJUnitRunner.class)
public class EnvironmentServiceRestTest {

	@InjectMocks
	private EnvironmentServiceImpl environmentServiceImpl = new EnvironmentServiceImpl();

	@Mock
	private CountryRepository countryDao;

	private Country country = new Country("Deutschland", "deutsch");

	@Test
	public void getEnvironmentTest() {
		when(countryDao.findOne("Deutschland")).thenReturn(country);

		given().standaloneSetup(environmentServiceImpl).when().get("/environment?lat=1.0&lon=2.0").then()
				.contentType(ContentType.JSON).body("weather", startsWith("superwetter")).and()
				.body("language", equalTo("deutsch")).and().body("country", equalTo("Deutschland")).and()
				.time(lessThan(1000L));
	}
}
