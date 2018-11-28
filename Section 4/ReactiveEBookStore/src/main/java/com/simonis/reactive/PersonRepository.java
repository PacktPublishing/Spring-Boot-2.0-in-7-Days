package com.simonis.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, Long> {

}
