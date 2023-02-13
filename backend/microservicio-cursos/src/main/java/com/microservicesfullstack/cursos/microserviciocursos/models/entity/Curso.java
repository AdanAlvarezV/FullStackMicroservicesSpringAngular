package com.microservicesfullstack.cursos.microserviciocursos.models.entity;

import com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity.Alumno;
import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Examen;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Examen> examenes;

    public Curso() {
        this.alumnos = new ArrayList<>();
        this.examenes = new ArrayList<>();
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

}
