package com.ebookstore.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("select p from Person p where p.username=:username")
	Person findByUsername(@Param("username") String username);

	@Query("select max(p.id) from Person p")
	Long getMaxId();
}
