package com.microservicesfullstack.respuestas.microserviciorespuestas.models.entity;

import com.microservicesfullstack.respuestas.microserviciorespuestas.models.pojo.Alumno;
import com.microservicesfullstack.respuestas.microserviciorespuestas.models.pojo.Pregunta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "respuestas")
public class Respuesta {

    @Id
    private String id;
    private String texto;

    //@Transient
    private Alumno alumno;

    private Integer alumnoId;

    //@Transient
    private Pregunta pregunta;

    private Integer preguntaId;
}
