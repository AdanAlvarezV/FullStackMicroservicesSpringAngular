package com.microservicesfullstack.respuestas.microserviciorespuestas.services;

import com.microservicesfullstack.respuestas.microserviciorespuestas.clients.ExamenFeignClient;
import com.microservicesfullstack.respuestas.microserviciorespuestas.models.pojo.Examen;
import com.microservicesfullstack.respuestas.microserviciorespuestas.models.pojo.Pregunta;
import com.microservicesfullstack.respuestas.microserviciorespuestas.models.entity.Respuesta;
import com.microservicesfullstack.respuestas.microserviciorespuestas.models.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService{

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private ExamenFeignClient examenClient;

    @Override
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas){
        return repository.saveAll(respuestas);
    }

    @Override
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Integer alumnoId, Integer examenId){
        Examen examen = examenClient.obtenerExamenPorId(examenId);
        List<Pregunta> preguntas = examen.getPreguntas();
        List<Integer> preguntaIds = preguntas.stream()
                .map(p -> p.getId()).collect(Collectors.toList());
        List<Respuesta> respuestas = repository.findRespuestaByAlumnoByPreguntaId(alumnoId, preguntaIds);
        respuestas = respuestas.stream()
                .map(r -> {
                    preguntas.forEach(p -> {
                        if (p.getId() == r.getPreguntaId()){
                            r.setPregunta(p);
                        }
                    });
                    return r;
                }).collect(Collectors.toList());
        return respuestas;
    }

    @Override
    public List<Integer> findExamenesIdsConRespuestasByAlumno(Integer alumnoId){
        List<Respuesta> respuestasAlumno = repository.findByAlumnoId(alumnoId);
        List<Integer> examenIds = Collections.emptyList();
        if (respuestasAlumno.size() > 0){
            List<Integer> preguntasIds = respuestasAlumno.stream()
                    .map(r -> r.getPreguntaId())
                    .collect(Collectors.toList());
            examenIds = examenClient.obtenerExamenesIdsPorPreguntasIdRespondidas(preguntasIds);
        }
        return examenIds;
    }

    @Override
    public List<Respuesta> findByAlumnoId(Integer alumnoId){
        return repository.findByAlumnoId(alumnoId);
    }


}
