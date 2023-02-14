package com.microservicesfullstack.examenes.microservicioexamenes.controller;

import com.microservicesfullstack.examenes.microservicioexamenes.models.entity.Examen;
import com.microservicesfullstack.examenes.microservicioexamenes.services.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class ExamenController {

    @Autowired
    protected ExamenService service;

    @GetMapping
    public ResponseEntity<List<Examen>> listar(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/pagina")//?page=0&size=3
    public ResponseEntity<?> listar(Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examen> ver(@PathVariable Integer id){
        Optional<Examen> o = service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(o.get());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Examen entity, BindingResult result){
        if (result.hasErrors()){
            return this.validar(result);
        }
        Examen entityDb = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    protected ResponseEntity<?> validar(BindingResult result){
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
