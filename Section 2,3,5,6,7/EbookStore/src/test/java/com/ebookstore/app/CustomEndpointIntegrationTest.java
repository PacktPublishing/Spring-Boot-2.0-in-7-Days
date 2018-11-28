package com.ebookstore.app;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)

@SpringBootTest
@AutoConfigureMockMvc

public class CustomEndpointIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext context;
	
	
	 @Before
	    public void setup() {


	        mockMvc = MockMvcBuilders
	                .webAppContextSetup(context)
	                .alwaysDo(print())
	                .build();
	    }

	
	@Test
	public void integrationTest() throws Exception
	{
		mockMvc.perform(get("/management/custom").with(httpBasic("psimonis","1234")))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.books",notNullValue()))
			.andExpect(jsonPath("$.authors",notNullValue()));
	}
}
