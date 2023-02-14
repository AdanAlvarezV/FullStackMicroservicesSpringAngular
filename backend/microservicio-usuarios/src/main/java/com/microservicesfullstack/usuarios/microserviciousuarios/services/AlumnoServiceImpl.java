package com.microservicesfullstack.usuarios.microserviciousuarios.services;

import com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity.Alumno;
import com.microservicesfullstack.commoons.microserviciocommons.services.CommonServiceImpl;
import com.microservicesfullstack.usuarios.microserviciousuarios.client.CursoFeignClient;
import com.microservicesfullstack.usuarios.microserviciousuarios.models.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService{

    @Autowired
    private CursoFeignClient clientCurso;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String term) {
        return repository.findByNombreOrApellido(term);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAllById(List<Integer> ids){
        return repository.findAllById(ids);
    }

    @Override
    public void eliminarCusroAlumnoPorId(Integer id){
        clientCurso.eliminarCusroAlumnoPorId(id);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        super.deleteById(id);
        this.eliminarCusroAlumnoPorId(id);
    }
}
