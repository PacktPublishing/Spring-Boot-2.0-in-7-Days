package com.ebookstore.app;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.web.ManagementContextConfiguration;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@Endpoint(id = "custom")

class CustomEndpoint {

	@Autowired
	private EBookStoreService eservice;

	@ReadOperation
	public ConcurrentMap<String, Integer> getOperation() {
		ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

		for (Book b : eservice.getBooks()) {
			map.put("books", map.getOrDefault("books", 0) + 1);
		}

		for (Person p : eservice.getAuthors()) {
			map.put("authors", map.getOrDefault("authors", 0) + 1);
		}
		return map;
	}

	@WriteOperation
	public String writeOperation() {
		return "write operation";
	}

	@DeleteOperation
	public String deleteOperation() {
		return "delete operation";
	}

}
