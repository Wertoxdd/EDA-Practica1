package org.eda.packpractica1;

public class Pelicula {
	private String nombre;
	private int año;
	
	public Pelicula(String nombre, int año) {
		this.nombre = nombre;
		this.año = año;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getAño() {
		return this.año;
	}
	
}
