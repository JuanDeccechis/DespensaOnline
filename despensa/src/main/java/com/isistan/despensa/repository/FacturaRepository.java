package com.isistan.despensa.repository;


import java.sql.Date;
import java.util.List;

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

	
	@Query( value = "select count(fp.factura_id) as cantidad from cliente c\r\n"
			  		+ "left join factura f ON f.cliente_id = c.id\r\n"
			  		+ "left join factura_productos fp ON f.id = fp.factura_id\r\n"
			  		+ "where c.id = ?1 and f.fecha = ?2\r\n"
			  		+ "group by c.id", 
			  nativeQuery = true)
	Integer getComprasDiaPorUsuario(Integer id, Date fecha);
}