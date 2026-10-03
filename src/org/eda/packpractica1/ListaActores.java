package org.eda.packpractica1;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.PrintWriter;

// FALTA MANEJAR LOS ACTORES CON LAS PELICULAS

public class ListaActores {
	private HashMap<String, ArrayList<Actor>> mapaActores;
	private static ListaActores miLista = null;
	
	private ListaActores() {
        this.mapaActores = new HashMap<String, ArrayList<Actor>> ();
	}
	
	// Singleton
	public static ListaActores getListaActores() {
		if (miLista == null) {
			miLista = new ListaActores();
		}
		return miLista;
	}
	
	/*
	 * Post: si en el mapa existe el nombre del actor devuelve un ArrayList de los actores con ese nombre (O(1)), si no devuelve null.
	 */
	public ArrayList<Actor> obtenerActoresPorNombre(String nombre){
		return mapaActores.get(nombre);
	}
	
	
	/*
	 * Pre: actor no nulo
	 * Pos: devuelve el actor como objeto. si no existe o no tiene el mismo id devuelve null.
	 */
	public Actor obtenerActor(Actor actor) {
		ArrayList<Actor> mismoNombre = obtenerActoresPorNombre(actor.getNombre());
		if (mismoNombre == null) return null;
		for (Actor a: mismoNombre) {
			if (a.equals(actor)) return a;
		}
		return null;
	}
	
	/*
	 * Pos: devuelve si el mapa contiene el id 
	 */
	public boolean existeNombre(String nombre) {
		return mapaActores.containsKey(nombre);
	}
	
	
	/*
	 * Pos: devuelve si existe el actor o no en el mapa
	 */
	public boolean existe(Actor actor) {
		return obtenerActor(actor) != null;
	}
	
	/*
	 * Pos: si el actor que se pasa como parametro es nulo no ocurre nada. Si no, obtiene la lista de los actores con mismo nombre.
	 * Una vez obtenida la lista elimina al actor del ArrayList, y luego comprueba si el ArrayList queda vacio.
	 * Si el ArrayList queda vacio se elimina del HashMap por eficiencia.
	 */
	public boolean eliminarActor(Actor actor) {
		Actor actorTemp = obtenerActor(actor);
		if (actorTemp == null) return false;
		ArrayList<Actor> listaMismoNombre = obtenerActoresPorNombre(actorTemp.getNombre());
		listaMismoNombre.remove(actorTemp);
		if (listaMismoNombre.isEmpty()) mapaActores.remove(listaMismoNombre);
		return true;
	}
	
	/*
	 * Pos:
	 */
	public boolean añadirActor(Actor actor) {
		if (obtenerActor(actor) != null) return false;
		ArrayList<Actor> listaActoresMismoNombre = obtenerActoresPorNombre(actor.getNombre());
		if (listaActoresMismoNombre == null) {
			listaActoresMismoNombre = new ArrayList<Actor>();
			mapaActores.put(actor.getNombre(), listaActoresMismoNombre);
		}
		listaActoresMismoNombre.add(actor);
		return true;
	}
	
	public void escribirEnDirectorioCon(PrintWriter editor){
		for (ArrayList<Actor> actores: mapaActores.values()){
			for (Actor a: actores){
				editor.println(a);
				for (Pelicula p: a.getPeliculas()){
					editor.println("\t" + p);
				}
			}
		}
	}

	public ArrayList<Actor>[] obtenerListaOrdenada(){
		ArrayList<Actor>[] temp = new ArrayList [mapaActores.size()];
		ArrayList<Actor>[] valores = new ArrayList [temp.length];
		int i = 0;
		for (ArrayList<Actor> actores: mapaActores.values()) {
			valores[i] = actores;
			i++;
		}
		mergeSort(valores, temp, 0, temp.length-1);
		return valores;
	}
	
	private void mergeSort(ArrayList<Actor>[] valores, ArrayList<Actor>[] temp, int inicio, int fin) {
		if (inicio >= fin) return;
		mergeSort(valores, temp, inicio, (inicio+fin)/2);
		mergeSort(valores, temp, (inicio+fin)/2 + 1, fin);
		merge(valores, temp, inicio, (inicio+fin)/2, fin);
	}
	
	private void merge(ArrayList<Actor>[] valores, ArrayList<Actor>[] temp, int inicio, int medio, int fin) {
		int izq = inicio, der = medio + 1, i = inicio;
		while(izq <= medio && der <= fin) {
			if (valores[der].get(0).compareTo(valores[izq].get(0)) < 0) {
				temp[i] = valores[der];
				i++;
				der++;
			}
			else {
				temp[i] = valores[izq];
				i++;
				izq++;
			}
		}
		while (izq <= medio) {
			temp[i] = valores[izq];
			i++;
			izq++;
		}
		
		while (der <= fin) {
			temp[i] = valores[der];
			i++;
			der++;
		}
		
		for (i = inicio; i <= fin; i++) {
			valores[i] = temp[i];
		}
	}
	
}
