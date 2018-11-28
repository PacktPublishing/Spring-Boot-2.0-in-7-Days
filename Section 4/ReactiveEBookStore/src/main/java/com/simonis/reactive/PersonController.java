package com.simonis.reactive;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {

	private PersonRepository personrepository;
	private BookRepository bookrepository;
	public PersonController(PersonRepository p, BookRepository b)
	{
		this.personrepository = p;
		this.bookrepository = b;
	}
	
	@GetMapping("/api/authors")
	public Flux<Person> getAllAuthors(){
		return personrepository.findAll(Sort.by("lastName")
				.ascending());
	}
	
	@GetMapping("/api/authors/{id}")
	public Mono<Person> getAuthorById(@PathVariable Long id)
	{
		return personrepository.findById(id);
	}
	
	@GetMapping("/api/authors/{id}/books")
	public Flux<Book> getBooksByAuthor(@PathVariable Long id)
	{
		return personrepository.findById(id)
				.flatMapMany(p->Flux.fromIterable(p.getBooks()));
				
		
	}
	
	@PostMapping("/api/authors")
	public Mono<Person> insertAuthor(@RequestBody Person newperson)
	{
		
		return personrepository.save(newperson);
	}
	
	@PutMapping("/api/authors/{id}")
	public Mono<Person> updateAuthor(@RequestBody Person updatePerson, 
			@PathVariable Long id)
	{
		return personrepository.findById(id)
		.doOnSuccess(f->{
			f.setFirstName(updatePerson.getFirstName());
			f.setLastName(updatePerson.getLastName());
			f.setPassword(updatePerson.getPassword());
			f.setUsername(updatePerson.getUsername());
			f.setUserRole(updatePerson.getUserRole());
			f.setBooks(updatePerson.getBooks());
			
			personrepository.save(f).subscribe();
		});
		
	}
	
	@DeleteMapping("/api/authors/{id}")
	public Mono<Void> deleteAuthor(@PathVariable Long id)
	{
		return personrepository.deleteById(id);
	}
}
