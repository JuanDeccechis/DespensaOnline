package com.isistan.despensa.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.isistan.despensa.model.Cliente;
import com.isistan.despensa.repository.ClienteRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadData {

	@Bean
	CommandLineRunner initDataBase(@Qualifier("clienteRepository") ClienteRepository repository) {
		List<Cliente> datos = new ArrayList<Cliente> ();
		datos.add(new Cliente(1111, "Enemark", "Belen", 1111));
		datos.add(new Cliente(1234, "Deccechis", "Juan", 1234));
		datos.add(new Cliente(9999, "Zarrabeitia", "Mateo", 9999));
		repository.saveAll(datos);
		return null;
	}
}
