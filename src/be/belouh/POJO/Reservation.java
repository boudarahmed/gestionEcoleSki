package be.belouh.POJO;

import java.util.Date;
import java.util.ArrayList;

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
	private ArrayList<Cours> listeCours = new ArrayList<Cours>();

	// CONSTRUCTEURS
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

	public void setListeCours(ArrayList<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	public ArrayList<Cours> getListeCours() {
		return listeCours;
	}

	// REDEFINITION
	@Override
	public boolean equals(Object obj) {
		Reservation r;

		if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			r = (Reservation) obj;
			if (r.getDateReservation().equals(getDateReservation()) && r.getClient().equals(getClient())
					&& r.getEleve().equals(getEleve()) && r.getListeCours().equals(getListeCours()))
				return true;
			else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getDateReservation().hashCode() + this.getClient().hashCode() + this.getEleve().hashCode()
				+ this.getListeCours().hashCode();
	}

	@Override
	public String toString() {
		return "Réservation passée le " + this.getDateReservation() + " par " + this.getClient().toString() + " pour "
				+ this.getEleve().toString() + " concernant " + this.getListeCours().size() + " cours";
	}
}
