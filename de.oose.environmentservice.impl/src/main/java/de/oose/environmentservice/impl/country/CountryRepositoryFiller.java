package de.oose.environmentservice.impl.country;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Other way to fill data to repo:
 * Create a data.json 

[ { "_class" : "de.oose.environmentservice.impl.Country",
 "name" : "Deutschland",
  "language" : "deutsch" },
  { "_class" : "de.oose.environmentservice.impl.Country",
 "name" : "USA",
  "language" : "englisch" },
  { "_class" : "de.oose.environmentservice.impl.Country",
 "name" : "Frankreich",
  "language" : "franzoesisch" } ]

 * and add to app context:
 *   <repository:jackson-populator locations="classpath:data.json" />
 * @author felix
 *
 */
@Component
public class CountryRepositoryFiller {

    @Autowired
    private CountryRepository countryRepository;

    @PostConstruct
    public void fill() {
        countryRepository.save(new Country("Deutschland", "deutsch"));
        countryRepository.save(new Country("USA", "englisch"));
        countryRepository.save(new Country("Frankreich", "franzoesisch"));

    }
}
