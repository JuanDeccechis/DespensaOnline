package com.isistan.despensa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isistan.despensa.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("SELECT c FROM Cliente c WHERE c.apellido = :surname")
	public List<Cliente> findAllBySurname(String surname);
	
}
