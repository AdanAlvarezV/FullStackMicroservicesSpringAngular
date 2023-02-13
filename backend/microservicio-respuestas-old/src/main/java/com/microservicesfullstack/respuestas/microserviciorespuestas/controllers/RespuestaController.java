package com.microservicesfullstack.respuestas.microserviciorespuestas.controllers;

import com.microservicesfullstack.respuestas.microserviciorespuestas.models.entity.Respuesta;
import com.microservicesfullstack.respuestas.microserviciorespuestas.services.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RespuestaController {

    @Autowired
    private RespuestaService service;
/*
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas){
        Iterable<Respuesta> respuestaDb = service.saveAll(respuestas);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuestaDb);
    }

    @GetMapping("/alumno/{alimnoId}/examen/{examenId}")
    public ResponseEntity<?> obtenerRespuestasPorAlumnoPorExamen(@PathVariable Integer alumnoId, @PathVariable Integer examenId){
        Iterable<Respuesta> respuestas = service.findRespuestaByAlumnoByExamen(alumnoId, examenId);
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    public ResponseEntity<?> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Integer alumnoId){
        Iterable<Integer> examenesIds = service.findExamenesIdsConRespuestasByAlumno(alumnoId);
        return ResponseEntity.ok(examenesIds);
    }

 */
}
