package com.ebookstore.app;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(secure=false)
public class EBSRestTest {

	@MockBean
	private EBookStoreService ebsservice;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonRepository personrepository;
	
	private final List<Book> books = new ArrayList<Book>();

	@Test
	public void testresttemplate() {
		TestRestTemplate template = new TestRestTemplate();
		template
				.getForObject("http://localhost:8080", String.class)
				.contains("EBookStore");
	}
	
	
	
	
	private String examplePostRes = "{\"title\":\"Lotta Life\",\r\n" + 
			"	\"description\":\"young girl tells about her life\",\r\n" + 
			"	\"price\":0.0,\r\n" + 
			"	\"publishDate\":\"2005-01-01\",\r\n" + 
			"	\"path\":\"\",\r\n" + 
			"	\"id\":1}";
	@Before
	public void setup()
	{
		byte[] file = new byte[255];
		books.add(new Book("test","test",0.0, Date.valueOf("2001-12-12"), "",file));
		when(ebsservice.getBooks()).thenReturn(books);
	}
	
	public void testGetService() throws Exception
	{
		mockMvc.perform(get("/rest/books").with(httpBasic("psimonis","1234")))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)));
	}
	
	public void testPostService() throws Exception
	{
		byte[] file = new byte[255];
		Book newbook = new Book("test2","desc",0,Date.valueOf("2001-09-01"),"",file);
		newbook.setId(1L);
		Mockito.when(ebsservice.newBook(Mockito.any(Book.class)))
				.thenReturn(newbook);
		
		
		MvcResult result = mockMvc.perform(post("/rest/books/new")
				.with(httpBasic("psimonis","1234"))
					.accept(MediaType.APPLICATION_JSON)
					.content(examplePostRes)
					.contentType(MediaType.APPLICATION_JSON))
					.andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	
	@Test
	public void personsITTest() throws Exception
	{
		Person author = new Person("newuser", "newpassword", "Chris", "Fox", "AUTHOR");
		Optional<Person> p = Optional.of(author);
		when(personrepository.findById((long) 1))
		.thenReturn(p);
		
		mockMvc.perform(get("/rest/persons/1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString(("newuser"))));
		
	}
}
