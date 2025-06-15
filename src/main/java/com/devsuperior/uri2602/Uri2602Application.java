package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dto.CustomerMinDto;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner{
	
	private final CustomerRepository repository;
	
	public Uri2602Application(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CustomerMinProjection> list = repository.search1("rS");
		// converter para DTO
		List<CustomerMinDto> result1 = list.stream().map(x -> new CustomerMinDto(x)).collect(Collectors.toList());
		
		System.out.println("\n*** RESULTADO SQL RAIZ: ***");
		for(CustomerMinDto obj : result1) {
			System.out.println(obj);
		}
		System.out.println("\n\n");
		
		List<CustomerMinDto> result2 = repository.search2("rS");
		
		System.out.println("\n*** RESULTADO JQL: ***");
		for(CustomerMinDto obj : result2) {
			System.out.println(obj);
		}
	}
}
