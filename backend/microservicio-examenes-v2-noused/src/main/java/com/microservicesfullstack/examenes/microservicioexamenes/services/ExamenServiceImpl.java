package com.microservicesfullstack.examenes.microservicioexamenes.services;

import com.microservicesfullstack.examenes.microservicioexamenes.models.entity.Asignatura;
import com.microservicesfullstack.examenes.microservicioexamenes.models.entity.Examen;
import com.microservicesfullstack.examenes.microservicioexamenes.models.repository.AsignaturaRepository;
import com.microservicesfullstack.examenes.microservicioexamenes.models.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String term) {
        return examenRepository.findByNombre(term);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> findAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    @Override
    public List<Examen> findAll() {
        return examenRepository.findAll();
    }

    @Override
    public Page<Examen> findAll(Pageable pagable) {
        return examenRepository.findAll(pagable);
    }

    @Override
    public Optional<Examen> findById(Integer id) {
        return examenRepository.findById(id);
    }

    @Override
    public Examen save(Examen entity) {
        return examenRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        examenRepository.deleteById(id);
    }
}
