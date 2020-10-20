package com.isistan.despensa.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isistan.despensa.dto.DTOFacturaClienteReporte;
import com.isistan.despensa.dto.DTOProductoMasVendido;
import com.isistan.despensa.dto.DTOReporteVentasDia;
import com.isistan.despensa.model.Factura;
import com.isistan.despensa.repository.FacturaRepository;



@RestController
@RequestMapping("facturas")
public class FacturaControllerJPA {

	@Qualifier("facturaRepository")
	@Autowired
	private final FacturaRepository repository;

	public FacturaControllerJPA(@Qualifier("facturaRepository") FacturaRepository repository) {
		this.repository = null;
	}

	@GetMapping("/") 
	@CrossOrigin
	public Iterable<Factura> getFacturas() { 
		return repository.findAll();
	}
	
	@GetMapping("/reporteClientes") 
	@CrossOrigin
	public Iterable<DTOFacturaClienteReporte> getReporteComprasClientes() { 
		return repository.getReporteCliente();
	}
	
	@GetMapping("/reporteVentasDia") 
	@CrossOrigin
	public Iterable<DTOReporteVentasDia> getReporteVentasDia() { 
		return repository.getReporteVentasPorDia();
	}

	@GetMapping("/productoMasVendido") 
	@CrossOrigin
	public Iterable<DTOProductoMasVendido> getProductoMasVendido() { 
		return repository.getProductoMasVendido();
	}

	@PostMapping("/")
	@CrossOrigin
	public Factura newFactura(@RequestBody Factura f) {
		Integer cantCompras = repository.getComprasDiaPorUsuario(f.getCliente().getId(), f.getFecha());
		if(cantCompras == null) {
			return repository.save(f);
		} else if(cantCompras < 3 && cantCompras+f.getProductos().size() <=3) {
			System.out.println(cantCompras+f.getProductos().size());
			return repository.save(f);
		}
		return null;
	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public void dropFactura(@PathVariable Integer id) { 
		repository.deleteById(id);
	}

	@PutMapping("/{id}")
	@CrossOrigin
	public void updateFactura(@RequestBody Factura f, @PathVariable Integer id) { 
		repository.deleteById(id);
		repository.save(f);
	}


}
