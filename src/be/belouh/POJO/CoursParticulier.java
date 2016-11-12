package be.belouh.POJO;

import java.util.Date;

public class CoursParticulier extends Cours {
	// STATUT RESERVATION
	public static final int MIN_ELEVE = 1;
	public static final int MAX_ELEVE = 4;
	public static final double PRIX_1_HEURE = 50;
	public static final double PRIX_2_HEURE = 80;
	// ATTRIBUTS
	private Date date;
	private boolean congeScolaire;
	private double prix;
	private Accreditation accreditation;

	// CONSTRUCTEURS
	public CoursParticulier() {
		super();
	}

	// GETTERS ET SETTERS
	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setCongeScolaire(boolean congeScolaire) {
		this.congeScolaire = congeScolaire;
	}

	public boolean getCongeScolaire() {
		return congeScolaire;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getPrix() {
		return prix;
	}

	public void setAccreditation(Accreditation accreditation) {
		this.accreditation = accreditation;
	}

	public Accreditation getAccreditation() {
		return accreditation;
	}

	// REDEFINITION
	@Override
	public boolean equals(Object obj) {
		CoursParticulier c;

		if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			c = (CoursParticulier) obj;
			if (c.getDate().equals(getDate()) && c.getHoraire().equals(getHoraire())
					&& c.getAccreditation().equals(getAccreditation()))
				return true;
			else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getDate().hashCode() + this.getHoraire().hashCode() + this.getAccreditation().hashCode();
	}

	@Override
	public String toString() {
		return "Cours particulier de " + this.getAccreditation().toString() + " le " + this.getDate() + " de "
				+ this.getHoraire();
	}
}
