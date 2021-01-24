package com.escalab.academyappbackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Información o Propiedades del Alumno")
@Entity
@Table(name = "alumnos")
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ApiModelProperty (notes = "Nombre debe tener como minimo 3 caracteres.")
	@Size(min = 3, message = "Minimo 3 caracteres")
	@Column(name="nombres", nullable = false, length = 70)
	private String nombres;
	
	@ApiModelProperty (notes = "Apellido debe tener como minimo 70 caracteres.")
	@Column(name="apellidos", nullable = false, length = 70)
	private String apellidos;
	
	@ApiModelProperty (notes = "Mail debe tener como minimo 70 caracteres y con el formato correcto.")
	@Email
	@Column(name="mail", nullable = false, length = 70)
	private String mail;
	
	@ApiModelProperty (notes = "Fecha de creación del alumno.")
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
}
