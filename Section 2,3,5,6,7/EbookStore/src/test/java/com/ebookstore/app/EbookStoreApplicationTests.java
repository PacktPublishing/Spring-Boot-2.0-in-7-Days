package com.ebookstore.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.ebookstore.app.BookController;
import com.ebookstore.app.EbookStoreController;
import com.ebookstore.app.PersonController;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EbookStoreApplicationTests {

	@Autowired
	EbookStoreController ebscontroller;
	@Test
	public void contextLoads() {
	
		assertThat(ebscontroller).isNotNull();
		
	}
	
	

}
