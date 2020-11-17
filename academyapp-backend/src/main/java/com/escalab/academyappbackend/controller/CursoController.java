package com.escalab.academyappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.escalab.academyappbackend.exception.ExceptionsUtil;
import com.escalab.academyappbackend.model.Curso;
import com.escalab.academyappbackend.service.CursoService;

public class CursoController {

	@Autowired
	private CursoService  cursoService;
	
	@GetMapping("/curso")
	public @ResponseBody List<Curso> getAll(){
		return cursoService.getAll();
	}
	
	@PostMapping("/curso")
	public @ResponseBody Curso save( @RequestBody Curso curso) {
		return cursoService.save(curso);
	}


	@DeleteMapping("/curso/{id}")
	public @ResponseBody ResponseEntity<Object>  deleteById( @PathVariable int id) {
				
		Curso curso = cursoService.findById(id);
		if (curso.getIdCurso() == null) {
			throw new ExceptionsUtil("Curso " + id + "No encontrado");
		}
		cursoService.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PutMapping("/curso")
	public ResponseEntity<Curso> modificar( @RequestBody Curso curso) {
		return new ResponseEntity<Curso>(cursoService.update(curso), HttpStatus.OK);
	}
}
