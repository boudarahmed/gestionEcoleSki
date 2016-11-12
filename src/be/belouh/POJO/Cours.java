package be.belouh.POJO;

import java.util.ArrayList;

public abstract class Cours {
	// STATUT RESERVATION
	public static final String ATTENTE = "ATTENTE";
	public static final String OUVERT = "OUVERT";
	public static final String FERMER = "FERMER";
	// ATTRIBUTS
	protected int id;
	protected String statutCours;
	protected Moniteur moniteur;
	protected Horaire horaire;
	protected ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();

	// CONSTRUCTEURS
	public Cours() {

	}

	// GETTERS ET SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setStatutCours(String statutCours) {
		this.statutCours = statutCours;
	}

	public String getStatutCours() {
		return statutCours;
	}

	public void setMoniteur(Moniteur moniteur) {
		this.moniteur = moniteur;
	}

	public Moniteur getMoniteur() {
		return moniteur;
	}

	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}

	public Horaire getHoraire() {
		return horaire;
	}

	public void setListeReservation(ArrayList<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}

	public ArrayList<Reservation> getListeReservation() {
		return listeReservation;
	}
}
