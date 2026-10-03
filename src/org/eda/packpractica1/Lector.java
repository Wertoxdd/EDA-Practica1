package org.eda.packpractica1;

import java.util.Scanner;

public class Lector {
	private static Lector miLector = null;
	private Scanner sc;
	
	private Lector() {
		sc = new Scanner(System.in);
	}
	
	public static Lector getLector() {
		if (miLector == null) {
			miLector = new Lector();
		}
		return miLector;
	}
	
	public int leerEntero(String mensaje) {
		System.out.print(mensaje);
		return Integer.parseInt(sc.nextLine());
	}
	
	public String leerString(String mensaje) {
		System.out.print(mensaje);
		return sc.nextLine();
	}
}
