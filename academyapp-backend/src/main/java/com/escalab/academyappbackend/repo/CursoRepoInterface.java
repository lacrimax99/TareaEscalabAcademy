package com.escalab.academyappbackend.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.academyappbackend.model.Curso;

public interface CursoRepoInterface extends JpaRepository<Curso, Integer> {

}
