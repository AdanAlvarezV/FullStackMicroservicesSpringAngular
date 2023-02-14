package com.microservicesfullstack.cursos.microserviciocursos.controllers;

import com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity.Alumno;
import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Examen;
import com.microservicesfullstack.commoons.microserviciocommons.controllers.CommonController;
import com.microservicesfullstack.cursos.microserviciocursos.models.entity.Curso;
import com.microservicesfullstack.cursos.microserviciocursos.models.entity.CursoAlumno;
import com.microservicesfullstack.cursos.microserviciocursos.services.CursoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    @DeleteMapping("/eliminar-alumno/{id}")
    public ResponseEntity<?> eliminarCusroAlumnoPorId(@PathVariable Integer id){
        service.eliminarCursoAlumnoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<?> listar() {
        List<Curso> cursos = ((List<Curso>) this.service.findAll())
                .stream()
                .map(c ->{
                    c.getCursoAlumnos()
                            .forEach(ca -> {
                                Alumno alumno = new Alumno();
                                alumno.setId(ca.getAlumnoId());
                                c.addAlumno(alumno);
                            });
                    return c;
                }).collect(Collectors.toList());
        return ResponseEntity.ok(cursos);
    }

    @Override
    @GetMapping({"/{id}"})
    public ResponseEntity<?> ver(@PathVariable Integer id) {
        Optional<Curso> o = this.service.findById(id);
        if (!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso curso = o.get();
        if (!curso.getCursoAlumnos().isEmpty()){
            List<Integer> ids = curso.getCursoAlumnos().stream()
                    .map(ca -> ca.getAlumnoId())
                    .collect(Collectors.toList());
            List<Alumno> alunos = service.obtenerAlumnosPorCurso(ids);
            curso.setAlumnos(alunos);
        }
        return ResponseEntity.ok().body(curso);
    }

    @Override
    @GetMapping({"/pagina"})
    public ResponseEntity<?> listar(Pageable pageable) {
        Page<Curso> cursos = service.findAll(pageable)
                .map(curso ->{
                    curso.getCursoAlumnos()
                            .forEach(ca -> {
                                Alumno alumno = new Alumno();
                                alumno.setId(ca.getAlumnoId());
                                curso.addAlumno(alumno);
                            });
                    return curso;
                });
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/balanceador-test")
    public ResponseEntity<?> balanceadorTest() {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("balanceador",balanceadorTest);
        response.put("cursos", service.findAll());
        return ResponseEntity.ok(response);
    }

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
            CursoAlumno cursoAlumno = new CursoAlumno();
            cursoAlumno.setAlumnoId(a.getId());
            cursoAlumno.setCurso(dbCurso);
            //if (!dbCurso.getCursoAlumnos().contains(a))
                dbCurso.addCursoAlumno(cursoAlumno);
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
        CursoAlumno cursoAlumno = new CursoAlumno();
        cursoAlumno.setAlumnoId(alumno.getId());
        dbCurso.removecursoAlumno(cursoAlumno);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Integer id){
        Curso curso = service.findCursoByAlumnoId(id);

        if (curso != null){
            List<Integer> examenesIds = service.obtenerExamenesIdsConRespuestasAlumno(id).getBody();
            if (examenesIds != null && examenesIds.size() > 0) {
                List<Examen> examenes = curso.getExamenes().stream()
                        .map(examen -> {
                            if (examenesIds.contains(examen.getId())) {
                                examen.setRespondido(Boolean.TRUE);
                            }
                            return examen;
                        })
                        .collect(Collectors.toList());
                curso.setExamenes(examenes);
            }
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
