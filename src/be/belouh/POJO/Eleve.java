package be.belouh.POJO;

import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Eleve extends Personne {
	// ATTRIBUTS
	private boolean assurance;
	private Client client;
	private ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();

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

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void setListeReservation(ArrayList<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}

	public ArrayList<Reservation> getListeReservation() {
		return listeReservation;
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
		return this.getNom() + " " + this.getPrenom() + " "
				+ Period.between(this.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getYears()
				+ " ans\n";
	}
}
