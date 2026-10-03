package org.eda.packpractica1;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaPeliculas {
	private static ListaPeliculas miLista = null;
	private HashMap<String, ArrayList<Pelicula>> mapaPeliculas;
	
	private ListaPeliculas() {
		mapaPeliculas = new HashMap<String, ArrayList<Pelicula>>();
	}
	
	public static ListaPeliculas getListaPeliculas() {
		if (miLista == null) {
			miLista = new ListaPeliculas();
		}
		return miLista;
	}
	
	public ArrayList<Pelicula> obtenerPeliculasPorTitulo(String titulo){
		return mapaPeliculas.get(titulo);
	}
	
	public boolean anadir(Pelicula pelicula) {
		if (obtenerPelicula(pelicula) != null) return false;
		ArrayList<Pelicula> mismoTitulo = obtenerPeliculasPorTitulo(pelicula.getTitulo());
		if (mismoTitulo == null) {
			mismoTitulo = new ArrayList<Pelicula>();
			mapaPeliculas.put(pelicula.getTitulo(), mismoTitulo);
		}
		mismoTitulo.add(pelicula);
		return true;
	}
	
	public boolean modificarAnio(Pelicula pelicula, int nuevoAnio) {
		Pelicula almacenada = obtenerPelicula(pelicula);
		if (almacenada == null) return false;
		almacenada.setAnio(nuevoAnio);
		return true;
	}
	
	public Pelicula eliminarPelicula(Pelicula pelicula) {
		Pelicula almacenada = obtenerPelicula(pelicula);
		if (almacenada == null) return null;
		ArrayList<Pelicula> mismoTitulo = obtenerPeliculasPorTitulo(almacenada.getTitulo());
		mismoTitulo.remove(almacenada);
		if (mismoTitulo.isEmpty()) mapaPeliculas.remove(almacenada.getTitulo());
		return almacenada;
	}
	
	public int tamanio() {
		int total = 0;
		for (ArrayList<Pelicula> p: mapaPeliculas.values()) {
			total += p.size();
		} 
		return total;
	}
	
	public Pelicula obtenerPelicula(Pelicula pelicula) {
		ArrayList<Pelicula> mismoTitulo = obtenerPeliculasPorTitulo(pelicula.getTitulo());
		if (mismoTitulo == null) return null;
		for (Pelicula p: mismoTitulo) {
			if (p.equals(pelicula)) return p;
		}
		return null;
	}
	
}
