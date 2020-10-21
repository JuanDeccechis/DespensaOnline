package com.isistan.despensa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isistan.despensa.model.Cliente;
import com.isistan.despensa.repository.ClienteRepository;

@RestController
@RequestMapping("clientes")
public class ClienteControllerJPA {

	@Qualifier("clienteRepository")
	@Autowired
	private final ClienteRepository repository;

	public ClienteControllerJPA(@Qualifier("clienteRepository") ClienteRepository repository) {
		this.repository = null;
	}

	@GetMapping("/") 
	@CrossOrigin
	public ResponseEntity<List<Cliente>> getPersons() {
		try {
			List<Cliente> clientes = repository.findAll();
			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/")
	@CrossOrigin
	public ResponseEntity<Cliente> newCliente(@RequestBody Cliente c) { 
		c.setId(null);		
		try {
			if(c.getApellido() == null || c.getDni() == null || c.getNombre() == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(repository.save(c), HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<String> dropCliente(@PathVariable Integer id) { 
		try {
			repository.deleteById(id);
			return new ResponseEntity<>("Se elimino el cliente",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("El cliente no existe o no se pudo eliminar",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente c, @PathVariable Integer id) { 
		if (repository.existsById(id)) {
			return new ResponseEntity<>(repository.save(c), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/BySurname/{surname}")
	@CrossOrigin
	public Iterable<Cliente> getClientesBySurname(@PathVariable String surname) { 
		return repository.findAllBySurname(surname);
	}

}
