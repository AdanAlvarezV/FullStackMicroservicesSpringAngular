package com.microservicesfullstack.cursos.microserviciocursos.models.repository;

import com.microservicesfullstack.cursos.microserviciocursos.models.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

    @Query("select c from Curso c join fetch c.alumnos a where a.id=?1")
    public Curso findCursoByAlumnoId(Integer id);
}
