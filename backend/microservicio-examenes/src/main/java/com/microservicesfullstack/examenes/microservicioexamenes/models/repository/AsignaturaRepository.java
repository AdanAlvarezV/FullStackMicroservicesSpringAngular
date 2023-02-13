package com.microservicesfullstack.examenes.microservicioexamenes.models.repository;

import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
}
