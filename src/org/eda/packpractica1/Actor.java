package org.eda.packpractica1;

import java.util.Objects;
import java.util.ArrayList;

public class Actor implements Comparable<Actor>{
	private String nombre;
	private int id;
	private ArrayList<Pelicula> peliculas;
	
	public Actor(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
		this.peliculas = new ArrayList<Pelicula>();
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public ArrayList<Pelicula> getPeliculas(){
		return this.peliculas;
	}
	
	public int getId() {
		return this.id;
	}

	public boolean tieneMismoId(int id){
		return this.id == id;
	}	

	public void eliminarPelicula(Pelicula pelicula) {
		this.peliculas.remove(pelicula);
	}
	
	public boolean participaEn(Pelicula pelicula) {
		return this.peliculas.contains(pelicula);
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + ", nombre: " + this.nombre;
	}
	
	@Override
	public boolean equals(Object obj) { 
		if (this == obj) return true; // comprueba si son los mismos objetos
		else if (obj == null || this.getClass() != obj.getClass()) return false; // comprueba si el parametro es null o si son diferentes clases. 
		else {
			Actor actor = (Actor) obj; // hace casting al parametro de tipo Object
			return this.id==actor.id; // una vez hecho el casting comprueba si tienen el mismo id
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}
	
	public int compareTo(Actor actor) {
		return this.nombre.compareTo(actor.getNombre());
	}
	
}