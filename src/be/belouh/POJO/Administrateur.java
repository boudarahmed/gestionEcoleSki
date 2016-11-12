package be.belouh.POJO;

import java.util.Date;

public class Administrateur extends Utilisateur {
	private String poste;

	// CONSTRUCTEURS
	public Administrateur(int id, String nom, String prenom, Date dateNaissance, char sexe, String ville,
			String codePostal, String numero, String rue, String adresseMail, String motDePasse, String poste) {
		super(id, nom, prenom, dateNaissance, sexe, ville, codePostal, numero, rue, adresseMail, motDePasse);
		setPoste(poste);
	}

	public Administrateur(String nom, String prenom, Date dateNaissance, char sexe, String ville, String codePostal,
			String numero, String rue, String adresseMail, String motDePasse, String poste) {
		super(nom, prenom, dateNaissance, sexe, ville, codePostal, numero, rue, adresseMail, motDePasse);
		setPoste(poste);
	}

	public Administrateur() {
		super();
	}

	// GETTERS ET SETTERS
	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getPoste() {
		return poste;
	}
}
