package com.microservicesfullstack.respuestas.microserviciorespuestas.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/*
import com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity.Alumno;
import com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity.Pregunta;

 */

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EnableEurekaClient
@Table(name = "respuesta")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String texto;
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    private Alumno alumno;
    @OneToOne(fetch = FetchType.LAZY)
    private Pregunta pregunta;

     */
}
