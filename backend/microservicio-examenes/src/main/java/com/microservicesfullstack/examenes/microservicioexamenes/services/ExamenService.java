package com.microservicesfullstack.examenes.microservicioexamenes.services;

import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Asignatura;
import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Examen;
import com.microservicesfullstack.commoons.microserviciocommons.services.CommonService;

import java.util.List;

public interface ExamenService extends CommonService<Examen> {
    public List<Examen> findByNombre(String term);
    public Iterable<Asignatura> findAllAsignaturas();
}
