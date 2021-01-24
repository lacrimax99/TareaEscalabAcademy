package com.escalab.academyappbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escalab.academyappbackend.exception.ModeloNotFoundException;
import com.escalab.academyappbackend.model.Usuario;
import com.escalab.academyappbackend.service.IUsuarioService;

@RestController
@RequestMapping("/usuario")
public class UserController {
	
	@Autowired
	private IUsuarioService userService;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Usuario>> listar(){
		List<Usuario> lista = userService.listar();
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> lista = userService.listar();
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
			Usuario obj = userService.leerPorId(id);
			if(obj.getIdUsuario() == null) {
				throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
			}
			return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
		}else {
			return new ResponseEntity<Usuario>(new Usuario(), HttpStatus.UNAUTHORIZED);
		}
		
	}

}
