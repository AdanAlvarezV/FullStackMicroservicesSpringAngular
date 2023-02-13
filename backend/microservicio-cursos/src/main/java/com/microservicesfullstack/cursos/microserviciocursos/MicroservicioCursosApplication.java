package com.microservicesfullstack.cursos.microserviciocursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity",
		     "com.microservicesfullstack.cursos.microserviciocursos.models.entity",
			 "com.microservicesfullstack.commons.examenes.microserviciocommonsexamenes.models.entity"})
public class MicroservicioCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCursosApplication.class, args);
	}

}
