package org.eda.packpractica1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ListaPeliculasTest {

	private ListaPeliculas lista = ListaPeliculas.getListaPeliculas();

	@Before
	public void limpiar() {
		lista.reset();
	}

	@Test
	public void anadirYBuscarPelicula() {
		Pelicula pelicula = new Pelicula("Peli", 2000, 10);

		assertTrue(lista.añadir(pelicula));
		assertSame(pelicula, lista.obtenerPelicula(pelicula));
	}

	@Test
	public void noSeDuplicaUnaPelicula() {
		assertTrue(lista.añadir(new Pelicula("Peli", 2000, 10)));
		assertFalse(lista.añadir(new Pelicula("Peli", 2000, 10)));

		assertEquals(1, lista.tamaño());
	}

	@Test
	public void permitePeliculasConElMismoTitulo() {
		lista.añadir(new Pelicula("Peli", 2000, 10));
		lista.añadir(new Pelicula("Peli", 2005, 11));

		assertEquals(2, lista.obtenerPeliculasPorTitulo("Peli").size());
	}

	@Test
	public void modificarAnioDePelicula() {
		Pelicula pelicula = new Pelicula("Peli", 2000, 10);
		lista.añadir(pelicula);

		assertTrue(lista.modificarAño(pelicula, 2015));
		assertTrue(pelicula.toString().endsWith("2015"));
	}
}