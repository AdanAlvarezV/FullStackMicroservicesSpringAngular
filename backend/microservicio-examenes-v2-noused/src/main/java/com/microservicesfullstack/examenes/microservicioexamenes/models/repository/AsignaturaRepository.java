package com.microservicesfullstack.examenes.microservicioexamenes.models.repository;

import com.microservicesfullstack.examenes.microservicioexamenes.models.entity.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
}
