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

	// REDEFINITION
	@Override
	public boolean equals(Object obj) {
		Horaire h;

		if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			h = (Horaire) obj;
			if (h.getHeureDeb() == getHeureDeb() && h.getHeureFin() == getHeureFin())
				return true;
			else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getHeureDeb() + this.getHeureFin();
	}

	@Override
	public String toString() {
		return this.getHeureDeb() + "h à " + this.getHeureFin() + "h";
	}
}
