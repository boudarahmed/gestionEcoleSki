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

	// REDEFINITION
	@Override
	public boolean equals(Object obj) {
		CoursCollectif c;

		if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			c = (CoursCollectif) obj;
			if (c.getHoraire().equals(getHoraire()) && c.getSemaine().equals(getSemaine())
					&& c.getTypeCours().equals(getTypeCours()))
				return true;
			else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getHoraire().hashCode() + this.getSemaine().hashCode() + this.getTypeCours().hashCode();
	}

	@Override
	public String toString() {
		return "- Cours de " + this.getTypeCours().getAccreditation().getSport() + " niveau "
				+ this.getTypeCours().getNiveau() + " du " + this.getSemaine().toString() + " de "
				+ this.getHoraire().toString() + "\n";
	}
}
