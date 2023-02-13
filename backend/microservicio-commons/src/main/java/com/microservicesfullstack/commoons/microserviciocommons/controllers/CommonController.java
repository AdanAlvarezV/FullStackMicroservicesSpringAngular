package com.microservicesfullstack.usuarios.microserviciousuarios.controller;

import com.microservicesfullstack.usuarios.microserviciousuarios.models.entity.Alumno;
import com.microservicesfullstack.usuarios.microserviciousuarios.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AlumnoController {
	
	@Autowired
	private AlumnoService service;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Integer id){
		Optional<Alumno> o = service.findById(id);
		if (!o.isPresent()) {	
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o.get());
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno){
		Alumno alumnoDb = service.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Integer id){
		Optional<Alumno> o = service.findById(id);
		if (!o.isPresent()) {	
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnoDb = o.get();
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id){
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
