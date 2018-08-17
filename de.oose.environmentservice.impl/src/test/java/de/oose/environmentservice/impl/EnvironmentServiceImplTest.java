package de.oose.environmentservice.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.oose.environmentservice.Environment;
import de.oose.environmentservice.impl.country.Country;
import de.oose.environmentservice.impl.country.CountryRepository;

@RunWith(MockitoJUnitRunner.class)
public class EnvironmentServiceImplTest {

    @InjectMocks
    private EnvironmentServiceImpl environmentServiceImpl =
            new EnvironmentServiceImpl();

    @Mock
    private CountryRepository countryDao;

    private Country country = new Country("Deutschland", "deutsch");

    @Test
    public void testGetEnvironment() throws Exception {

        when(countryDao.findOne("Deutschland")).thenReturn(country);

        Environment environment =
                environmentServiceImpl.getEnvironment(12.4, 15.6);

        assertNotNull(environment);
        assertEquals(country.getName(), environment.getCountry());
        assertEquals(country.getLanguage(), environment.getLanguage());
        assertEquals("superwetter at lat 12.4 lon 15.6",
                environment.getWeather());

    }

    @Test
    public void testCountryNotFound() throws Exception {

        when(countryDao.findOne("Deutschland")).thenReturn(null);

        Environment environment =
                environmentServiceImpl.getEnvironment(12.4, 15.6);

        assertNotNull(environment);
        assertEquals(country.getName(), environment.getCountry());
        assertTrue(environment.getLanguage().contains(country.getName()));
        assertTrue(environment.getLanguage().contains("?"));
        assertEquals("superwetter at lat 12.4 lon 15.6",
                environment.getWeather());

    }
}
