package com.isistan.despensa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.isistan.despensa.model.Cliente;
import com.isistan.despensa.model.Producto;
import com.isistan.despensa.repository.ClienteRepository;
import com.isistan.despensa.repository.ProductoRepository;

@RestController
@RequestMapping("productos")
public class ProductoControllerJPA {

	@Qualifier("productoRepository")
	@Autowired
	private final ProductoRepository repository;

	public ProductoControllerJPA(@Qualifier("productoRepository") ProductoRepository repository) {
		this.repository = null;
	}

	@GetMapping("/") 
	@CrossOrigin
	public Iterable<Producto> getProductos() { 
		return repository.findAll();
	}

	@PostMapping("/")
	@CrossOrigin
	public Producto newProducto(@RequestBody Producto p) { 
		return repository.save(p);
	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public void dropProducto(@PathVariable Integer id) { 
		repository.deleteById(id);
	}

	@PutMapping("/{id}")
	@CrossOrigin
	public void updateProducto(@RequestBody Producto p, @PathVariable Integer id) { 
		repository.deleteById(id);
		repository.save(p);
	}

	@GetMapping("/BySurname/{surname}")
	@CrossOrigin
	public Iterable<Producto> getProductoBySurname(@PathVariable String surname) { 
		return repository.findAllByname(surname);
	}

}
