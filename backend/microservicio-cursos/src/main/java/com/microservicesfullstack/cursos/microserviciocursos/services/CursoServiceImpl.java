package com.microservicesfullstack.cursos.microserviciocursos.services;

import com.microservicesfullstack.commoons.microserviciocommons.services.CommonServiceImpl;
import com.microservicesfullstack.cursos.microserviciocursos.clients.RespuestaFeignClient;
import com.microservicesfullstack.cursos.microserviciocursos.models.entity.Curso;
import com.microservicesfullstack.cursos.microserviciocursos.models.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService{

    @Autowired
    private RespuestaFeignClient client;

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Integer id) {
        return repository.findCursoByAlumnoId(id);
    }

    @Override
    public ResponseEntity<List<Integer>> obtenerExamenesIdsConRespuestasAlumno(Integer alumnoId){
        return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
    }
}
