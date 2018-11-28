package com.ebookstore.app;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ebookstore.config.MyUserDetailsService;
import com.ebookstore.config.MyUserPrincipal;

@RunWith(SpringRunner.class)
@WebMvcTest

public class BookControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@Test
	public void doGetTest() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/books/list")
				.accept(MediaType.TEXT_HTML))		
        		.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="psimonis",password="1234")
	public void doCreateNewBookTest() throws Exception
	{
		
		mvc.perform(MockMvcRequestBuilders.get("/books/new")
				.accept(MediaType.TEXT_HTML))		
        		.andExpect(status().isOk())
        		.andExpect(view().name("book"));
	}
	
	
}
