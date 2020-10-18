package com.isistan.despensa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isistan.despensa.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

//	@Modifying
//	@Query("INSERT Factura f set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//	void setUserInfoById(String firstname, String lastname, Integer userId);

}