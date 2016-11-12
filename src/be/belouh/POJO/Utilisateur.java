package be.belouh.POJO;

public abstract class Utilisateur extends Personne {
	// ATTRIBUTS
	protected String adresseMail;
	protected String motDePasse;

	// CONSTRUCTEURS
	public Utilisateur() {
		super();
	}

	// GETTERS ET SETTERS
	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getMotDePasse() {
		return motDePasse;
	}
}
