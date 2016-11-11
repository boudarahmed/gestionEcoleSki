package be.belouh.POJO;

import java.util.Date;
import java.util.HashSet;

public class Moniteur extends Utilisateur {
	// ATTRIBUTS
	private double salaireHoraire;
	private boolean coursParticulier;
	private HashSet<Cours> listeCours = new HashSet<Cours>();
	private HashSet<Semaine> listeIndisponibilitee = new HashSet<Semaine>();
	private HashSet<Accreditation> listeAccreditation = new HashSet<Accreditation>();

	// CONSTRUCTEURS
	public Moniteur(int id, String nom, String prenom, Date dateNaissance, char sexe, String ville, String codePostal,
			String numero, String rue, String adresseMail, String motDePasse, double salaireHoraire,
			boolean coursParticulier, HashSet<Cours> listeCours, HashSet<Semaine> listeIndisponibilitee,
			HashSet<Accreditation> listeAccreditation) {
		super(id, nom, prenom, dateNaissance, sexe, ville, codePostal, numero, rue, adresseMail, motDePasse);
		setSalaireHoraire(salaireHoraire);
		setCoursParticulier(coursParticulier);
		setListeCours(listeCours);
		setListeIndisponibilitee(listeIndisponibilitee);
		setListeAccreditation(listeAccreditation);
	}

	public Moniteur(String nom, String prenom, Date dateNaissance, char sexe, String ville, String codePostal,
			String numero, String rue, String adresseMail, String motDePasse, double salaireHoraire,
			boolean coursParticulier, HashSet<Cours> listeCours, HashSet<Semaine> listeIndisponibilitee,
			HashSet<Accreditation> listeAccreditation) {
		super(nom, prenom, dateNaissance, sexe, ville, codePostal, numero, rue, adresseMail, motDePasse);
		setSalaireHoraire(salaireHoraire);
		setCoursParticulier(coursParticulier);
		setListeCours(listeCours);
		setListeIndisponibilitee(listeIndisponibilitee);
		setListeAccreditation(listeAccreditation);
	}

	// GETTERS ET SETTERS
	public void setSalaireHoraire(double salaireHoraire) {
		this.salaireHoraire = salaireHoraire;
	}

	public double getSalaireHoraire() {
		return salaireHoraire;
	}

	public void setCoursParticulier(boolean coursParticulier) {
		this.coursParticulier = coursParticulier;
	}

	public boolean getCoursParticulier() {
		return coursParticulier;
	}

	public void setListeCours(HashSet<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	public HashSet<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeIndisponibilitee(HashSet<Semaine> listeIndisponibilitee) {
		this.listeIndisponibilitee = listeIndisponibilitee;
	}

	public HashSet<Semaine> getListeIndisponibilitee() {
		return listeIndisponibilitee;
	}

	public void setListeAccreditation(HashSet<Accreditation> listeAccreditation) {
		this.listeAccreditation = listeAccreditation;
	}

	public HashSet<Accreditation> getListeAccreditation() {
		return listeAccreditation;
	}
}
