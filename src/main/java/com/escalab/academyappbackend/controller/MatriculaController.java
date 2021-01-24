package com.escalab.academyappbackend.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import javax.el.MethodNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escalab.academyappbackend.dto.MatriculaDTO;
import com.escalab.academyappbackend.model.Matricula;
import com.escalab.academyappbackend.service.IMatriculaService;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {
	
	@Autowired
	private IMatriculaService service;
	
	@GetMapping
	public ResponseEntity<List<Matricula>> listar() {
		List<Matricula> listar = service.listar();
		return new ResponseEntity<List<Matricula>>(listar, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Matricula> listarPorId(@PathVariable("id") Integer id) {
		Matricula matricula = service.leerPorId(id);
		if (matricula.getIdMatricula() == null) {
			throw new MethodNotFoundException(String.format("ID NO ENCONTRADO %s" + id));
		}
		return new ResponseEntity<Matricula>(matricula, HttpStatus.OK);
	}
	
	@GetMapping(value = "/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MatriculaDTO> listarHateoas() {
		List<Matricula> matriculas = new ArrayList<>();
		List<MatriculaDTO> matriculasDTO = new ArrayList<>();
		matriculas = service.listar();
		
		for (Matricula matricula : matriculas) {
			MatriculaDTO dto = new MatriculaDTO();
			dto.setIdMatricula(matricula.getIdMatricula());
			dto.setAlumno(matricula.getAlumno());
			dto.setCurso(matricula.getCurso());
			
			ControllerLinkBuilder linkTo = linkTo(methodOn(MatriculaController.class).listarPorId(matricula.getIdMatricula()));
			dto.add(linkTo.withSelfRel());
//			matriculasDTO.add(dto);
			
			ControllerLinkBuilder linkTo1 = linkTo(methodOn(AlumnoController.class).listarPorId(matricula.getAlumno().getId()));
			dto.add(linkTo1.withSelfRel());
//			matriculasDTO.add(dto);
			
			ControllerLinkBuilder linkTo2 = linkTo(methodOn(CursoController.class).listarPorId(matricula.getCurso().getIdCurso()));
			dto.add(linkTo2.withRel("xxx"));
			matriculasDTO.add(dto);
			
		}
		return matriculasDTO;
		
	}

}
