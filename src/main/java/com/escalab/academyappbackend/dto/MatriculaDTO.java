package com.escalab.academyappbackend.dto;

import org.springframework.hateoas.ResourceSupport;

import com.escalab.academyappbackend.model.Alumno;
import com.escalab.academyappbackend.model.Curso;


public class MatriculaDTO extends ResourceSupport {

	private Integer idMatricula;
	
	private Alumno alumno;
	
	private Curso curso;

	public Integer getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
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
