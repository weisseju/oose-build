package de.oose.environmentservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class EnvironmentTest {

	private Environment environment;

	@Before
	public void setup() {
		environment = new Environment();
	}

	@Test
	public void setWeatherAllowsNull() throws Exception {
		environment.setWeather(null);
		assertNull(environment.getWeather());
	}

	@Test
	public void setWeatherAllowsEmptyString() throws Exception {
		environment.setWeather("");
		assertEquals("", environment.getWeather());
	}

	@Test
	public void setWeatherSetsWeatherAttribute() throws Exception {
		environment.setWeather("hot");
		assertEquals("hot", environment.getWeather());
	}

	@Test
	public void setLanguageAllowsNull() throws Exception {
		environment.setLanguage(null);
		assertNull(environment.getLanguage());
	}

	@Test
	public void setLanguageAllowsEmptyString() throws Exception {
		environment.setLanguage("");
		assertEquals("", environment.getLanguage());
	}

	@Test
	public void setLanguageSetsLanguageAttribute() throws Exception {
		environment.setLanguage("somewhere");
		assertEquals("somewhere", environment.getLanguage());
	}

	@Test
	public void setCountryAllowsNull() throws Exception {
		environment.setCountry(null);
		assertNull(environment.getCountry());
	}

	@Test
	public void setCountryAllowsEmptyString() throws Exception {
		environment.setCountry("");
		assertEquals("", environment.getCountry());
	}

	@Test
	public void setCountrySetsCountryAttribute() throws Exception {
		environment.setCountry("brabbel");
		assertEquals("brabbel", environment.getCountry());
	}

	@Test
	public void testSameEquals() throws Exception {
		assertEquals(environment, environment);

	}

	@Test
	public void testAllEqual() throws Exception {

		Environment e1 = new Environment();
		e1.setCountry("D");
		e1.setLanguage("auchD");
		e1.setWeather("gut");

		Environment e2 = new Environment();
		e2.setCountry("D");
		e2.setLanguage("auchD");
		e2.setWeather("gut");

		assertEquals(e1, e2);
	}

	@Test
	public void testCountryEqual() throws Exception {

		Environment e1 = new Environment();
		e1.setCountry("D");

		Environment e2 = new Environment();
		e2.setCountry("D");
		assertEquals(e1, e2);
	}

	@Test
	public void testLangEqual() throws Exception {

		Environment e1 = new Environment();
		e1.setLanguage("auchD");

		Environment e2 = new Environment();
		e2.setLanguage("auchD");

		assertEquals(e1, e2);
	}

	@Test
	public void testWeatherEqual() throws Exception {

		Environment e1 = new Environment();
		e1.setWeather("gut");

		Environment e2 = new Environment();
		e2.setWeather("gut");

		assertEquals(e1, e2);
	}

	@Test
	public void testNotEqualCountry() throws Exception {

		Environment e1 = new Environment();
		e1.setCountry("D");
		e1.setLanguage("auchD");
		e1.setWeather("gut");

		Environment e2 = new Environment();
		e2.setCountry("GB");
		e2.setLanguage("auchD");
		e2.setWeather("gut");

		assertNotEquals(e1, e2);
	}

	@Test
	public void testNotEqualLanguage() throws Exception {

		Environment e1 = new Environment();
		e1.setCountry("D");
		e1.setLanguage("auchD");
		e1.setWeather("gut");

		Environment e2 = new Environment();
		e2.setCountry("D");
		e2.setLanguage("E");
		e2.setWeather("gut");

		assertNotEquals(e1, e2);
	}

	@Test
	public void testNotEqualWeather() throws Exception {

		Environment e1 = new Environment();
		e1.setCountry("D");
		e1.setLanguage("auchD");
		e1.setWeather("gut");

		Environment e2 = new Environment();
		e2.setCountry("D");
		e2.setLanguage("auchD");
		e2.setWeather("schlecht");

		assertNotEquals(e1, e2);
	}

	@Test
	public void testHashCode() throws Exception {

		Environment e1 = new Environment();
		e1.setCountry("D");
		e1.setLanguage("auchD");
		e1.setWeather("gut");

		Environment e2 = new Environment();
		e2.setCountry("D");
		e2.setLanguage("auchD");
		e2.setWeather("gut");

		assertEquals(e1.hashCode(), e2.hashCode());

	}

	@Test
	public void testToString() throws Exception {

		Environment environment = new Environment();
		environment.setCountry("D");
		environment.setLanguage("auchD");
		environment.setWeather("gut");

		String string = environment.toString();

		assertTrue(string.contains(environment.getCountry()));
		assertTrue(string.contains(environment.getLanguage()));
		assertTrue(string.contains(environment.getWeather()));

	}

}
