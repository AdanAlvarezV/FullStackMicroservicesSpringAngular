package com.microservicesfullstack.respuestas.microserviciorespuestas.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

//@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {

	private Integer id;

	private String nombre;

	private String apellido;

	private String email;
	private Date createAt;

	@JsonIgnore
	private byte[] foto;


	public Integer getFotoHashCode(){
		return (this.foto != null) ? this.foto.hashCode():null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
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
