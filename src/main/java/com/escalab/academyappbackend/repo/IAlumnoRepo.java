package com.escalab.academyappbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.academyappbackend.model.Alumno;

public interface IAlumnoRepo extends JpaRepository<Alumno, Integer> {

}
