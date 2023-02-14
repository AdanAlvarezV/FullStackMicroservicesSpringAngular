package com.microservicesfullstack.respuestas.microserviciorespuestas.models.repository;

import com.microservicesfullstack.respuestas.microserviciorespuestas.models.entity.Respuesta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RespuestaRepository extends MongoRepository<Respuesta, Integer> {

    /*
    @Query("select r from Respuesta r join fetch r.pregunta p join fetch p.examen e where r.alumnoId=?1 and e.id=?2")
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Integer alumnoId, Integer examenId);

    @Query("select e.id from Respuesta r join r.pregunta p join p.examen e where r.alumnoId=?1 group by e.id")
    public List<Integer> findExamenesIdsConRespuestasByAlumno(Integer alumnoId);
     */

    @Query("{'alumnoId':?0, 'preguntaId':{$in: ?1}}")
    public List<Respuesta> findRespuestaByAlumnoByPreguntaId(Integer alumnoId, List<Integer> preguntaIds);

    @Query("{'alumnoId':?0}")
    public List<Respuesta> findByAlumnoId(Integer alumnoId);

    @Query("{'alumnoId':?0, 'pregunta.examen.id':?1}")
    public List<Respuesta> findRespuestaByAlumnoByExamen(Integer alumnoId, Integer examenId);

    @Query(value = "{'alumnoId':?0}", fields = "{'pregunta.examen.id':1}")
    public List<Respuesta> findExamenesIdsConRespuestasByAlumno(Integer alumnoId);

}
