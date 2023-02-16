package com.microservicesfullstack.examenes.microservicioexamenes.controllers;

import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Examen;
import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Pregunta;
import com.microservicesfullstack.commoons.microserviciocommons.controllers.CommonController;
import com.microservicesfullstack.examenes.microservicioexamenes.services.ExamenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

    @GetMapping("/respondidos-por-preguntas")
    public ResponseEntity<List<Integer>> obtenerExamenesIdsPorPreguntasIdRespondidas(@RequestParam List<Integer> preguntaIds){
        return ResponseEntity.ok().body(service.findExamenesIdsConRespuestasByPreguntaIds(preguntaIds));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Integer id){
        if (result.hasErrors()){
            //return this.validar(result);
        }
        Optional<Examen> o = service.findById(id);
        if (!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Examen examenDb = o.get();
        examenDb.setNombre(examen.getNombre());

        List<Pregunta> eliminadas = examenDb.getPreguntas()
                .stream()
                .filter(pdb -> !examen.getPreguntas().contains(pdb))
                .collect(Collectors.toList());

        eliminadas.stream()
                .forEach(examenDb::removePregunta);

        examenDb.setPreguntas(examen.getPreguntas());
        examenDb.setAsignaturaHija(examen.getAsignaturaHija());
        examenDb.setAsignaturaPadre(examen.getAsignaturaPadre());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term){
        return ResponseEntity.ok(service.findByNombre(term));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas(){
        return ResponseEntity.ok(service.findAllAsignaturas());
    }

}
