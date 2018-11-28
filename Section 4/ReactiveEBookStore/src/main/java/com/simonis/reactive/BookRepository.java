package com.simonis.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<Book, Long>{

}
