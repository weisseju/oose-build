package de.oose.environmentservice.web;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
 * test the web frontend with mockMvc. That checks all internals are setup correctly.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = App.class)
public class WebServletMockMvcTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetBasicViewReturns200Status() throws Exception {
		mockMvc.perform(get("/view")).andExpect(status().isOk());
	}

	@Test
	public void testGetBasicViewReturnsTextMediaType() throws Exception {
		mockMvc.perform(get("/view")).andExpect(content().contentTypeCompatibleWith("text/html"));
	}

	@Test
	public void testGetBasicViewReturnsSimpleStringAsContent() throws Exception {
		MvcResult result = mockMvc.perform(get("/view")).andReturn();
		// the template is resolved
		assertTrue(result.getResponse().getContentAsString().contains("<html>"));

	}
}
