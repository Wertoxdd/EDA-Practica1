package org.eda.packpractica1;

import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
	private static Menu instanciaMenu = null;
	private enum Opciones {CARGAR_FICHERO, ENCONTRAR_ACTOR, AÑADIR_ACTOR, AÑADIR_PELICULA, OBTENER_PELICULAS_DE_ACTOR, OBTENER_ACTORES_DE_PELICULA, 
		CAMBIAR_AÑO_DE_PELICULA, ELIMINAR_ACTOR, GUARDAR_FICHERO, ORDENAR
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
		Menu.getMenu().mensajeCargando("Pulsa Enter para continuar", 50);
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

			mensajeCargando("Elige una opción", 40);
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

				String[] carreraActor = Lector.getLector().leerString("Introduce los titulos de las peliculas en las que participa el actor/actriz (separalos con un guión: '---' ): ").split("----");

				for (String titulo: carreraActor){
					if (titulo.length() == 0) continue;

					ArrayList<Pelicula> peliculas = ListaPeliculas.getListaPeliculas().obtenerPeliculasPorTitulo(titulo);
					int i = 0;
					if (peliculas.size() > 1){
						i = Lector.getLector().leerEntero("Ya existe una pelicula llamada '" + titulo + "'. Introduce la posicion de la pelicula correcta: (0-" + (peliculas.size()-1) + "): ");
						actor.añadirPelicula(peliculas.get(i));
						peliculas.get(i).añadirActor(actor);
					}
				}

				break;
			
			case AÑADIR_PELICULA:
				System.out.println("============| AÑADIR PELICULA |============");

				String titulo = Lector.getLector().leerString("Introduce el titulo de la pelicula: ");
				//TODO terminar los casos y pulir codigo, hacer los JUnits y comprobar todo. 

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