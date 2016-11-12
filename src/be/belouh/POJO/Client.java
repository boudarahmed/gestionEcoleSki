package be.belouh.POJO;

import java.util.ArrayList;

public class Client extends Utilisateur {
	// ATTRIBUTS
	private String numeroCompte;
	private ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();

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

	public void setListeReservation(ArrayList<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}

	public ArrayList<Reservation> getListeReservation() {
		return listeReservation;
	}

	// REDEFINITION
	@Override
	public boolean equals(Object obj) {
		Client c;

		if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			c = (Client) obj;
			if (c.getAdresseMail().equals(getAdresseMail()) && c.getMotDePasse().equals(getMotDePasse()))
				return true;
			else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getAdresseMail().hashCode() + this.getMotDePasse().hashCode();
	}

	@Override
	public String toString() {
		if (this.getSexe() == 'M')
			return "M." + this.getNom() + " " + this.getPrenom() + " né le " + this.getDateNaissance() + "\nDomicile : "
					+ this.getNumero() + " " + this.getRue() + " " + this.getCodePostal() + " " + this.getVille()
					+ "\nNuméro de compte : " + this.getNumeroCompte();
		else
			return "Mme." + this.getNom() + " " + this.getPrenom() + " né le " + this.getDateNaissance()
					+ "\nDomicile : " + this.getNumero() + " " + this.getRue() + " " + this.getCodePostal() + " "
					+ this.getVille() + "\nNuméro de compte : " + this.getNumeroCompte();
	}
}
