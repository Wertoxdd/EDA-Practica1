package org.eda.packpractica1;

import java.util.Objects;
import java.util.ArrayList;

public class Actor {
	private String nombre;
	private String apellido;
	private int id;
	private ArrayList<Pelicula> peliculas;
	
	public Actor(String nombre, String apellido, int id) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
		this.peliculas = new ArrayList<Pelicula>();
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		
	}
	
	@Override
	public String toString() {
		return "Nombre: " + this.nombre + " - Apellido: " + this.apellido + " - ID: " + this.id;
	}
	
	@Override
	public boolean equals(Object obj) { 
		if (this == obj) return true; // comprueba si son los mismos objetos
		else if (obj == null) return false; // comprueba si el parametro es null
		else if (getClass() != obj.getClass()) return false; // comprueba si ambos pertenecen a la misma clase
		else {
			Actor actor = (Actor) obj; // hace casting al parametro de tipo Object
			return this.id==actor.id; // una vez hecho el casting comprueba si tienen el mismo nombre
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}
	
}