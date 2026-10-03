package org.eda.packpractica1;

import java.util.ArrayList;
import java.util.Objects;

public class Pelicula {
	private String titulo;
	private int año;
	private int id; 
	private ArrayList<Actor> actores;
	
	public Pelicula(String titulo, int año, int id) {
		this.titulo = titulo;
		this.año = año;
		this.id = id;
		this.actores = new ArrayList<Actor>();
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public int getAño(){
		return this.año;
	}
	
	public void setAño(int nuevoAño) {
		this.año = nuevoAño;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean tieneMismoId(int id){
		return this.id == id;
	}

	/*
	 * Pos: devuelve una copia de la lista de actores en la que participa, asi no es modificable el atributo
	 */
	public ArrayList<Actor> getActores(){
		return new ArrayList<Actor>(this.actores);
	}
	
	public boolean añadirActor(Actor actor) {
		if (!this.actores.contains(actor)) {
			this.actores.add(actor); 
			return true;
		}
		else return false;
	}
	
	public boolean eliminarActor(Actor actor) {
		if (this.actores.contains(actor)) {
			this.actores.remove(actor);
			return true;
		}
		return false;
	}
	
	
	/*
	 * Mismos Overrides que con la clase Actor, excepto compareTo()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		else if (obj == null || this.getClass() != obj.getClass()) return false;
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
		return "ID: " + this.id + ", nombre: " + this.titulo + ", año: " + this.año;
	}
	
	
	
}
