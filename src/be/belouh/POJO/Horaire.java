package be.belouh.POJO;

public class Horaire {
	// ATTRIBUTS
	private int id;
	private int heureDeb;
	private int heureFin;

	// CONSTRUCTEURS
	public Horaire() {

	}

	// GETTERS ET SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setHeureDeb(int heureDeb) {
		this.heureDeb = heureDeb;
	}

	public int getHeureDeb() {
		return heureDeb;
	}

	public void setHeureFin(int heureFin) {
		this.heureFin = heureFin;
	}

	public int getHeureFin() {
		return heureFin;
	}
}
