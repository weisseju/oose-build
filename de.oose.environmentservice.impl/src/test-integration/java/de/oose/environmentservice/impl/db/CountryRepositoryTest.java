package de.oose.environmentservice.impl.db;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.oose.environmentservice.impl.country.Country;
import de.oose.environmentservice.impl.country.CountryRepository;

/**
 * This is not a useful test! The Repository is spring generated!
 * 
 * @author felix
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class CountryRepositoryTest {

	@Autowired
	private CountryRepository countryDao;
	private Country country;

	@Before
	public void setup() {
		country = new Country("land", "sprache");
	}

	@Test
	public void storedCountriesCanBeFound() throws Exception {
		countryDao.save(country);
		assertEquals(country, countryDao.findOne("land"));
	}

	@Test
	public void findByCountryFindsTheCorrectCountry() throws Exception {
		persistThreeCountries();
		assertEquals(country, countryDao.findOne("land"));
	}

	private void persistThreeCountries() {
		Country other = new Country("other", "lang");
		Country another = new Country("another", "brabbel");
		countryDao.save(Arrays.asList(other, country, another));
	}

	@After
	public void tearDown() {
		countryDao.deleteAll();
	}
}
