package com.ebookstore.app;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(secure = false)

public class EBookStoreIT {

	@Autowired
	private MockMvc mvc;
	
	@MockBean 
	private BookRepository bookrepository;
	
	@MockBean
	private PersonRepository personrepository;
	
	List<Book> books = new ArrayList<Book>();

	Person author = new Person("psimonis","pw1234","Petra","Simonis","AUTHOR");
	
	
	@Test
	public void integrationTest() throws Exception
	{
		mvc.perform(get("/").with(httpBasic("psimonis","1234")))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
	@Test
	public void repositoryITTest() throws Exception
	{
		when(bookrepository.findAll())
			.thenReturn(books);
		
		mvc.perform(get("/books/list"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Booklist")))
			.andExpect(model().attributeExists("booklist"));
	}
	
	@Test
	public void personrepositoryITTest() throws Exception
	{
		when(personrepository.findById(1L))
		.thenReturn(Optional.of(author));
		
		mvc.perform(get("/rest/persons/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.lastName", containsString("Simonis")));
	}
	
}
