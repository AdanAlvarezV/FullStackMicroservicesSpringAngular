package com.microservicesfullstack.commoons.microserviciocommons.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommonService<E> {
	
	public Iterable<E> findAll();

	public Page<E> findAll(Pageable pagable);
	
	public Optional<E> findById(Integer id);
	
	public E save(E alumno);
	
	public void deleteById(Integer id);

}
