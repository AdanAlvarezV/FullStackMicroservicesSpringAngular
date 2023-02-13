package com.microservicesfullstack.respuestas.microserviciorespuestas.services;

import com.microservicesfullstack.respuestas.microserviciorespuestas.models.entity.Respuesta;
import com.microservicesfullstack.respuestas.microserviciorespuestas.models.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespuestaServiceImpl implements RespuestaService{
/*
    @Autowired
    private RespuestaRepository repository;

    @Override
    @Transactional
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas){
        return repository.saveAll(respuestas);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Integer alumnoId, Integer examenId){
        return repository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Integer> findExamenesIdsConRespuestasByAlumno(Integer alumnoId){
        return repository.findExamenesIdsConRespuestasByAlumno(alumnoId);
    }

 */
}
