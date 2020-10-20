package com.isistan.despensa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;
import com.isistan.despensa.model.Cliente;
import com.isistan.despensa.model.Factura;
import com.isistan.despensa.model.Producto;
import com.isistan.despensa.repository.ClienteRepository;
import com.isistan.despensa.repository.FacturaRepository;
import com.isistan.despensa.repository.ProductoRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadData {
	final static int CANTMAXPRODUCTOS = 10;
	final static int CANTMAXCLIENTES = 10;
	final static int CANTMAXFACTURAS = 50;
	@Bean
	CommandLineRunner initDataBaseCliente(@Qualifier("clienteRepository") ClienteRepository repository) {
		Faker faker = new Faker(new Locale("es"));
		List<Cliente> datos = new ArrayList<Cliente> ();
		for (int i = 1; i <= CANTMAXCLIENTES; i++) {
			datos.add(new Cliente(i, faker.name().lastName(), faker.name().firstName(), Integer.parseInt(faker.phoneNumber().subscriberNumber(8))));
		}
//		datos.add(new Cliente(1,"Enemark", "Belen", 1111));
//		datos.add(new Cliente(2,"Deccechis", "Juan", 1234));
//		datos.add(new Cliente(3,"Zarrabeitia", "Mateo", 9999));
		repository.saveAll(datos);
		return null;
	}
	
	
	@Bean
	CommandLineRunner initDataBaseProducto(@Qualifier("productoRepository") ProductoRepository repository) {
		Faker faker = new Faker(new Locale("es"));
		List<Producto> datos = new ArrayList<Producto> ();
		for (int i = 1; i <= CANTMAXPRODUCTOS; i++) {
			datos.add(new Producto(i,faker.commerce().productName(),faker.lorem().word(), faker.number().numberBetween(1, 50), faker.number().randomDouble(2, 50, 500)));
		}
//		datos.add(new Producto(1,"Lavandina","Lorem lorem", 10, 150.00));
//		datos.add(new Producto(2,"Tallarines","Lorem lorem", 10, 100.00));
//		datos.add(new Producto(3,"Detergente","Lorem lorem", 10, 115.00));
//		datos.add(new Producto(4,"Coca Cola","Lorem lorem", 10, 150.00));
		repository.saveAll(datos);
		return null;
	}
	
	@Bean
	CommandLineRunner initDataBaseFactura(@Qualifier("facturaRepository") FacturaRepository repository) {
		Faker faker = new Faker(new Locale("es"));
		List<Factura> facturas = new ArrayList<Factura> ();
		for (int i = 1; i <= CANTMAXCLIENTES; i++) {
			List<Producto> productos = new ArrayList<Producto> ();
			for (int j = 1; j <= faker.number().numberBetween(1, 3); j++) {
				productos.add(new Producto(faker.number().numberBetween(1, CANTMAXPRODUCTOS),"","", 0,0.00));
			}
			facturas.add(new Factura(new Cliente(i,"", "", 0),productos, faker.date().past(faker.number().numberBetween(3, 8), TimeUnit.DAYS)));
		}
		repository.saveAll(facturas);
		return null;
	}
}
