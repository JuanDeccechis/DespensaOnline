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
public class Cliente {

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
	private String apellido;

	@Column
	private String nombre;

	@Column
	private Integer dni;

	public Cliente(Integer id, String apellido, String nombre, Integer dni) {
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
	}

	public Cliente(String apellido, String nombre, Integer dni) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
	}

	public Cliente() {
	}


}
