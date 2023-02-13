package com.microservicesfullstack.usuarios.microserviciousuarios.services;

import com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity.Alumno;
import com.microservicesfullstack.commoons.microserviciocommons.services.CommonServiceImpl;
import com.microservicesfullstack.usuarios.microserviciousuarios.models.repository.AlumnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService{

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String term) {
        return repository.findByNombreOrApellido(term);
    }
}
