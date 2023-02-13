package com.microservicesfullstack.examenes.microservicioexamenes.models.repository;

import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Integer> {

    @Query("select e from Examen e where e.nombre like %?1%")
    public List<Examen> findByNombre(String term);
}
