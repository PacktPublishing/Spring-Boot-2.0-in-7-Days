package com.simonis.reactive;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;


import reactor.test.StepVerifier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureWebTestClient
 
public class ReactiveEBookStoreApplicationTests {
	@Autowired 
	WebTestClient client;
	
	@Before
	public void setup() {
		client = WebTestClient.bindToServer()
	.baseUrl("http://localhost:8080")
				.build();
	}
	@Test
	public void testreactive() {
		FluxExchangeResult<Person> result = client
		.get().uri("/api/authors").accept(MediaType.APPLICATION_JSON)
		.exchange().expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
		.returnResult(Person.class);

		StepVerifier.create(result.getResponseBody())
		.consumeNextWith(p ->
		assertThat(p.getLastName(),is("Rowling"))
				)
				.expectNextCount(1)
				.expectComplete()
				.verify();
	}

}
