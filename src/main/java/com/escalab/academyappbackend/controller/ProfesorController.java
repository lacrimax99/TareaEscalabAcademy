package com.escalab.academyappbackend.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escalab.academyappbackend.exception.ExceptionsUtil;
import com.escalab.academyappbackend.model.Profesor;
import com.escalab.academyappbackend.service.IProfesorService;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
	
	@Autowired
	private IProfesorService  profesorService;
	
	@GetMapping
	public ResponseEntity<List<Profesor>> getAll(){
		List<Profesor> listAlumnos = profesorService.listar();
		if (listAlumnos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Profesor>>(listAlumnos, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public @ResponseBody ResponseEntity<Object>  deleteById(@PathVariable int id) {
		profesorService.deleteById(id);
		
		Profesor profesor = profesorService.leerPorId(id);
		if (profesor.getIdProfesor() == null) {
			throw new ExceptionsUtil("Estudiante " + id + "No encontrado");
		}
		profesorService.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Profesor> modificar( @RequestBody Profesor profesor) {
		return new ResponseEntity<Profesor>(profesorService.modificar(profesor), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Profesor profesor) {
		profesorService.registrar(profesor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(profesor.getIdProfesor()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
