package com.microservicesfullstack.commoons.microserviciocommons.services;


import java.util.Optional;

public interface AlumnoService<E> {
	
	public Iterable<E> findAll();
	
	public Optional<E> findById(Integer id);
	
	public E save(E alumno);
	
	public void deleteById(Integer id);

}
