package com.escalab.academyappbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.academyappbackend.model.Profesor;

public interface ProfesorRepoInterface extends JpaRepository<Profesor, Integer> {

}
