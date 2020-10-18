package com.isistan.despensa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Producto{


	@Id
	@GeneratedValue(
			strategy= GenerationType.IDENTITY,
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
	
	@ManyToMany
	@JsonIgnore
	private List<Factura> facturas;
	
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
