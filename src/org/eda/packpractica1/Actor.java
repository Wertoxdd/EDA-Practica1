package org.eda.packpractica1;

public class Actor implements Comparable<Actor>{
	private String nombreCompleto;
	
	public Actor(String nombre) {
		this.nombreCompleto = nombre;
	}
	
	public String getNombreCompleto() {
		return this.nombreCompleto;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + this.nombreCompleto;
	}
	
	@Override
	public boolean equals(Object obj) { 
		if (this == obj) return true; // comprueba si son los mismos objetos
		else if (obj == null) return false; // comprueba si el parametro es null
		else if (getClass() != obj.getClass()) return false; // comprueba si ambos pertenecen a la misma clase
		else {
			Actor actor = (Actor) obj; // hace casting al parametro de tipo Object
			return this.nombreCompleto.equals(actor.nombreCompleto); // una vez hecho el casting comprueba si tienen el mismo nombre
		}
	}
	
	@Override
	public int hashCode() {
		return java.util.Objects.hash(this.nombreCompleto); // se podria usar "return this.nomnbreCompleto.hashCode()" (polimorfismo)
	}
	
}