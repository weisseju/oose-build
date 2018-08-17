package de.oose.environmentservice.web;

import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * Plain selenium integrationtest. This makes sure the service is available for real browsers.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
 @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes =
 App.class,properties = { "management.port=0" })
public class WebServletSeleniumTest {

	@LocalServerPort
	int port;

	private URI siteBase;
	private WebDriver drv;

	@Before
	public void setUpClass() throws Exception {
		siteBase = new URI("http://localhost:" + port + "/view/");
		// drv = new FirefoxDriver();
		drv = new HtmlUnitDriver();
	}

	@After
	public void tearDownClass() {
		drv.close();
	}

	@Test
	public void testWeSeeHelloWorld() {
		drv.get(siteBase.toString());
		assertTrue(drv.getPageSource().contains("country"));
	}

}
