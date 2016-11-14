package be.belouh.Program;

import be.belouh.Controlleur.Controlleur;
import be.belouh.Vue.Fenetre;

public class Main {
	public static void main(String[] args) {
		new Controlleur(new Fenetre());
	}
}
