package com.escalab.academyappbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.academyappbackend.model.Matricula;
import com.escalab.academyappbackend.repo.IMatriculaRepo;
import com.escalab.academyappbackend.service.IMatriculaService;

@Service
public class MatriculaServiceImpl implements IMatriculaService{

	@Autowired
	private IMatriculaRepo repo;
	
	@Override
	public List<Matricula> listar() {
		return repo.findAll();
	}

	@Override
	public Matricula registrar(Matricula obj) {
		return repo.save(obj);
	}

	@Override
	public Boolean deleteById(int id) {
		repo.deleteById(id);
		return true;
	}

	@Override
	public Matricula modificar(Matricula obj) {
		return repo.save(obj);
	}

	@Override
	public Matricula leerPorId(int id) {
		Optional<Matricula> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Matricula();
	}

}
