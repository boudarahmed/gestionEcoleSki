package be.belouh.POJO;

import java.util.Date;

public abstract class Utilisateur extends Personne {
	// ATTRIBUTS
	protected String adresseMail;
	protected String motDePasse;

	// CONSTRUCTEURS
	public Utilisateur(int id, String nom, String prenom, Date dateNaissance, char sexe, String ville,
			String codePostal, String numero, String rue, String adresseMail, String motDePasse) {
		super(id, nom, prenom, dateNaissance, sexe, ville, codePostal, numero, rue);
		setAdresseMail(adresseMail);
		setMotDePasse(motDePasse);
	}

	public Utilisateur(String nom, String prenom, Date dateNaissance, char sexe, String ville, String codePostal,
			String numero, String rue, String adresseMail, String motDePasse) {
		super(nom, prenom, dateNaissance, sexe, ville, codePostal, numero, rue);
		setAdresseMail(adresseMail);
		setMotDePasse(motDePasse);
	}

	public Utilisateur() {
		super();
	}

	// GETTERS ET SETTERS
	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getMotDePasse() {
		return motDePasse;
	}
}
