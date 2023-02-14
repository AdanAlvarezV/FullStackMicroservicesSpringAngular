package com.microservicesfullstack.cursos.microserviciocursos.services;

import com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity.Alumno;
import com.microservicesfullstack.commoons.microserviciocommons.services.CommonServiceImpl;
import com.microservicesfullstack.cursos.microserviciocursos.clients.AlumnoFeignClient;
import com.microservicesfullstack.cursos.microserviciocursos.clients.RespuestaFeignClient;
import com.microservicesfullstack.cursos.microserviciocursos.models.entity.Curso;
import com.microservicesfullstack.cursos.microserviciocursos.models.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService{

    @Autowired
    private RespuestaFeignClient client;

    @Autowired
    private AlumnoFeignClient clientAlumno;

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Integer id) {
        return repository.findCursoByAlumnoId(id);
    }

    @Override
    public ResponseEntity<List<Integer>> obtenerExamenesIdsConRespuestasAlumno(Integer alumnoId){
        return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
    }

    @Override
    public List<Alumno> obtenerAlumnosPorCurso(@RequestParam List<Integer> ids){
        return clientAlumno.obtenerAlumnosPorCurso(ids);
    }

    @Override
    @Transactional
    public void eliminarCursoAlumnoPorId(Integer id){
        repository.eliminarCursoAlumnoPorId(id);
    }
}
