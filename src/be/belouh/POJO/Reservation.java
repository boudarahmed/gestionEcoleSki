package be.belouh.POJO;

import java.util.Date;
import java.util.HashSet;

public class Reservation {
	// STATUT RESERVATION
	public static final String COMMANDER = "COMMANDER";
	public static final String RESERVER = "RESERVER";
	public static final String PAYER = "PAYER";
	// ATTRIBUTS
	private int id;
	private Date dateReservation;
	private String statutReservation;
	private double prix;
	private Client client;
	private Eleve eleve;
	private HashSet<Cours> listeCours = new HashSet<Cours>();

	// CONSTRUCTEURS
	public Reservation(int id, Date dateReservation, String statutReservation, double prix, Client client, Eleve eleve,
			HashSet<Cours> listeCours) {
		this.id = id;
		setDateReservation(dateReservation);
		setStatutReservation(statutReservation);
		setPrix(prix);
		setClient(client);
		setEleve(eleve);
		setListeCours(listeCours);
	}

	public Reservation(Date dateReservation, String statutReservation, double prix, Client client, Eleve eleve,
			HashSet<Cours> listeCours) {
		this.id = 0;
		setDateReservation(dateReservation);
		setStatutReservation(statutReservation);
		setPrix(prix);
		setClient(client);
		setEleve(eleve);
		setListeCours(listeCours);
	}

	public Reservation() {

	}

	// GETTERS ET SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setStatutReservation(String statutReservation) {
		this.statutReservation = statutReservation;
	}

	public String getStatutReservation() {
		return statutReservation;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getPrix() {
		return prix;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setListeCours(HashSet<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	public HashSet<Cours> getListeCours() {
		return listeCours;
	}
}
