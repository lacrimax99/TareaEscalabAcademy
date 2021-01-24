package com.escalab.academyappbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.academyappbackend.model.Profesor;

public interface IProfesorRepo extends JpaRepository<Profesor, Integer> {

}
