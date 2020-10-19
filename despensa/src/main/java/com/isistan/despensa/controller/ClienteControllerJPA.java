package com.isistan.despensa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	public Iterable<Cliente> getPersons() { 
		return repository.findAll();
	}
	
	@PostMapping("/")
	@CrossOrigin
	public Cliente newCliente(@RequestBody Cliente c) { 
		return repository.save(c);
	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public void dropCliente(@PathVariable Integer id) { 
		repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@CrossOrigin
	public void updateCliente(@RequestBody Cliente c, @PathVariable Integer id) { 
		repository.save(c);
	}
	
	@GetMapping("/BySurname/{surname}")
	@CrossOrigin
	public Iterable<Cliente> getClientesBySurname(@PathVariable String surname) { 
		return repository.findAllBySurname(surname);
	}
	
}
