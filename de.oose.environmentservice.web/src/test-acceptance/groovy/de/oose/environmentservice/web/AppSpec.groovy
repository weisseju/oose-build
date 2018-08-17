package de.oose.environmentservice.web

import geb.spock.GebSpec

import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ContextConfiguration

import spock.lang.Unroll

/*
 * A nicer version of real integration test using geb, spock and groovy. Test that the app is available for a real browser.
 */



@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = App.class)
@WebIntegrationTest(["server.port:0","management.port=0"])
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = App.class,properties = [ "management.port=0" ])
class AppSpec extends GebSpec {

	@Value('${local.server.port}')
	@LocalServerPort
	int port;

	def url = { "http://localhost:$port/view/" }

	def setup() {
		browser.driver = new HtmlUnitDriver();
    }

	def "the default position text ist null"() {
		given:
		go(url())


		expect:
		at EnvironmentPage

		and:
		position.text() == "Position null"
	}

	def "default country text ist null"() {
		given:
		go(url())

		expect:
		at EnvironmentPage

		and:
		country.text() == "Country null"
	}

	@Unroll
	def "all default text for #id is null"(){
		given:
		go(url())

		expect:
		at EnvironmentPage

		and:
		text == textfeld(id).text()

		where:
		id 		  | text
		"position"| "Position null"
		"heading" | "Heading null"
		"time"	  | "Time null"
		"country" | "Country null"
		"language"| "Language null"
		"weather" | "Weather null"
	}
}
