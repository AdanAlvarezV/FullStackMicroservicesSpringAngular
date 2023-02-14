package com.microservicesfullstack.examenes.microservicioexamenes.services;

import com.microservicesfullstack.examenes.microservicioexamenes.models.entity.Asignatura;
import com.microservicesfullstack.examenes.microservicioexamenes.models.entity.Examen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ExamenService {

    public List<Examen> findByNombre(String term);

    public List<Asignatura> findAllAsignaturas();

    public List<Examen> findAll();

    public Page<Examen> findAll(Pageable pagable);

    public Optional<Examen> findById(Integer id);

    public Examen save(Examen entity);

    public void deleteById(Integer id);
}
