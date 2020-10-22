package com.isistan.despensa.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isistan.despensa.dto.DTOFacturaClienteReporte;
import com.isistan.despensa.dto.DTOProductoMasVendido;
import com.isistan.despensa.dto.DTOReporteVentasDia;
import com.isistan.despensa.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
	

	@Query("SELECT COUNT(f.id) as cantidad FROM Cliente c "
			+ "LEFT JOIN c.facturas f "
			+ "LEFT JOIN f.productos p "
			+ "WHERE c.id = ?1 AND f.fecha = ?2 "
			+ "GROUP BY c.id")
	Integer getComprasDiaPorUsuario(Integer id, Date fecha);


	@Query("SELECT c.id AS id, c.nombre AS nombre , c.apellido AS apellido, c.dni AS dni , SUM(p.precio) AS total FROM Cliente c "
			+ "JOIN c.facturas f "
			+ "JOIN f.productos p "
			+ "GROUP BY c.id")
	List<DTOFacturaClienteReporte> getReporteCliente();

	@Query("SELECT f.fecha as fecha, sum(p.precio) as total FROM Cliente c "
			+ "JOIN c.facturas f "
			+ "JOIN f.productos p "
			+ "GROUP BY CAST(f.fecha AS LocalDate)")
	List<DTOReporteVentasDia> getReporteVentasPorDia();
	

	@Query("SELECT p.id AS id, p.nombre AS nombre, p.precio AS precio, p.stock AS stock, p.descripcion AS descripcion, COUNT(p.id) AS cantidad FROM Factura f "
			+ "JOIN f.productos p "
			+ "GROUP BY p.id "
			+ "ORDER BY cantidad DESC")
	List<DTOProductoMasVendido> getProductoMasVendido();


	
}