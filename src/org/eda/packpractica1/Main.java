package org.eda.packpractica1;

public class Main {
	public static void main(String[] args) {
		try{
			Menu.getMenu().menu();
		} catch (InterruptedException e) {
			System.out.println("Error: " + e);
		}
	}
}
