package org.eda.packpractica1;

import java.util.Scanner;

public class Menu {
	private static Menu instanciaMenu = null;

	public static Menu getMenu() {
		if(instanciaMenu == null) {
			instanciaMenu = new Menu();
		}
		return instanciaMenu;
	}
	
	public void menu() throws InterruptedException {
		//TODO barra de carga y menu interactivo
		mensajeCargando("Leyendo archivos");
		menuInteractivo();
		
	}
	
	public void mensajeCargando(String mensaje) {
		String[] bucleCargando = {"   ",".  ",".. ","..."};
		for (int numVueltas = 0; numVueltas < 4; numVueltas++) {
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
		Menu.getMenu().mensajeCargando("Pulsa Enter para continuar");
		sc.nextLine();
		sc.close();
	}

	private void menuInteractivo() {		
		System.out.println("------------------------------ Menú ------------------------------");
		System.out.println("|                                                                |");
		System.out.println("|                                                                |");
		System.out.println("|  1.                                                               |");
		System.out.println("|                                                                |");
		System.out.println("|                                                                |");
		System.out.println("|                                                                |");
		System.out.println("|                                                                |");
		System.out.println("|                                                                |");
		System.out.println("------------------------------------------------------------------");
		
		
		Lector.getLector().pausa();
		
		
		
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