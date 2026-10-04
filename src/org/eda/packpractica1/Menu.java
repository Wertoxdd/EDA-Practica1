package org.eda.packpractica1;

import java.util.ArrayList;

public class Menu {

	public static void main(String[] args) {
		int opcion;
		do {
			mostrarMenu();
			opcion = Lector.getLector().leerEntero("Opción: ");
			switch (opcion) {
				case 1: cargar(); break;
				case 2: buscarActor(); break;
				case 3: insertarActor(); break;
				case 4: peliculasDeActor(); break;
				case 5: actoresDePelicula(); break;
				case 6: modificarAño(); break;
				case 7: borrarActor(); break;
				case 8: guardar(); break;
				case 9: listaOrdenada(); break;
				case 0: System.out.println("Hasta pronto!"); break;
				default: System.out.println("Opción no válida.");
			}
		} while (opcion != 0);
	}

	private static void mostrarMenu() {
		System.out.println();
		System.out.println("====== GESTOR DE ACTORES Y PELÍCULAS ======");
		System.out.println(" 1. Cargar los datos desde un fichero");
		System.out.println(" 2. Buscar un actor/actriz");
		System.out.println(" 3. Insertar un nuevo actor/actriz");
		System.out.println(" 4. Ver las películas de un actor/actriz");
		System.out.println(" 5. Ver los actores/actrices de una película");
		System.out.println(" 6. Modificar el año de estreno de una película");
		System.out.println(" 7. Borrar un actor/actriz");
		System.out.println(" 8. Guardar la lista en un fichero");
		System.out.println(" 9. Lista de actores ordenada");
		System.out.println(" 0. Salir");
		System.out.println("===========================================");
	}

	// Pide un nombre y devuelve el actor elegido (o null). Si hay homónimos, se elige por posición.
	private static Actor elegirActor() {
		String nombre = Lector.getLector().leerString("Nombre y apellido del actor/actriz: ").trim();
		ArrayList<Actor> actores = ListaActores.getListaActores().obtenerActoresPorNombre(nombre);

		if (actores == null) {
			System.out.println("No existe ningún actor/actriz con ese nombre.");
			return null;
		}

		if (actores.size() == 1) return actores.get(0);

		for (int i = 0; i < actores.size(); i++) System.out.println("[" + i + "] " + actores.get(i));
		int pos = Lector.getLector().leerEntero("Hay varios con ese nombre, elige uno: ");
		if (pos < 0 || pos >= actores.size()) {
			System.out.println("Posición no válida.");
			return null;
		}

		return actores.get(pos);
	}

	// Igual que elegirActor, pero con películas.
	private static Pelicula elegirPelicula() {
		String titulo = Lector.getLector().leerString("Título de la película: ").trim();
		ArrayList<Pelicula> peliculas = ListaPeliculas.getListaPeliculas().obtenerPeliculasPorTitulo(titulo);
		if (peliculas == null) {
			System.out.println("No existe ninguna película con ese título.");
			return null;
		}

		if (peliculas.size() == 1) return peliculas.get(0);

		for (int i = 0; i < peliculas.size(); i++) System.out.println("[" + i + "] " + peliculas.get(i));
		int pos = Lector.getLector().leerEntero("Hay varias con ese título, elige una: ");
		if (pos < 0 || pos >= peliculas.size()) {
			System.out.println("Posición no válida.");
			return null;
		}

		return peliculas.get(pos);
	}

	private static void cargar() {
		String ruta = Lector.getLector().leerString("Ruta del fichero: ").trim();
		long inicio = System.nanoTime();
		boolean ok = GestorFicheros.getGestorFicheros().cargarElementosDe(ruta);
		long ms = (System.nanoTime() - inicio) / 1000000;

		if (ok) System.out.println("Datos cargados en " + ms + " ms.");
		else System.out.println("No se han podido cargar los datos.");
	}

	private static void buscarActor() {
		String nombre = Lector.getLector().leerString("Nombre y apellido del actor/actriz: ").trim();
		ArrayList<Actor> actores = ListaActores.getListaActores().obtenerActoresPorNombre(nombre);

		if (actores == null) {
			System.out.println("No existe ningún actor/actriz con ese nombre.");
			return;
		}

		for (Actor a : actores) System.out.println(a + " (" + a.getPeliculas().size() + " películas)");
	}

	private static void insertarActor() {
		String nombre = Lector.getLector().leerString("Nombre y apellido del actor/actriz: ").trim();

		if (nombre.isEmpty()) {
			System.out.println("El nombre no puede estar vacío.");
			return;
		}

		int id = Lector.getLector().leerEntero("Identificador (solo el número, sin la Q): ");

		if (ListaActores.getListaActores().añadirActor(new Actor(nombre, id))) System.out.println("Actor añadido.");
		else System.out.println("Ya existe un actor/actriz con ese nombre e identificador.");
	}

	private static void peliculasDeActor() {
		Actor actor = elegirActor();

		if (actor == null) return;
		if (actor.getPeliculas().isEmpty()) System.out.println("No tiene películas.");
		for (Pelicula p : actor.getPeliculas()) System.out.println(p);
	}

	private static void actoresDePelicula() {
		Pelicula pelicula = elegirPelicula();
		if (pelicula == null) return;
		if (pelicula.getActores().isEmpty()) System.out.println("No tiene actores.");

		for (Actor a : pelicula.getActores()) System.out.println(a);

	}

	private static void modificarAño() {
		Pelicula pelicula = elegirPelicula();
		if (pelicula == null) return;

		int año = Lector.getLector().leerEntero("Nuevo año de estreno: ");
		if (ListaPeliculas.getListaPeliculas().modificarAño(pelicula, año)) System.out.println("Actualizada: " + pelicula);
		else System.out.println("No se ha podido modificar.");
	}

	private static void borrarActor() {
		Actor actor = elegirActor();
		if (actor == null) return;

		if (ListaActores.getListaActores().eliminarActor(actor)) System.out.println("Actor/actriz borrado.");

		else System.out.println("No se ha podido borrar.");
	}

	private static void guardar() {
		String ruta = Lector.getLector().leerString("Fichero de salida: ").trim();

		if (GestorFicheros.getGestorFicheros().guardarDatosEn(ruta)) System.out.println("Datos guardados.");
		else System.out.println("No se han podido guardar los datos.");
	}

	private static void listaOrdenada() {
		long inicio = System.nanoTime();

		ArrayList<Actor>[] grupos = ListaActores.getListaActores().obtenerListaOrdenada();

		long ms = (System.nanoTime() - inicio) / 1000000;
		int mostrados = 0;

		for (int i = 0; i < grupos.length && mostrados < 100; i++) { // se muestran los primeros 100 para no saturar la consola de VSCode
			for (Actor a : grupos[i]) {
				System.out.println(a);
				mostrados++;
			}
		}
		
		System.out.println("(Lista ordenada en " + ms + " ms; se muestran los primeros)");
	}
}