package com.isistan.despensa.controller;

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

	@PostMapping("/")
	@CrossOrigin
	public Factura newFactura(@RequestBody Factura f) { 
		return repository.save(f);
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