package com.escalab.academyappbackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.escalab.academyappbackend.model.Usuario;
import com.escalab.academyappbackend.repo.IUsuarioRepo;
import com.escalab.academyappbackend.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements UserDetailsService, IUsuarioService {

	@Autowired
	private IUsuarioRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repo.findOneByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		usuario.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		});
		UserDetails userDetails = new User(usuario.getUsername(), usuario.getPassword(), roles);
		return userDetails;
	}

	@Override
	public Usuario registrar(Usuario obj) {
		return repo.save(obj);
	}

	@Override
	public Usuario modificar(Usuario obj) {
		return repo.save(obj);
	}

	@Override
	public List<Usuario> listar() {
		return repo.findAll();
	}

	@Override
	public Boolean deleteById(int id) {
		repo.deleteById(id);
		return true;
	}

	@Override
	public Usuario leerPorId(int id) {
		Optional<Usuario> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Usuario();
	}

	

	
}
