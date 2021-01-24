package com.escalab.academyappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.escalab.academyappbackend.exception.ExceptionsUtil;
import com.escalab.academyappbackend.exception.ModeloNotFoundException;
import com.escalab.academyappbackend.model.Curso;
import com.escalab.academyappbackend.model.Usuario;
import com.escalab.academyappbackend.service.ICursoService;

public class CursoController {

	@Autowired
	private ICursoService  cursoService;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/curso")
	public @ResponseBody ResponseEntity<List<Curso>> getAll(){
		return  (ResponseEntity<List<Curso>>) cursoService.listar();
	}
	
	@PostMapping("/curso")
	public @ResponseBody Curso save( @RequestBody Curso curso) {
		return cursoService.registrar(curso);
	}


	@DeleteMapping("/curso/{id}")
	public @ResponseBody ResponseEntity<Object>  deleteById( @PathVariable int id) {
				
		Curso curso = cursoService.leerPorId(id);
		if (curso.getIdCurso() == null) {
			throw new ExceptionsUtil("Curso " + id + "No encontrado");
		}
		cursoService.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PutMapping("/curso")
	public ResponseEntity<Curso> modificar( @RequestBody Curso curso) {
		return new ResponseEntity<Curso>(cursoService.modificar(curso), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> listarPorId(@PathVariable("id") Integer id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			Curso obj = cursoService.leerPorId(id);
			if(obj.getIdCurso() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			return new ResponseEntity<Curso>(obj, HttpStatus.OK);
		}else {
			return new ResponseEntity<Curso>(new Curso(), HttpStatus.UNAUTHORIZED);
		}
		
	}
}
