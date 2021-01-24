package com.escalab.academyappbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.academyappbackend.model.Curso;
import com.escalab.academyappbackend.repo.ICursoRepo;
import com.escalab.academyappbackend.service.ICursoService;

@Service
public class CursoSeviceImpl implements ICursoService {

	@Autowired
	private ICursoRepo repoInterFace;
	
	@Override
	public List<Curso> listar() {
		return repoInterFace.findAll();
	}

	@Override
	public Curso registrar(Curso curso) {
		return repoInterFace.save(curso);
	}

	@Override
	public Boolean deleteById(int id) {
		repoInterFace.deleteById(id);
		return true;
	}
	
	@Override
	public Curso modificar(Curso curso) {
		return repoInterFace.save(curso);
	}
	
	@Override
	public Curso leerPorId(int id) {
		Optional<Curso> curso = repoInterFace.findById(id);
		return curso.isPresent() ? curso.get() : new Curso();
	}
}
