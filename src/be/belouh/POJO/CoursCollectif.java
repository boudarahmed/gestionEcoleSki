package be.belouh.POJO;

public class CoursCollectif extends Cours {
	// ATTRIBUTS
	private Semaine semaine;
	private TypeCours typeCours;

	// CONSTRUCTEURS
	public CoursCollectif() {
		super();
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
