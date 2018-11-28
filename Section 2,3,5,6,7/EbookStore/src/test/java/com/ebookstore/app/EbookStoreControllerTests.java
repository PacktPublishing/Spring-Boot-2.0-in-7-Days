package com.ebookstore.app;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(secure = false)

public class EbookStoreControllerTests {

	@Autowired
	private MockMvc mvc;



	@Test
	public void getIndex() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/")
				.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk());
	}

	@Test
	public void getLogin() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/login")
				.accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk());
			
	}
}
