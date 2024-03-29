package com.microservicesfullstack.commoons.microserviciocommons.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CommonServiceImpl<E, R extends JpaRepository<E, Integer>> implements CommonService<E> {
	
	@Autowired
	protected R repository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pagable){
		return repository.findAll(pagable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public E save(E entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		repository.deleteById(id);
		
	}

}
