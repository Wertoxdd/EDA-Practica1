package org.eda.packpractica1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class GestorFicherosTest {

	private static final String URL = "http://www.wikidata.org/entity/Q";

	@Rule
	public TemporaryFolder carpeta = new TemporaryFolder();

	private GestorFicheros gestor = GestorFicheros.getGestorFicheros();
	private ListaActores actores = ListaActores.getListaActores();
	private ListaPeliculas peliculas = ListaPeliculas.getListaPeliculas();

	@Before
	public void limpiar() {
		actores.reset();
		peliculas.reset();
	}

	private static String linea(int idActor, String actor, int idPelicula, String titulo) {
		return URL + idActor + " ### " + actor + " ### " + URL + idPelicula + " ### " + titulo;
	}

	@Test
	public void cargarFicheroCreaActoresPeliculasYRelaciones() throws IOException {
		File fichero = new File(carpeta.getRoot(), "datos_2000.txt");

		Files.write(
			fichero.toPath(),
			Arrays.asList(
				linea(1, "Carlitos", 10, "Patatin patatan"),
				linea(2, "Pepito", 10, "Patatin patatan")
			),
			StandardCharsets.UTF_8
		);

		assertTrue(gestor.cargarElementosDe(fichero.getPath()));
		assertEquals(2, actores.tamaño());
		assertEquals(1, peliculas.tamaño());
		assertEquals(2, actores.contarRelaciones());
	}

	@Test
	public void cargarFicherosConservaElPrimerAnio() throws IOException {
		File fichero1 = new File(carpeta.getRoot(), "a_2000.txt");
		File fichero2 = new File(carpeta.getRoot(), "b_2005.txt");

		Files.write(
			fichero1.toPath(),
			Arrays.asList(linea(1, "Carlitos", 10, "Patatin patatan")),
			StandardCharsets.UTF_8
		);

		Files.write(
			fichero2.toPath(),
			Arrays.asList(linea(2, "Pepito", 10, "Patatin patatan")),
			StandardCharsets.UTF_8
		);

		File directorio = carpeta.getRoot();

		assertTrue(gestor.cargarTodosLosElementosDe(directorio.getPath()));

		assertTrue(
			peliculas.obtenerPeliculasPorTitulo("Patatin patatan")
				.get(0)
				.toString()
				.endsWith("2000")
		);
	}

	@Test
	public void guardarYVolverACargarMantieneLosDatos() throws IOException {
		gestor.crearActorYPelicula(1, "Carlitos", 10, "Patatin patatan", 2000);
		gestor.crearActorYPelicula(2, "Pepito", 10, "Patatin patatan", 2000);

		File fichero = new File(carpeta.getRoot(), "datos_2000.txt");

		assertTrue(gestor.guardarDatosEn(fichero.getPath()));

		int actoresOriginales = actores.tamaño();
		int peliculasOriginales = peliculas.tamaño();
		int relacionesOriginales = actores.contarRelaciones();

		actores.reset();
		peliculas.reset();

		assertTrue(gestor.cargarElementosDe(fichero.getPath()));

		assertEquals(actoresOriginales, actores.tamaño());
		assertEquals(peliculasOriginales, peliculas.tamaño());
		assertEquals(relacionesOriginales, actores.contarRelaciones());
	}

	@Test
	public void guardarEscribeElFormatoCorrecto() throws IOException {
		gestor.crearActorYPelicula(1, "Carlitos", 10, "Patatin patatan", 2000);

		File fichero = new File(carpeta.getRoot(), "datos_2000.txt");

		assertTrue(gestor.guardarDatosEn(fichero.getPath()));

		List<String> lineas = Files.readAllLines(
			fichero.toPath(),
			StandardCharsets.UTF_8
		);

		assertEquals(
			linea(1, "Carlitos", 10, "Patatin patatan"),
			lineas.get(0)
		);
	}
}