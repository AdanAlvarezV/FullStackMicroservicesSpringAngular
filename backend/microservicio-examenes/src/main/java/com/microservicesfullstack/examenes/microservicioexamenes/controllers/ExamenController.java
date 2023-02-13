package com.microservicesfullstack.examenes.microservicioexamenes.controllers;

import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Examen;
import com.microservicesfullstack.commoons.microserviciocommons.controllers.CommonController;
import com.microservicesfullstack.examenes.microservicioexamenes.services.ExamenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Integer id){
        if (result.hasErrors()){
            return this.validar(result);
        }
        Optional<Examen> o = service.findById(id);
        if (!o.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Examen examenDb = o.get();
        examenDb.setNombre(examen.getNombre());

        examenDb.getPreguntas()
                .stream()
                .filter(pdb -> !examen.getPreguntas().contains(pdb))
                .forEach(examenDb::removePregunta);

        examenDb.setPreguntas(examen.getPreguntas());
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