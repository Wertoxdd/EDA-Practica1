package org.eda.packpractica1;

import java.util.Scanner;

public class Menu {
	private static Menu instanciaMenu = null;

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
	
	public void menu() throws InterruptedException {
		//TODO barra de carga y menu interactivo
		mensajeCargando("Leyendo archivos", 4);
		menuInteractivo();
		
	}
	
	public void mensajeCargando(String mensaje, int numVueltasTotal) {
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

	private void menuInteractivo() {		
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
		System.out.print(AMARILLO + "  » Elige una opción: " + RESET);
		
		pausa();		
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