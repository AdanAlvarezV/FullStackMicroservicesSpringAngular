package com.microservicesfullstack.usuarios.microserviciousuarios.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservicesfullstack.usuarios.microserviciousuarios.models.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

}
