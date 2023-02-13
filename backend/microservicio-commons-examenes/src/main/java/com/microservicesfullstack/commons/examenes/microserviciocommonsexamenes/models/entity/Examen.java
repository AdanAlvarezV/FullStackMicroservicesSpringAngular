package com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@Entity
@Table(name = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(min = 4, max = 30)
    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createAt;

    @JsonIgnoreProperties(value = {"examen"}, allowSetters = true)
    @OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Asignatura asignatura;

    public Examen() {
        this.preguntas = new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public void addPregunta(Pregunta pregunta){
        this.preguntas.add(pregunta);
        pregunta.setExamen(this);
    }

    public void removePregunta(Pregunta pregunta){
        this.preguntas.remove(pregunta);
        pregunta.setExamen(null);
    }

    public void setPreguntas(List<Pregunta> preguntas){
        this.preguntas.clear();
        preguntas.forEach(this::addPregunta);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Examen examen = (Examen) o;
        return Objects.equals(id, examen.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
