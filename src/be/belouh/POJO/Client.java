package be.belouh.POJO;

import java.util.Date;
import java.util.HashSet;

public class Client extends Utilisateur {
	// ATTRIBUTS
	private String numeroCompte;
	private HashSet<Reservation> listeReservation = new HashSet<Reservation>();

	// CONSTRUCTEURS
	public Client(int id, String nom, String prenom, Date dateNaissance, char sexe, String ville, String codePostal,
			String numero, String rue, String adresseMail, String motDePasse, String numeroCompte,
			HashSet<Reservation> listeReservation) {
		super(id, nom, prenom, dateNaissance, sexe, ville, codePostal, numero, rue, adresseMail, motDePasse);
		setNumeroCompte(numeroCompte);
		setListeReservation(listeReservation);
	}

	public Client(String nom, String prenom, Date dateNaissance, char sexe, String ville, String codePostal,
			String numero, String rue, String adresseMail, String motDePasse, String numeroCompte,
			HashSet<Reservation> listeReservation) {
		super(nom, prenom, dateNaissance, sexe, ville, codePostal, numero, rue, adresseMail, motDePasse);
		setNumeroCompte(numeroCompte);
		setListeReservation(listeReservation);
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
