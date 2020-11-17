package com.escalab.academyappbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.escalab.academyappbackend.model.Profesor;
import com.escalab.academyappbackend.repo.ProfesorRepoInterface;
import com.escalab.academyappbackend.service.ProfesorService;

public class ProfesorServiceImpl implements ProfesorService{
	
	@Autowired
	private ProfesorRepoInterface repoInterface;

	@Override
	public List<Profesor> getAll() {
		// TODO Auto-generated method stub
		return repoInterface.findAll();
	}
	
	@Override
	public Profesor save(Profesor profesor) {
		// TODO Auto-generated method stub
		return repoInterface.save(profesor);
	}

	@Override
	public Boolean deleteById(int id) {
		// TODO Auto-generated method stub
		repoInterface.deleteById(id);
		return true;
	}
	
	@Override
	public Profesor update(Profesor profesor) {
		// TODO Auto-generated method stub
		return repoInterface.save(profesor);
	}
	
	@Override
	public Profesor findById(int id) {
		// TODO Auto-generated method stub
		Optional<Profesor> profesor = repoInterface.findById(id);
		if(profesor.isPresent()) {
			return profesor.get();
		}else {
			return new Profesor();
		}
		
	}

}
