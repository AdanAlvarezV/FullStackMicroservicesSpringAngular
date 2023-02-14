package com.microservicesfullstack.respuestas.microserviciorespuestas.models.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

//@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pregunta {

    private Integer id;

    private String texto;

    private Examen examen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pregunta pregunta = (Pregunta) o;
        return Objects.equals(id, pregunta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
