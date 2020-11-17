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
import org.springframework.web.bind.annotation.RestController;

import com.escalab.academyappbackend.exception.ExceptionsUtil;
import com.escalab.academyappbackend.model.Profesor;
import com.escalab.academyappbackend.service.ProfesorService;

@RestController
public class ProfesorController {

	@Autowired
	private ProfesorService  profesorService;
	
	@GetMapping("/profesor")
	public @ResponseBody List<Profesor> getAll(){
		return profesorService.getAll();
	}
	
	@PostMapping("/profesor")
	public @ResponseBody Profesor save( @RequestBody Profesor profesor) {
		return profesorService.save(profesor);
	}


	@DeleteMapping("/profesor/{id}")
	public @ResponseBody ResponseEntity<Object>  deleteById(@PathVariable int id) {
		profesorService.deleteById(id);
		
		Profesor profesor = profesorService.findById(id);
		if (profesor.getIdProfesor() == null) {
			throw new ExceptionsUtil("Estudiante " + id + "No encontrado");
		}
		profesorService.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PutMapping("/profesor")
	public ResponseEntity<Profesor> modificar( @RequestBody Profesor profesor) {
		return new ResponseEntity<Profesor>(profesorService.update(profesor), HttpStatus.OK);
	}
}
