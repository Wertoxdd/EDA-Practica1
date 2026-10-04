package org.eda.packpractica1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ListaActoresTest {

	private ListaActores lista = ListaActores.getListaActores();

	@Before
	public void limpiar() {
		lista.reset();
		ListaPeliculas.getListaPeliculas().reset();
	}

	@Test
	public void anadirYBuscarActor() {
		Actor actor = new Actor("Pepito", 1);

		assertTrue(lista.añadirActor(actor));
		assertSame(actor, lista.obtenerActor(actor));
	}

	@Test
	public void noSeDuplicaUnActor() {
		assertTrue(lista.añadirActor(new Actor("Pepito", 1)));
		assertFalse(lista.añadirActor(new Actor("Pepito", 1)));

		assertEquals(1, lista.tamaño());
	}

	@Test
	public void permiteActoresConElMismoNombre() {
		lista.añadirActor(new Actor("Pepito", 1));
		lista.añadirActor(new Actor("Pepito", 2));

		assertEquals(2, lista.obtenerActoresPorNombre("Pepito").size());
	}

	@Test
	public void eliminarActor() {
		Actor actor = new Actor("Pepito", 1);
		Pelicula pelicula = new Pelicula("Peli", 2000, 10);

		actor.añadirPelicula(pelicula);
		pelicula.añadirActor(actor);
		lista.añadirActor(actor);

		assertTrue(lista.eliminarActor(actor));
		assertNull(lista.obtenerActoresPorNombre("Pepito"));
		assertFalse(pelicula.getActores().contains(actor));
	}

	@Test
	public void eliminarActorInexistente() {
		assertFalse(lista.eliminarActor(new Actor("Pepito", 1)));
	}

	@Test
	public void obtenerListaOrdenadaNoModificaLaOriginal() {
		lista.añadirActor(new Actor("Pepito", 1));
		lista.añadirActor(new Actor("Ana", 2));
		lista.añadirActor(new Actor("Carlos", 3));

		ArrayList<Actor> ordenada = new ArrayList<Actor>();

		for (ArrayList<Actor> grupo : lista.obtenerListaOrdenada()) {
			ordenada.addAll(grupo);
		}

		assertEquals("Ana", ordenada.get(0).getNombre());
		assertEquals("Carlos", ordenada.get(1).getNombre());
		assertEquals("Pepito", ordenada.get(2).getNombre());

		assertEquals(3, lista.tamaño());
	}
}