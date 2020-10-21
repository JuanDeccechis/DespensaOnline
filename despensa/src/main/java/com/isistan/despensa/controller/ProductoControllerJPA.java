package com.isistan.despensa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Iterable<Producto>> getProductos() { 
		try {
			List<Producto> productos = repository.findAll();
			if (productos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(productos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/")
	@CrossOrigin
	public ResponseEntity<Producto> newProducto(@RequestBody Producto p) { 
		p.setId(null);		
		try {
			if(p.getNombre() == null || p.getDescripcion() == null || p.getStock() == null || p.getPrecio() == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(repository.save(p), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public  ResponseEntity<String> dropProducto(@PathVariable Integer id) { 
		try {
			repository.deleteById(id);
			return new ResponseEntity<>("Se elimino el producto",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("El producto no existe o no se pudo eliminar",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<Producto> updateProducto(@RequestBody Producto p, @PathVariable Integer id) { 
		if (repository.existsById(id)) {
			return new ResponseEntity<>(repository.save(p), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/BySurname/{surname}")
	@CrossOrigin
	public Iterable<Producto> getProductoBySurname(@PathVariable String surname) { 
		return repository.findAllByname(surname);
	}

}
