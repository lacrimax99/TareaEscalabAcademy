package com.escalab.academyappbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCurso;
	
	@Column(name ="nombre", nullable = false, length = 70)
	private String nombre;
	
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
