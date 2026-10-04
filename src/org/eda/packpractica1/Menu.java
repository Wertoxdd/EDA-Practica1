package org.eda.packpractica1;

import java.util.Scanner;
import java.util.ArrayList;

// TODO separar los casos en metodos para limpiar codigo y añadir mas cositas

public class Menu {
	private static Menu instanciaMenu = null;
	private enum Opciones {CARGAR_FICHERO, ENCONTRAR_ACTOR, AÑADIR_ACTOR, AÑADIR_PELICULA, OBTENER_PELICULAS_DE_ACTOR, OBTENER_ACTORES_DE_PELICULA, 
		CAMBIAR_AÑO_DE_PELICULA, ELIMINAR_ACTOR, GUARDAR_EN_FICHERO, ORDENAR
	}
	private Opciones opcionSeleccionada;


	private static final String RESET = "\033[0m";
	private static final String NEGRITA = "\033[1m";
	private static final String CYAN = "\033[36m";
	private static final String VERDE = "\033[32m";
	private static final String AMARILLO = "\033[33m";
	private static final String ROJO = "\033[31m";


	public static Menu getMenu() {
		if(instanciaMenu == null) {
			instanciaMenu = new Menu();
		}
		return instanciaMenu;
	}
	
	public static void cargandoFicheros() throws InterruptedException {
		//TODO barra de carga y menu interactivo
		mensajeCargando("Leyendo archivos", 4);
		
	}
	
	public static void mensajeCargando(String mensaje, int numVueltasTotal) {
		String[] bucleCargando = {"   ",".  ",".. ","..."};
		for (int numVueltas = 0; numVueltas < numVueltasTotal; numVueltas++) {
			for(int vuelta = 0; vuelta < 4; vuelta++) {
				System.out.print("\r" + mensaje + bucleCargando[vuelta]);
				System.out.flush();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {}
			}
		}
		System.out.println();
	}
	
	public void pausa() {
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		mensajeCargando("Pulsa Enter para continuar", 3);
		sc.close();
	}
	
	public static void main(String[] args){
		
		while(true){
			System.out.println(CYAN + "╔════════════════════════════════════════════════════════════════╗" + RESET);
			System.out.println(CYAN + "║                 " + NEGRITA + AMARILLO + "GESTOR DE ACTORES Y PELÍCULAS" + RESET + CYAN + "                  ║" + RESET);
			System.out.println(CYAN + "╠════════════════════════════════════════════════════════════════╣" + RESET);
			System.out.println(CYAN + "╟─ CARGA ────────────────────────────────────────────────────────╢" + RESET);
			System.out.println(CYAN + "║" + RESET + "   " + VERDE + "[1]" + RESET + "  Cargar los datos desde los ficheros                     " + CYAN + "║" + RESET);
			System.out.println(CYAN + "╟─ ACTORES Y PELÍCULAS ──────────────────────────────────────────╢" + RESET);
			System.out.println(CYAN + "║" + RESET + "   " + VERDE + "[2]" + RESET + "  Buscar un actor/actriz                                  " + CYAN + "║" + RESET);
			System.out.println(CYAN + "║" + RESET + "   " + VERDE + "[3]" + RESET + "  Insertar un nuevo actor/actriz                          " + CYAN + "║" + RESET);
			System.out.println(CYAN + "║" + RESET + "   " + VERDE + "[4]" + RESET + "  Ver las películas de un actor/actriz                    " + CYAN + "║" + RESET);
			System.out.println(CYAN + "║" + RESET + "   " + VERDE + "[5]" + RESET + "  Ver los actores/actrices de una película                " + CYAN + "║" + RESET);
			System.out.println(CYAN + "╟─ MODIFICACIONES ───────────────────────────────────────────────╢" + RESET);
			System.out.println(CYAN + "║" + RESET + "   " + VERDE + "[6]" + RESET + "  Modificar el año de estreno de una película             " + CYAN + "║" + RESET);
			System.out.println(CYAN + "║" + RESET + "   " + VERDE + "[7]" + RESET + "  Borrar un actor/actriz                                  " + CYAN + "║" + RESET);
			System.out.println(CYAN + "╟─ FICHEROS Y LISTADOS ──────────────────────────────────────────╢" + RESET);
			System.out.println(CYAN + "║" + RESET + "   " + VERDE + "[8]" + RESET + "  Guardar la lista en un fichero                          " + CYAN + "║" + RESET);
			System.out.println(CYAN + "║" + RESET + "   " + VERDE + "[9]" + RESET + "  Lista de actores ordenada (sin modificar la original)   " + CYAN + "║" + RESET);
			System.out.println(CYAN + "╟────────────────────────────────────────────────────────────────╢" + RESET);
			System.out.println(CYAN + "║" + RESET + "   " + ROJO + "[0]" + RESET + "  Salir                                                   " + CYAN + "║" + RESET);
			System.out.println(CYAN + "╚════════════════════════════════════════════════════════════════╝" + RESET);

			mensajeCargando("Elige una opción", 0);
			int opcion = Lector.getLector().leerEntero();

			if (opcion < 0 || opcion > 9) return;
			
			instanciaMenu.opciones(opcion);
			
		}
	}

	private void opciones(int opcion){
		opcionSeleccionada = Opciones.values()[opcion];

		switch(opcionSeleccionada){
			case CARGAR_FICHERO:
				try{
					System.out.println("============| CARGAR DATOS DE FICHERO |============");
					if (GestorFicheros.getGestorFicheros().cargarElementosDe(Lector.getLector().leerString("Introduce el origen de los datos: "))) {
						cargandoFicheros();
						System.out.println("Operacion realizada con éxito.");
					}
					else{
						cargandoFicheros();
						System.out.println("No se han podido cargar los datos.");
					}
					break;
				}
				catch (Exception e) {System.out.println("Error: " + e );}
			
			case ENCONTRAR_ACTOR:
				System.out.println("============| ENCONTRAR ACTOR/ACTRIZ |============");
				ArrayList<Actor> actores = ListaActores.getListaActores().obtenerActoresPorNombre(Lector.getLector().leerString("Introduce el nombre y apellido del actor/actriz que quieras buscar: "));
				if (actores != null)
					for (int i = 0; i < actores.size(); i++) System.out.println("[" + i + "]" + actores.get(i));
				else System.out.println("No se ha podido encontrar el actor/actríz.");
				break;
			
			case AÑADIR_ACTOR:
				System.out.println("============| AÑADIR ACTOR/ACTRIZ |============");
				
				String nombre = Lector.getLector().leerString("Introduce el nombre y apellido del actor/actriz que quieres añadir: ");
				int id = Lector.getLector().leerEntero("Introduce el identificador (solo el número, sin la Q): ");
				Actor actor = new Actor(nombre, id);
				ListaActores.getListaActores().añadirActor(actor);

				String[] carreraActor = Lector.getLector().leerString("Introduce los titulos de las peliculas en las que participa el actor/actriz (separalos con tres guiones sin espacio: '---' ): ").split("----");

				for (String titulo: carreraActor){
					if (titulo.length() == 0) continue;

					ArrayList<Pelicula> peliculas = ListaPeliculas.getListaPeliculas().obtenerPeliculasPorTitulo(titulo);
					int i = 0;
					if (peliculas.size() > 1){
						i = Lector.getLector().leerEntero("Ya existe una pelicula llamada '" + titulo + "'. Introduce la posicion de la pelicula correcta: (0-" + (peliculas.size()-1) + "): ");
					}
					actor.añadirPelicula(peliculas.get(i));
					peliculas.get(i).añadirActor(actor);
				}

				break;
			
			case AÑADIR_PELICULA:
				System.out.println("============| AÑADIR PELICULA |============");

				String titulo = Lector.getLector().leerString("Introduce el titulo de la pelicula: ");
				//TODO terminar los casos y pulir codigo, hacer los JUnits y comprobar todo. 
				int año = Lector.getLector().leerEntero("Introduce el año que se estreno la pelicula: ");
				int id2 = Lector.getLector().leerEntero("Introduce el id de la pelicula (solo el numero, sin la Q: ");
				Pelicula pelicula2 = new Pelicula(titulo, año, id2);
				ListaPeliculas.getListaPeliculas().añadir(pelicula2);

				String[] reparto = Lector.getLector().leerString("Introduce el conjunto de actores que participan en la pelicula (separalos con tres guiones sin espacio: '---'): ").split("---");

				for (String participante: reparto){
					if (participante.length() == 0) continue;

					ArrayList<Actor> participantes = ListaActores.getListaActores().obtenerActoresPorNombre(participante);
					int j = 0;

					if (participantes.size() > 1){
						j = Lector.getLector().leerEntero("Ya existe un actor llamado '" + participante + ". Introduce la posición del actor correcto: (0-" + (participantes.size()-1 )+ "): ");
					}
					pelicula2.añadirActor(participantes.get(j));
					participantes.get(j).añadirPelicula(pelicula2);
				}

				break;

			case OBTENER_ACTORES_DE_PELICULA:
				System.out.println("============| OBTENER PELICULAS DE UN ACTOR/ACTRIZ |============");
				String  actor3 = Lector.getLector().leerString("Introduce el nombre del actor/actriz para saber las peliculas en las que ha estado: ");
				ArrayList<Actor> actores3 = ListaActores.getListaActores().obtenerActoresPorNombre(actor3);
				
				if (actores3 == null){
					System.out.println("Error: no se ha podido encontrar un actor con ese nombre.");
					break;
				}

				for (int i = 0; i < actores3.size(); i++){
					System.out.println("[" + i + "]");
					ArrayList<Pelicula> peliculas3 = actores3.get(i).getPeliculas();
					for (Pelicula p: peliculas3) System.out.println(p);
				}

				break;

			case OBTENER_PELICULAS_DE_ACTOR:
				System.out.println("============| OBTENER ACTORES DE UNA PELICULA |============");
				String titulo2 = Lector.getLector().leerString("Introduce el titulo de la pelicula para saber los actores que han participado: ");
				ArrayList<Pelicula> peliculas4 = ListaPeliculas.getListaPeliculas().obtenerPeliculasPorTitulo(titulo2);

				if (peliculas4 == null) {
					System.out.println("Error: No se ha podido encontrar ninguna pelicula con ese titulo.");
					break;
				}

				for (int i = 0; i < peliculas4.size(); i++){
					System.out.println("[" + i + "]");
					ArrayList<Actor> actores4 = peliculas4.get(i).getActores();
					for (Actor a: actores4) System.out.println(a);
				}
				
				break;

			case CAMBIAR_AÑO_DE_PELICULA:
				System.out.println("============| CAMBIAR AÑO DE LA PELICULA |============");
				String titulo3 = Lector.getLector().leerString("Introduce el titulo de la pelicula que desea modificar: ");
				ArrayList<Pelicula> peliculas5 = ListaPeliculas.getListaPeliculas().obtenerPeliculasPorTitulo(titulo3);

				if (peliculas5 == null){
					System.out.println("Error: No se ha podido encontrar ninguna pelicual con ese titulo. ");
					break;
				}

				if (peliculas5.size() == 1){
					int año2 = Lector.getLector().leerEntero("Introduce el año con el que vas a modificar la pelicula: ");
					peliculas5.get(0).setAño(año2);
				} 
				
				else {
					for (int i = 0; i < peliculas5.size(); i++) System.out.println("[" + i + "]" + peliculas5.get(i));
					int seleccion = Lector.getLector().leerEntero("Existen más de una pelicula con el mismo titulo, introduce la posicion cual quieres elegir: ");
					Pelicula pelicula5 = peliculas5.get(seleccion);
					int año3 = Lector.getLector().leerEntero("Introduce el año con el que vas a modificar la pelicula: ");
					pelicula5.setAño(año3);
				}

				break;
			
			case ELIMINAR_ACTOR:
				System.out.println("============| ELIMINAR ACTOR/ACTRIZ DE LA PELICULA |============");
				String nombre6 = Lector.getLector().leerString("Introduce el titulo del actor/actriz que desea eliminar: ");
				ArrayList<Actor> actores6 = ListaActores.getListaActores().obtenerActoresPorNombre(nombre6);

				if (actores6 == null){
					System.out.println("Error: No se ha podido encontrar el actor/actríz que estas buscando.");
					break;
				}

				if (actores6.size() == 1){
					if (ListaActores.getListaActores().eliminarActor(actores6.get(0))) System.out.println("El actor " + actores6.toString() + " se ha eliminado correctamente.");
					else System.out.println("Error: No se ha podido eliminar el actor deseado.");
				} 

				else {
					for (int i = 0; i < actores6.size(); i++) System.out.println("[" + i + "]" + actores6.get(i));
					int seleccion2 = Lector.getLector().leerEntero("Existen más de un actor con el mismo nombre, introduce la posicion cual quieres elegir: ");
					Actor actor6 = actores6.get(seleccion2);
					if (ListaActores.getListaActores().eliminarActor(actor6)) System.out.println("El actor " + actores6.get(seleccion2) + " se ha eliminado correctamente.");
					else System.out.println("Error: No se ha podido eliminar el actor deseado.");
				}

				break;

			case GUARDAR_EN_FICHERO:
				System.out.println("============| GUARDAR DATOS EN UN FICHERO |============");

				String ruta = Lector.getLector().leerString("Introduce la ruta y el nombre del fichero que quieres guardar: ");
				if (GestorFicheros.getGestorFicheros().guardarDatosEn(ruta)) System.out.println("Se han guardado los datos correctamente");
				else System.out.println("Error: No se ha podido eliminar el actor deseado.");
				
				break; 
			
			case ORDENAR:
				System.out.println("============| ORDENAR LISTA DE ACTORES |============");
				for (ArrayList<Actor> a: ListaActores.getListaActores().obtenerListaOrdenada()) System.out.println(a);
				break;
		}














	}
	
	/*
	public void iniciarBarra(String nombreFichero) {
		System.out.print("Cargando "+ nombreFichero + ": [");
	}
	
	public void avanzarBarra() throws InterruptedException {
		System.out.print("#");
	}
	
	public void finalizarBarra() {
		System.out.println("] Listo!");
	}
	*/
	
	
	
	/*
	public void barraDeCarga(int numElementos, String nombreFichero) throws InterruptedException {
		this.iniciarBarra(nombreFichero);
		int i = 0;
		while (i < numElementos) {
			this.avanzarBarra();
			Thread.sleep(50);
			i++;
		}
		this.finalizarBarra();
	}*/
}