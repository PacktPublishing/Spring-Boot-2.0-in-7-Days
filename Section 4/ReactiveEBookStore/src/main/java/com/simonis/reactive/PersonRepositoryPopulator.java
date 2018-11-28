package com.simonis.reactive;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;

@Component
public class PersonRepositoryPopulator  implements CommandLineRunner {

	static final Logger LOG = LoggerFactory
			.getLogger(PersonRepositoryPopulator.class);
	private final ResourceLoader resourceLoader;

	private final ObjectMapper objectMapper;
	private final PersonRepository personrepository;
	
	
	public PersonRepositoryPopulator(ResourceLoader resources, 
			ObjectMapper objects, PersonRepository repo)
	{
		this.resourceLoader=resources;
		this.objectMapper=objects;
		this.personrepository=repo;
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		final Person[] authors = loadAuthors();

		this.personrepository
			.deleteAll()
			.thenMany(Flux.just(authors))
			.flatMap(f -> this.personrepository.save(f))
			//.flatMap(f->this.bookrepository.saveAll(f.getBooks()))
			.doOnNext(f -> 
				LOG.info("Author '{}' (id={}) saved", 
					f.getLastName(), f.getId()))
			.blockLast();		
	}
	private Person[] loadAuthors() throws IOException {
		
		return this.objectMapper.readValue(
				this.resourceLoader.getResource("classpath:/data.json").getInputStream(),
				Person[].class);
	}
}
