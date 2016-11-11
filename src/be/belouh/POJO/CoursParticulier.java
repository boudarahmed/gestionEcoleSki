package be.belouh.POJO;

import java.util.Date;
import java.util.HashSet;

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
	public CoursParticulier(int id, String statutCours, Moniteur moniteur, Horaire horaire,
			HashSet<Reservation> listeReservation, Date date, boolean congeScolaire, double prix,
			Accreditation accreditation) {
		super(id, statutCours, moniteur, horaire, listeReservation);
		setDate(date);
		setCongeScolaire(congeScolaire);
		setPrix(prix);
		setAccreditation(accreditation);
	}

	public CoursParticulier(String statutCours, Moniteur moniteur, Horaire horaire,
			HashSet<Reservation> listeReservation) {
		super(statutCours, moniteur, horaire, listeReservation);
		setDate(date);
		setCongeScolaire(congeScolaire);
		setPrix(prix);
		setAccreditation(accreditation);
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
}
