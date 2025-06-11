package com.devsuperior.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2602.dto.CustomerMinDto;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	// SQL nativo. 														 
	@Query(nativeQuery = true, value = "SELECT name "
			+ "FROM customers "
			+ "WHERE UPPER(state) = UPPER(:state)") // 'UPPER' Converte tudo para maiúsculo. ':state' Pega o parâmetro do método
	List<CustomerMinProjection> search1(String state);
	
	// JPQL		   Busca novos objetos desse tipo  passa o construtor(name)
	@Query("SELECT new com.devsuperior.uri2602.dto.CustomerMinDto(obj.name) "
			+ "FROM Customer obj " // a partir dos objetos 'Customer obj'
			+ "WHERE UPPER(obj.state) = UPPER(:state)")
	List<CustomerMinDto> search2(String state);

}
