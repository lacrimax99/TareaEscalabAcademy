package com.escalab.academyappbackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Información o Propiedades del Curso")
@Entity
@Table(name = "curso")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCurso;
	
	@ApiModelProperty (notes = "Nombre debe tener como minimo 70 caracteres.")
	@Column(name ="nombre", nullable = false, length = 70)
	private String nombre;
	
	@ApiModelProperty (notes = "Nombre debe tener como minimo 3 caracteres.")
	@Column(name="create_At")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@ManyToOne
	@JoinColumn(name ="id_profesor", nullable = false, foreignKey = @ForeignKey(name = "FK_profesor_curso"))
	private Profesor profesor;

	public Curso() {
		super();
	}
	
	public Curso(Integer idCurso, String nombre, Profesor profesor) {
		super();
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.profesor = profesor;
	}

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nombre=" + nombre + ", profesor=" + profesor + "]";
	}
	
}
