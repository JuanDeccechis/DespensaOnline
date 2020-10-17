package com.isistan.despensa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Producto {
	
	@Id
	@GeneratedValue(
			strategy= GenerationType.AUTO,
			generator="native"
			)
	@GenericGenerator(
			name = "native",
			strategy = "native"
			)
	private Integer id;

	@Column
	private String nombre;

	@Column
	private String descripcion;


	@Column
	private Integer cantidad;
	
	@Column
	private Double precio;
	
	public Producto(Integer id, String nombre, String descripcion, Integer cantidad, Double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public Producto(String nombre, String descripcion, Integer cantidad, Double precio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public Producto() {
		super();
	}	
	


}
