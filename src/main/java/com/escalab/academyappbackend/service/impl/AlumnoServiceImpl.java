package com.escalab.academyappbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.academyappbackend.model.Alumno;
import com.escalab.academyappbackend.repo.IAlumnoRepo;
import com.escalab.academyappbackend.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
	
	@Autowired
	private IAlumnoRepo repoInterface;

	@Override
	public List<Alumno> listar() {
		return repoInterface.findAll();
	}
	
	@Override
	public Alumno registrar(Alumno Alumno) {
		return repoInterface.save(Alumno);
	}

	@Override
	public Boolean deleteById(int id) {
		repoInterface.deleteById(id);
		return true;
	}
	
	@Override
	public Alumno modificar(Alumno Alumno) {
		return repoInterface.save(Alumno);
	}
	
	@Override
	public Alumno leerPorId(int id) {
		Optional<Alumno> alumno = repoInterface.findById(id);
		if(alumno.isPresent()) {
			return alumno.get();
		}else {
			return new Alumno();
		}
		
	}

}
