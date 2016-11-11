package be.belouh.POJO;

public class Accreditation {
	// ATTRIBUTS
	private int id;
	private String sport;
	private int ageMin;
	private int ageMax;

	// CONSTRUCTEURS
	public Accreditation(int id, String sport, int ageMin, int ageMax) {
		this.id = id;
		setSport(sport);
		setAgeMin(ageMin);
		setAgeMax(ageMax);
	}

	public Accreditation(String sport, int ageMin, int ageMax) {
		this.id = 0;
		setSport(sport);
		setAgeMin(ageMin);
		setAgeMax(ageMax);
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
}
