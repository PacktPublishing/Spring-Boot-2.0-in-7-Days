package com.ebookstore.app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "select ifnull(max(o.id),0) from Orders o", nativeQuery = true)
	Long getMaxId();

	@Query(value = "select ifnull(max(o.order_num),0) from Orders o where o.person_id=:id", nativeQuery = true)
	Long getMaxOrdernumFromCustomer(@Param("id") Long id);

	@Query(value = "select * from Orders o where o.person_id=:id", nativeQuery = true)
	List<Order> getOrdersByCustomer(@Param("id") Long id);
}
