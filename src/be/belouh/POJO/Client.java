package be.belouh.POJO;

import java.util.HashSet;

public class Client extends Utilisateur {
	// ATTRIBUTS
	private String numeroCompte;
	private HashSet<Reservation> listeReservation = new HashSet<Reservation>();

	// CONSTRUCTEURS
	public Client() {
		super();
	}

	// GETTERS ET SETTERS
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setListeReservation(HashSet<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}

	public HashSet<Reservation> getListeReservation() {
		return listeReservation;
	}
}
