package com.microservicesfullstack.cursos.microserviciocursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("microservicio-respuestas")
public interface RespuestaFeignClient {

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    public ResponseEntity<List<Integer>> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Integer alumnoId);
}
