package com.microservicesfullstack.respuestas.microserviciorespuestas.controller;

import com.microservicesfullstack.respuestas.microserviciorespuestas.models.entity.Respuesta;
import com.microservicesfullstack.respuestas.microserviciorespuestas.services.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RespuestaController {

    @Autowired
    private RespuestaService service;

    @PostMapping
    //public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas){
    public ResponseEntity<?> crear(@RequestBody List<Respuesta> respuestas){
        respuestas = respuestas.stream()
                .map(r -> {
                    r.setAlumnoId(r.getAlumno().getId());
                    r.setPreguntaId(r.getPregunta().getId());
                    return r;
                }).collect(Collectors.toList());
        Iterable<Respuesta> respuestaDb = service.saveAll(respuestas);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuestaDb);
    }

    @GetMapping("/alumno/{alumnoId}/examen/{examenId}")
    public ResponseEntity<?> obtenerRespuestasPorAlumnoPorExamen(@PathVariable Integer alumnoId, @PathVariable Integer examenId){
        Iterable<Respuesta> respuestas = service.findRespuestaByAlumnoByExamen(alumnoId, examenId);
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    public ResponseEntity<List<Integer>> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Integer alumnoId){
        List<Integer> examenesIds = service.findExamenesIdsConRespuestasByAlumno(alumnoId);
        return ResponseEntity.ok(examenesIds);
    }
}
