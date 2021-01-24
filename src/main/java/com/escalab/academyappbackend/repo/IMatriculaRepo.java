package com.escalab.academyappbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.academyappbackend.model.Matricula;

public interface IMatriculaRepo extends JpaRepository<Matricula, Integer> {

}
