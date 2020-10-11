package com.isistan.despensa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id
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
	
	public Cliente() {
	}
	

}
