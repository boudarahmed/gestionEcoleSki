package be.belouh.POJO;

import java.util.ArrayList;

public class Eleve extends Personne {
	// ATTRIBUTS
	private boolean assurance;
	private ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
	private ArrayList<Cours> listeCours = new ArrayList<Cours>();

	// CONSTRUCTEURS
	public Eleve() {
		super();
	}

	// GETTERS ET SETTERS
	public void setAssurance(boolean assurance) {
		this.assurance = assurance;
	}

	public boolean getAssurance() {
		return assurance;
	}

	public void setListeReservation(ArrayList<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}

	public ArrayList<Reservation> getListeReservation() {
		return listeReservation;
	}

	public void setListeCours(ArrayList<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	public ArrayList<Cours> getListeCours() {
		return listeCours;
	}

	// REDEFINITION
	@Override
	public boolean equals(Object obj) {
		Eleve e;

		if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			e = (Eleve) obj;
			if (e.getNom().equals(getNom()) && e.getPrenom().equals(getPrenom())
					&& e.getDateNaissance().equals(getDateNaissance()))
				return true;
			else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getNom().hashCode() + this.getPrenom().hashCode() + this.getDateNaissance().hashCode();
	}

	@Override
	public String toString() {
		return this.getNom() + " " + this.getPrenom() + "né(e) le" + this.getDateNaissance();
	}
}
