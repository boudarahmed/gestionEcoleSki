package be.belouh.POJO;

import java.util.ArrayList;

public class Moniteur extends Utilisateur {
	// ATTRIBUTS
	private double salaireHoraire;
	private boolean coursParticulier;
	private ArrayList<Cours> listeCours = new ArrayList<Cours>();
	private ArrayList<Semaine> listeIndisponibilitee = new ArrayList<Semaine>();
	private ArrayList<Accreditation> listeAccreditation = new ArrayList<Accreditation>();

	// CONSTRUCTEURS
	public Moniteur() {
		super();
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

	public void setListeCours(ArrayList<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	public ArrayList<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeIndisponibilitee(ArrayList<Semaine> listeIndisponibilitee) {
		this.listeIndisponibilitee = listeIndisponibilitee;
	}

	public ArrayList<Semaine> getListeIndisponibilitee() {
		return listeIndisponibilitee;
	}

	public void setListeAccreditation(ArrayList<Accreditation> listeAccreditation) {
		this.listeAccreditation = listeAccreditation;
	}

	public ArrayList<Accreditation> getListeAccreditation() {
		return listeAccreditation;
	}

	// REDEFINITION
	@Override
	public boolean equals(Object obj) {
		Moniteur m;

		if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			m = (Moniteur) obj;
			if (m.getAdresseMail().equals(getAdresseMail()) && m.getMotDePasse().equals(getMotDePasse()))
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
		return "Moniteur " + this.getNom() + " " + this.getPrenom() + " né le " + this.getDateNaissance() + "\nDomicile : "
				+ this.getNumero() + " " + this.getRue() + " " + this.getCodePostal() + " " + this.getVille()
				+ "\npaye : " + this.getSalaireHoraire() + "/h";
	}
}
