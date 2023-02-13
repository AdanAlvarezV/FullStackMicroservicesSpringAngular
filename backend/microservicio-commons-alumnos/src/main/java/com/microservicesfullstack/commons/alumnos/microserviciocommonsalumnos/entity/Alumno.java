package com.microservicesfullstack.commons.alumnos.microserviciocommonsalumnos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alumnos")
public class Alumno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;

	@NotEmpty
	@Email
	private String email;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@Lob
	@JsonIgnore
	private byte[] foto;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Integer getFotoHashCode(){
		return (this.foto != null) ? this.foto.hashCode():null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Alumno)) return false;
		if (o == null || getClass() != o.getClass()) return false;
		Alumno alumno = (Alumno) o;
		return this.id != null && Objects.equals(id, alumno.id) && this.id.equals(alumno.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, apellido, email, createAt);
	}
}
