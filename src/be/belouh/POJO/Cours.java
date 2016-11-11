package be.belouh.POJO;

import java.util.HashSet;

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
	protected HashSet<Reservation> listeReservation = new HashSet<Reservation>();

	// CONSTRUCTEURS
	public Cours(int id, String statutCours, Moniteur moniteur, Horaire horaire,
			HashSet<Reservation> listeReservation) {
		this.id = id;
		setStatutCours(statutCours);
		setMoniteur(moniteur);
		setHoraire(horaire);
		setListeReservation(listeReservation);
	}

	public Cours(String statutCours, Moniteur moniteur, Horaire horaire, HashSet<Reservation> listeReservation) {
		this.id = 0;
		setStatutCours(statutCours);
		setMoniteur(moniteur);
		setHoraire(horaire);
		setListeReservation(listeReservation);
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

	public void setListeReservation(HashSet<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}

	public HashSet<Reservation> getListeReservation() {
		return listeReservation;
	}
}
