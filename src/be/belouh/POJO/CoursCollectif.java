package be.belouh.POJO;

import java.util.HashSet;

public class CoursCollectif extends Cours {
	// ATTRIBUTS
	private Semaine semaine;
	private TypeCours typeCours;

	// CONSTRUCTEURS
	public CoursCollectif(int id, String statutCours, Moniteur moniteur, Horaire horaire,
			HashSet<Reservation> listeReservation, Semaine semaine, TypeCours typeCours) {
		super(id, statutCours, moniteur, horaire, listeReservation);
		setSemaine(semaine);
		setTypeCours(typeCours);
	}

	public CoursCollectif(String statutCours, Moniteur moniteur, Horaire horaire, HashSet<Reservation> listeReservation,
			Semaine semaine, TypeCours typeCours) {
		super(statutCours, moniteur, horaire, listeReservation);
		setSemaine(semaine);
		setTypeCours(typeCours);
	}

	// GETTERS ET SETTERS
	public void setSemaine(Semaine semaine) {
		this.semaine = semaine;
	}

	public Semaine getSemaine() {
		return semaine;
	}

	public void setTypeCours(TypeCours typeCours) {
		this.typeCours = typeCours;
	}

	public TypeCours getTypeCours() {
		return typeCours;
	}
}
