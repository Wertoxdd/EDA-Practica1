package org.eda.packpractica1;

import java.util.ArrayList;
import java.util.Objects;

public class Pelicula {
	// anio = año (eclipse suele dar errores con los caracteres especiales)
	private String titulo;
	private String anio;
	private int id; 
	private ArrayList<Actor> actores;
	
	public Pelicula(String titulo, String anio, int id) {
		this.titulo = titulo;
		this.anio = anio;
		this.id = id;
		this.actores = new ArrayList<Actor>();
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getAnio(){
		return this.anio;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void anadirActor(Actor actor) {
		this.actores.add(actor);
	}
	
	public void eliminarActor(Actor actor) {
		this.actores.remove(actor);
	}
	
	
	/*
	 * Mismos Overrides que con la clase Actor, excepto con el compareTo()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		else if (obj == null) return false;
		else {
			Pelicula pelicula = (Pelicula) obj;
			return this.id == pelicula.id;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + ", nombre: " + this.titulo + ", año: " + this.anio;
	}
	
	
	
}
