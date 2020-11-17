package com.escalab.academyappbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.escalab.academyappbackend.model.Curso;
import com.escalab.academyappbackend.repo.CursoRepoInterface;
import com.escalab.academyappbackend.service.CursoService;

public class CursoSeviceImpl implements CursoService {

	@Autowired
	private CursoRepoInterface repoInterFace;
	
	@Override
	public List<Curso> getAll() {
		// TODO Auto-generated method stub
		return repoInterFace.findAll();
	}

	@Override
	public Curso save(Curso curso) {
		// TODO Auto-generated method stub
		return repoInterFace.save(curso);
	}

	@Override
	public Boolean deleteById(int id) {
		// TODO Auto-generated method stub
		repoInterFace.deleteById(id);
		return true;
	}
	
	@Override
	public Curso update(Curso curso) {
		// TODO Auto-generated method stub
		return repoInterFace.save(curso);
	}
	
	@Override
	public Curso findById(int id) {
		// TODO Auto-generated method stub
		Optional<Curso> curso = repoInterFace.findById(id);
		if(curso.isPresent()) {
			return curso.get();
		}else {
			return new Curso();
		}
	}
}
