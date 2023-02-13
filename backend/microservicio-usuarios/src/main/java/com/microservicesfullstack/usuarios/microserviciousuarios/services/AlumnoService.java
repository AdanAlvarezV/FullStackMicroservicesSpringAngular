package com.microservicesfullstack.usuarios.microserviciousuarios.services;

import java.util.Optional;

import com.microservicesfullstack.usuarios.microserviciousuarios.models.entity.Alumno;

public interface AlumnoService {
	
	public Iterable<Alumno> findAll();
	
	public Optional<Alumno> findById(Integer id);
	
	public Alumno save(Alumno alumno);
	
	public void deleteById(Integer id);

}
