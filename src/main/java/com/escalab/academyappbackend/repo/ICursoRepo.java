package com.escalab.academyappbackend.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.academyappbackend.model.Curso;

public interface ICursoRepo extends JpaRepository<Curso, Integer> {

}
