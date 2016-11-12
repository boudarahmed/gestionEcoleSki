package be.belouh.POJO;

import java.util.Date;

public abstract class Personne {
	// ATTRIBUTS
	protected int id;
	protected String nom;
	protected String prenom;
	protected Date dateNaissance;
	protected char sexe;
	protected String ville;
	protected String codePostal;
	protected String numero;
	protected String rue;

	// CONSTRUCTEURS
	public Personne() {

	}

	// GETTERS ET SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
	}

	public char getSexe() {
		return sexe;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getVille() {
		return ville;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getRue() {
		return rue;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}
}
