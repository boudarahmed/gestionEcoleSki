package be.belouh.POJO;

import java.util.Date;
import java.util.HashSet;

public class Eleve extends Personne {
	// ATTRIBUTS
	private boolean assurance;
	private HashSet<Reservation> listeReservation = new HashSet<Reservation>();
	private HashSet<Cours> listeCours = new HashSet<Cours>();

	// CONSTRUCTEURS
	public Eleve(int id, String nom, String prenom, Date dateNaissance, char sexe, String ville, String codePostal,
			String numero, String rue, boolean assurance, HashSet<Reservation> listeReservation,
			HashSet<Cours> listeCours) {
		super(id, nom, prenom, dateNaissance, sexe, ville, codePostal, numero, rue);
		setAssurance(assurance);
		setListeReservation(listeReservation);
		setListeCours(listeCours);
	}

	public Eleve(String nom, String prenom, Date dateNaissance, char sexe, String ville, String codePostal,
			String numero, String rue, boolean assurance, HashSet<Reservation> listeReservation,
			HashSet<Cours> listeCours) {
		super(nom, prenom, dateNaissance, sexe, ville, codePostal, numero, rue);
		setAssurance(assurance);
		setListeReservation(listeReservation);
		setListeCours(listeCours);
	}

	// GETTERS ET SETTERS
	public void setAssurance(boolean assurance) {
		this.assurance = assurance;
	}

	public boolean getAssurance() {
		return assurance;
	}

	public void setListeReservation(HashSet<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}

	public HashSet<Reservation> getListeReservation() {
		return listeReservation;
	}

	public void setListeCours(HashSet<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	public HashSet<Cours> getListeCours() {
		return listeCours;
	}
}
