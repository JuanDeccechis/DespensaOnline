package com.isistan.despensa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isistan.despensa.model.Cliente;
import com.isistan.despensa.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	@Query("SELECT p FROM Producto p WHERE p.nombre = :name")
	public List<Producto> findAllByname(String name);
	
}