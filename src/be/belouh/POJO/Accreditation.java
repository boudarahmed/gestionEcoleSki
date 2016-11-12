package be.belouh.POJO;

public class Accreditation {
	// ATTRIBUTS
	private int id;
	private String sport;
	private int ageMin;
	private int ageMax;

	// CONSTRUCTEURS
	public Accreditation() {

	}

	// GETTERS ET SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getSport() {
		return sport;
	}

	public void setAgeMin(int ageMin) {
		this.ageMin = ageMin;
	}

	public int getAgeMin() {
		return ageMin;
	}

	public void setAgeMax(int ageMax) {
		this.ageMax = ageMax;
	}

	public int getAgeMax() {
		return ageMax;
	}

	// REDEFINITION
	@Override
	public boolean equals(Object obj) {
		Accreditation a;

		if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			a = (Accreditation) obj;
			if (a.getSport().equals(getSport()) && a.getAgeMin() == getAgeMin() && a.getAgeMax() == getAgeMax())
				return true;
			else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getSport().hashCode() + this.getAgeMin() + this.getAgeMax();
	}

	@Override
	public String toString() {
		return this.getSport() + " de " + this.getAgeMin() + " à " + this.getAgeMax();
	}
}
