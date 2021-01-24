package com.escalab.academyappbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Información o Propiedades del Profesor")
@Entity
@Table(name="profesor")
public class Profesor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProfesor;

	@ApiModelProperty (notes = "Nombres debe tener como minimo 3 caracteres y máximo 70 caracteres.")
	@Size(min = 3, message = "Minimo 3 caracteres")
	@Column(name="nombres", nullable = false, length = 70)
	private String nombres;
	
	@ApiModelProperty (notes = "Apellidos debe tener como minimo 3 caracteres y máximo 70 caracteres.")
	@Size(min = 3, message = "Minimo 3 caracteres")
	@Column(name="apellidos", nullable = true, length = 70)
	private String apellidos;
	
	@ApiModelProperty (notes = "Nombres debe tener como máximo 150 caracteres.")
	@Column(name="direccion", nullable = false, length = 150)
	private String direccion;
	
	@Column(name="telefono", nullable = false, length = 9)
	private String telefono;
	
	@ApiModelProperty (notes = "Mail debe tener como minimo 70 caracteres y con el formato correcto.")
	@Column(name="email", nullable = false, length = 70)
	private String email;

	public Integer getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Integer idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
