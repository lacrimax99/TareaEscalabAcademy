package com.escalab.academyappbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.academyappbackend.model.Profesor;
import com.escalab.academyappbackend.repo.IProfesorRepo;
import com.escalab.academyappbackend.service.IProfesorService;

@Service
public class ProfesorServiceImpl implements IProfesorService{
	
	@Autowired
	private IProfesorRepo repoInterface;

	@Override
	public List<Profesor> listar() {
		return repoInterface.findAll();
	}
	
	@Override
	public Profesor registrar(Profesor profesor) {
		return repoInterface.save(profesor);
	}

	@Override
	public Boolean deleteById(int id) {
		repoInterface.deleteById(id);
		return true;
	}
	
	@Override
	public Profesor modificar(Profesor profesor) {
		return repoInterface.save(profesor);
	}
	
	@Override
	public Profesor leerPorId(int id) {
		// TODO Auto-generated method stub
		Optional<Profesor> profesor = repoInterface.findById(id);
		if(profesor.isPresent()) {
			return profesor.get();
		}else {
			return new Profesor();
		}
		
	}

}
