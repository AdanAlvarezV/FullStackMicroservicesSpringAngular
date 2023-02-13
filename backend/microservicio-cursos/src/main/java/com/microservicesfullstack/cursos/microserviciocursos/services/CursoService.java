package com.microservicesfullstack.cursos.microserviciocursos.services;

import com.microservicesfullstack.commoons.microserviciocommons.services.CommonService;
import com.microservicesfullstack.cursos.microserviciocursos.models.entity.Curso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CursoService extends CommonService<Curso> {

    public Curso findCursoByAlumnoId(Integer id);

    public ResponseEntity<List<Integer>> obtenerExamenesIdsConRespuestasAlumno(Integer alumnoId);

}
