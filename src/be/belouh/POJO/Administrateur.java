package be.belouh.POJO;

public class Administrateur extends Utilisateur {
	private String poste;

	// CONSTRUCTEURS
	public Administrateur() {
		super();
	}

	// GETTERS ET SETTERS
	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getPoste() {
		return poste;
	}
}
