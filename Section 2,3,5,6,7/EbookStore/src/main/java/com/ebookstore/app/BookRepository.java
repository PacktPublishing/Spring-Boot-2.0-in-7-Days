package com.ebookstore.app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query(value = "select * from Book b where b.p_id=:id", nativeQuery = true)
	List<Book> findBooksByAuthor(@Param("id") Long id);

	@Query(value = "select * from Book b where b.title like %:title%", nativeQuery = true)
	List<Book> findBooksByTitle(@Param("title") String title);
}
