package de.oose.environmentservice.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.oose.environmentservice.Environment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class EnvironmentServiceImplLoopTest {

    @Autowired
    private EnvironmentServiceImpl serviceImpl;

    @Test
    public void latLongAreIrrelevantForGetCountry() throws Exception {
        for (int latitude = -3; latitude <= 3; latitude++) {
            for (int longitude = -3; longitude < 3; longitude++) {
                Environment environment =
                        serviceImpl.getEnvironment(latitude, longitude);
                assertEquals("Deutschland", environment.getCountry());
            }
        }
    }
}