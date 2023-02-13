package com.microservicesfullstack.respuestas.microserviciorespuestas.models.repository;

import com.microservicesfullstack.respuestas.microserviciorespuestas.models.entity.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {

    @Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id=?1 and e.id=?2")
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Integer alumnoId, Integer examenId);

    @Query("select e.id from Respuesta r join r.alumno a join r.pregunta p join p.examen e where a.id=?1 group by e.id")
    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Integer alumnoId);
}
