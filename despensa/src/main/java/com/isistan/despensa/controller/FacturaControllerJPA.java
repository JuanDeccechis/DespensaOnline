package com.isistan.despensa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.isistan.despensa.model.Cliente;
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
	public ResponseEntity<Iterable<Factura>> getFacturas() { 
		try {
			List<Factura> facturas = repository.findAll();
			if (facturas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(facturas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/reporteClientes") 
	@CrossOrigin
	public ResponseEntity<Iterable<DTOFacturaClienteReporte>> getReporteComprasClientes() { 
		try {
			List<DTOFacturaClienteReporte> facturas = repository.getReporteCliente();
			if (facturas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(facturas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/reporteVentasDia") 
	@CrossOrigin
	public ResponseEntity<Iterable<DTOReporteVentasDia>> getReporteVentasDia() { 
		try {
			List<DTOReporteVentasDia> facturas = repository.getReporteVentasPorDia();
			if (facturas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(facturas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/productoMasVendido") 
	@CrossOrigin
	public ResponseEntity<Iterable<DTOProductoMasVendido>> getProductoMasVendido() { 
		try {
			List<DTOProductoMasVendido> facturas = repository.getProductoMasVendido();
			if (facturas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(facturas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/")
	@CrossOrigin
	public ResponseEntity<Factura> newFactura(@RequestBody Factura f) {

		try {
			Integer cantCompras = repository.getComprasDiaPorUsuario(f.getCliente().getId(), f.getFecha());
			if(cantCompras == null) {
				return new ResponseEntity<>(repository.save(f), HttpStatus.CREATED);
			} else if(cantCompras < 3 && cantCompras+f.getProductos().size() <=3) {
				return new ResponseEntity<>(repository.save(f), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<String> dropFactura(@PathVariable Integer id) { 
		try {
			if(repository.existsById(id)) {
			repository.deleteById(id);
			return new ResponseEntity<>("Se elimino la factura",HttpStatus.OK);
			} else {
				return new ResponseEntity<>("La factura no existe o no se pudo eliminar",HttpStatus.NOT_MODIFIED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("La factura no existe o no se pudo eliminar",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<Factura> updateFactura(@RequestBody Factura f, @PathVariable Integer id) { 
		if (repository.existsById(id)) {
			return new ResponseEntity<>(repository.save(f), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
