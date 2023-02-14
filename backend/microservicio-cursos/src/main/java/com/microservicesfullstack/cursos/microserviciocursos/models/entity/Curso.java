package com.microservicesfullstack.cursos.microserviciocursos.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity.Alumno;
import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Examen;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String nombre;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @JsonIgnoreProperties(value = {"curso"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CursoAlumno> cursoAlumnos;


    //@OneToMany(fetch = FetchType.LAZY)
    @Transient
    private List<Alumno> alumnos;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Examen> examenes;

    public Curso() {
        this.alumnos = new ArrayList<>();
        this.examenes = new ArrayList<>();
        this.cursoAlumnos = new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public void addAlumno(Alumno alumno){
        this.alumnos.add(alumno);
    }

    public void removeAlumno(Alumno alumno){
        this.alumnos.remove(alumno);
    }

    public void addExamen(Examen examen){
        this.examenes.add(examen);
    }

    public void removeExamen(Examen examen){
        this.examenes.remove(examen);
    }

    public void addCursoAlumno(CursoAlumno cursoAlumno){
        this.cursoAlumnos.add(cursoAlumno);
    }

    public void removecursoAlumno(CursoAlumno cursoAlumno){
        this.cursoAlumnos.remove(cursoAlumno);
    }
}
