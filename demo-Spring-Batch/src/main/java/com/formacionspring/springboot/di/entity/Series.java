package com.formacionspring.springboot.di.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Series implements Serializable{

	
	private static final long serialVersionUID = 8538846547613476739L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String clasificacion;
	
	
	//@Transient
	@JsonIgnore
	private String nivel_clasificacion;
	
	private int puntuacion;
	
	private int estreno;
	
	private int temporadas;

	
	
	private Date fecha;
	
	
	public Series() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Series(Long id, String titulo, String clasificacion, String nivel_clasificacion, int puntuacion, int estreno,
			int temporadas, Date fecha) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.clasificacion = clasificacion;
		this.nivel_clasificacion = nivel_clasificacion;
		this.puntuacion = puntuacion;
		this.estreno = estreno;
		this.temporadas = temporadas;
		this.fecha = fecha;
	}





	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getClasificacion() {
		return clasificacion;
	}



	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}



	public String getNivel_clasificacion() {
		return nivel_clasificacion;
	}



	public void setNivel_clasificacion(String nivel_clasificacion) {
		this.nivel_clasificacion = nivel_clasificacion;
	}



	public int getPuntuacion() {
		return puntuacion;
	}



	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}



	public int getEstreno() {
		return estreno;
	}



	public void setEstreno(int estreno) {
		this.estreno = estreno;
	}



	public int getTemporadas() {
		return temporadas;
	}



	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}





	public Date getFecha() {
		return fecha;
	}





	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}





	@Override
	public String toString() {
		return "Series [id=" + id + ", titulo=" + titulo + ", clasificacion=" + clasificacion + ", nivel_clasificacion="
				+ nivel_clasificacion + ", puntuacion=" + puntuacion + ", estreno=" + estreno + ", temporadas="
				+ temporadas + ", fecha=" + fecha + "]";
	}



	











	
	


	
	
	
	



	
	
	
}
