package com.microservicesfullstack.respuestas.microserviciorespuestas.services;

import com.microservicesfullstack.respuestas.microserviciorespuestas.models.entity.Respuesta;

import java.util.List;

public interface RespuestaService {

    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);

    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Integer alumnoId, Integer examenId);

    public List<Integer> findExamenesIdsConRespuestasByAlumno(Integer alumnoId);

    public List<Respuesta> findByAlumnoId(Integer alumnoId);

}
