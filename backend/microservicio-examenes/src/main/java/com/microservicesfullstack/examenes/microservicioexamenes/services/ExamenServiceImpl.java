package com.microservicesfullstack.examenes.microservicioexamenes.services;

import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Asignatura;
import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Examen;
import com.microservicesfullstack.commoons.microserviciocommons.services.CommonServiceImpl;
import com.microservicesfullstack.examenes.microservicioexamenes.models.repository.AsignaturaRepository;
import com.microservicesfullstack.examenes.microservicioexamenes.models.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService{

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String term) {
        return repository.findByNombre(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asignatura> findAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integer> findExamenesIdsConRespuestasByPreguntaIds(List<Integer> preguntaIds){
        return repository.findExamenesIdsConRespuestasByPreguntaIds(preguntaIds);

    }
}
