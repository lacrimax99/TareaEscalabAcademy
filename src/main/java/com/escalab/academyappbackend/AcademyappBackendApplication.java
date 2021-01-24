package com.escalab.academyappbackend;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.escalab.academyappbackend.model.Alumno;
import com.escalab.academyappbackend.model.Curso;
import com.escalab.academyappbackend.model.Matricula;
import com.escalab.academyappbackend.model.Profesor;
import com.escalab.academyappbackend.repo.IAlumnoRepo;
import com.escalab.academyappbackend.repo.ICursoRepo;
import com.escalab.academyappbackend.repo.IMatriculaRepo;
import com.escalab.academyappbackend.repo.IProfesorRepo;

@SpringBootApplication
public class AcademyappBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademyappBackendApplication.class, args);
	}
	
	@Bean
	public ApplicationRunner applicationRunner(IAlumnoRepo alumnoRepo, IProfesorRepo profesorRepo, ICursoRepo cursoRepo, IMatriculaRepo matriculaRepo) {
		return args -> {
			prepare(alumnoRepo, profesorRepo, cursoRepo, matriculaRepo);
		};
	}
	
	private void prepare(IAlumnoRepo alumnoRepo, IProfesorRepo profesorRepo, ICursoRepo cursoRepo, IMatriculaRepo matriculaRepo) {
		matriculaRepo.deleteAll();
		cursoRepo.deleteAll();
		alumnoRepo.deleteAll();
		profesorRepo.deleteAll();
		
		Alumno alumno = new Alumno();
		alumno.setNombres("Fredy Alex");
		alumno.setApellidos("Bedoya");
		alumno.setMail("bedoyafredyalex@gmail.com");
		alumnoRepo.save(alumno);
		
		Profesor prof = new Profesor();
		prof.setNombres("Josse");
		prof.setApellidos("Niño");
		prof.setDireccion("Calle 10");
		prof.setEmail("josse@escalab.com");
		prof.setTelefono("12312344");
		profesorRepo.save(prof);
		
		Curso curso = new Curso();
		curso.setNombre("Spring Boot");
		curso.setProfesor(prof);
		cursoRepo.save(curso);
		
		Matricula matricula = new Matricula();
		matricula.setCurso(curso);
		matricula.setAlumno(alumno);
		matriculaRepo.save(matricula);
	}
}
