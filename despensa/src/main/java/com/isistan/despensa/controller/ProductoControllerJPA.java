package com.isistan.despensa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isistan.despensa.model.Producto;
import com.isistan.despensa.repository.ProductoRepository;



/**
 * @author Belen Enemark
 * @author Juan Deccechis
 * @author Mateo Zarrabeitia
 * Esta clase se ocupa  de manejar los servicios rest de la clase Producto*/
@RestController
@RequestMapping("productos")
public class ProductoControllerJPA {
	
	@Qualifier("productoRepository")
	@Autowired
	private final ProductoRepository repository;

	/** Constructor que crea el Repository, y carga los productos con el LoadData*/
	public ProductoControllerJPA(@Qualifier("productoRepository") ProductoRepository repository) {
		this.repository = null;
	}
	
	
	/** Obtener todos los productos
	 * @return retorna un Iterable<Producto> con todos los productos*/
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

	/** Inserta un producto en la base de datos 
	 * @param producto objeto Producto con sus elementos para persistir
     * @return retorna el objeto creado, o en caso contrario retorna null con el status request
	 */
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
	
	/** Elimina un producto de la base de datos
	 * @param identificador del producto a borrar (id Integer)
	 * @return retorna un String(mensaje) si el producto fue eliminado o si no se pudo eliminar con el status request
	 * */
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

	/** Actualiza un producto de la base de datos
	 * @param identificador del producto a borrar (id Integer)
	 * @return retorna el objeto modificado, en caso contrario retorna un status request
	 * */
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
