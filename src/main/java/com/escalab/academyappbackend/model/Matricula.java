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

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "matricula")
public class Matricula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMatricula;
	
	@ApiModelProperty (notes = "Fecha de registro de la matricula.")
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "id_alumno", nullable = false, foreignKey = @ForeignKey(name = "FK_matricula_alumno"))
	private Alumno alumno;
	
	@ManyToOne
	@JoinColumn(name = "id_curso", nullable = false, foreignKey = @ForeignKey(name = "FK_matricula_curso"))
	private Curso curso;
	
	@PrePersist
	public void prePersist() {
		this.fecha = new Date();
	}

	public Integer getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
