package com.microservicesfullstack.cursos.microserviciocursos.controllers;

import com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity.Alumno;
import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Examen;
import com.microservicesfullstack.commoons.microserviciocommons.controllers.CommonController;
import com.microservicesfullstack.cursos.microserviciocursos.models.entity.Curso;
import com.microservicesfullstack.cursos.microserviciocursos.services.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Integer id){
        if (result.hasErrors()){
            return this.validar(result);
        }
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();
        dbCurso.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{id}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Integer id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();

        alumnos.forEach(a -> {
            if (!dbCurso.getAlumnos().contains(a))
                dbCurso.addAlumno(a);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Integer id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();

        dbCurso.removeAlumno(alumno);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Integer id){
        Curso curso = service.findCursoByAlumnoId(id);

        if (curso != null){
            System.out.println("ARRAY => " + service.obtenerExamenesIdsConRespuestasAlumno(id));
            List<Integer> examenesIds = service.obtenerExamenesIdsConRespuestasAlumno(id).getBody();
            List<Examen> examenes = curso.getExamenes().stream()
                    .map(examen -> {
                        if (examenesIds.contains(examen.getId())){
                            examen.setRespondido(Boolean.TRUE);
                        }
                        return examen;
                    })
                    .collect(Collectors.toList());
            curso.setExamenes(examenes);
        }
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}/asignar-examenes")
    public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Integer id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();

        examenes.forEach(e -> {
            if (!dbCurso.getExamenes().contains(e))
                dbCurso.addExamen(e);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Integer id){
        Optional<Curso> o = this.service.findById(id);
        if(!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso dbCurso = o.get();

        dbCurso.removeExamen(examen);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

}
