package com.microservicesfullstack.respuestas.microserviciorespuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.microservicesfullstack.respuestas.microserviciorespuestas.models.entity",
		"com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity",
		"com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity"})
public class MicroservicioRespuestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioRespuestasApplication.class, args);
	}

}
