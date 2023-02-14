package com.microservicesfullstack.usuarios.microserviciousuarios.services;

import com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity.Alumno;
import com.microservicesfullstack.commoons.microserviciocommons.services.CommonService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AlumnoService extends CommonService<Alumno> {

    public List<Alumno> findByNombreOrApellido(String term);

    public List<Alumno> findAllById(List<Integer> ids);

    public void eliminarCusroAlumnoPorId(@PathVariable Integer id);
}
