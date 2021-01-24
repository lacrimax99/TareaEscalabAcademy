package com.escalab.academyappbackend.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escalab.academyappbackend.exception.ModeloNotFoundException;
import com.escalab.academyappbackend.model.Alumno;
import com.escalab.academyappbackend.model.Profesor;
import com.escalab.academyappbackend.service.IAlumnoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
	
	@Autowired
	private IAlumnoService alumnoService;
	
	@ApiOperation(value = "Obtener los alumnos",
			notes = "No es necesario parametros",
			response = List.class,
			responseContainer = "Alumnos")
	@ApiResponses (value = { 
			@ApiResponse (code = 200, message = "La solicitud ha tenido éxito")})
	@GetMapping
	public ResponseEntity<List<Alumno>> listar(){
		List<Alumno> lista = alumnoService.listar();
		return new ResponseEntity<List<Alumno>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Alumno>> getAll(){
		List<Alumno> lista = alumnoService.listar();
		return new ResponseEntity<List<Alumno>>(lista, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Obtener un alumno por id.",
			notes = "Es necesario ingresar el id cómo parametro",
			response = Alumno.class,
			responseContainer = "Alumno")
	@ApiResponses (value = { 
			@ApiResponse (code = 200, message = "La solicitud ha tenido éxito"),
			@ApiResponse (code = 404, message = "Not found, no encontrado"),
			@ApiResponse (code = 401, message = "Sin autorización") })
	@GetMapping("/{id}")
	public ResponseEntity<Alumno> listarPorId(@PathVariable("id") Integer id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			Alumno obj = alumnoService.leerPorId(id);
			if(obj.getId() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			return new ResponseEntity<Alumno>(obj, HttpStatus.OK);
		}else {
			return new ResponseEntity<Alumno>(new Alumno(), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@ApiOperation(value = "Mofificar alumno.",
			notes = "Es necesario enviar el alumno como parametro.")
	@ApiResponses (value = { 
			@ApiResponse (code = 200, message = "Modificado exitosamente") })
	@PutMapping
	public ResponseEntity<Alumno> modificar( @RequestBody Alumno alumno) {
		return new ResponseEntity<Alumno>(alumnoService.modificar(alumno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Registra alumno.",
			notes = "Es necesario enviar el alumno como parametro.")
	@ApiResponses (value = { 
			@ApiResponse (code = 201, message = "Registrado exitosamente") })
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Alumno alumno) {
		Alumno obj = alumnoService.registrar(alumno);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
