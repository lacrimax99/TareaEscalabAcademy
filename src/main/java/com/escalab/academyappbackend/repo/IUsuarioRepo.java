package com.escalab.academyappbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.academyappbackend.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {

	//select * from usuario where username = ?
	Usuario findOneByUsername(String username);
}
