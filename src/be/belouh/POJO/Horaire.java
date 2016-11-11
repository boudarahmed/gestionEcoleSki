package be.belouh.POJO;

public class Horaire {
	// ATTRIBUTS
	private int id;
	private int heureDeb;
	private int heureFin;

	// CONSTRUCTEURS
	public Horaire(int id, int heureDeb, int heureFin) {
		this.id = id;
		setHeureDeb(heureDeb);
		setHeureFin(heureFin);
	}

	public Horaire(int heureDeb, int heureFin) {
		this.id = 0;
		setHeureDeb(heureDeb);
		setHeureFin(heureFin);
	}

	// GETTERS ET SETTERS
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
