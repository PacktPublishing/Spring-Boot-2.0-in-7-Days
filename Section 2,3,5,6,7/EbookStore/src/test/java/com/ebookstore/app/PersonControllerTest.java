package com.ebookstore.app;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(secure=false)

public class PersonControllerTest {

	@Autowired
    private MockMvc mvc;
	
	private Person person;
	
	private HttpHeaders httpHeaders = new HttpHeaders();
	
	@Test
	public void getPersonIndex() throws Exception
	{
		
		mvc.perform(MockMvcRequestBuilders.get("/person/new"))
				.andExpect(status().isOk())
        		.andExpect(view().name("account"));
        
	}
	
	@Test
	public void postPerson() throws Exception
	{
		person = new Person();
		person.setFirstName("Christian");
		person.setLastName("Becker");
		person.setUsername("chris_b");
		person.setPassword("1235");
		person.setUserRole("AUTHOR");
		
		mvc.perform(MockMvcRequestBuilders.post("/person/new", person)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.flashAttr("person", person))
				.andExpect(status().isOk())
				.andExpect(view().name("account"))
				.andExpect(model().attribute("person", person));
	}
}
