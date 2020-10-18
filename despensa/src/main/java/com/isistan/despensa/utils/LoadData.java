package com.isistan.despensa.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

	@Bean
	CommandLineRunner initDataBaseCliente(@Qualifier("clienteRepository") ClienteRepository repository) {
		List<Cliente> datos = new ArrayList<Cliente> ();
		datos.add(new Cliente(1,"Enemark", "Belen", 1111));
		datos.add(new Cliente(2,"Deccechis", "Juan", 1234));
		datos.add(new Cliente(3,"Zarrabeitia", "Mateo", 9999));
		repository.saveAll(datos);
		return null;
	}
	
	
	@Bean
	CommandLineRunner initDataBaseProducto(@Qualifier("productoRepository") ProductoRepository repository) {
		List<Producto> datos = new ArrayList<Producto> ();
		datos.add(new Producto(1,"Lavandina","Lorem lorem", 10, 150.00));
		datos.add(new Producto(2,"Tallarines","Lorem lorem", 10, 100.00));
		datos.add(new Producto(3,"Detergente","Lorem lorem", 10, 115.00));
		datos.add(new Producto(4,"Coca Cola","Lorem lorem", 10, 150.00));
		repository.saveAll(datos);
		return null;
	}
	
	@Bean
	CommandLineRunner initDataBaseFactura(@Qualifier("facturaRepository") FacturaRepository repository) {
		List<Producto> productos = new ArrayList<Producto> ();
		List<Factura> facturas = new ArrayList<Factura> ();
		productos.add(new Producto(1,"Lavandina","Lorem lorem", 10, 150.00));
		productos.add(new Producto(2,"Tallarines","Lorem lorem", 10, 100.00));
		productos.add(new Producto(3,"Detergente","Lorem lorem", 10, 115.00));
		facturas.add(new Factura(new Cliente(1,"Enemark", "Belen", 1111),productos, new Date(System.currentTimeMillis())));
		repository.saveAll(facturas);
		return null;
	}
}
