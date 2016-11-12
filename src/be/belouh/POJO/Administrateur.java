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

	// REDEFINITION
	@Override
	public boolean equals(Object obj) {
		Administrateur a;

		if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			a = (Administrateur) obj;
			if (a.getAdresseMail().equals(getAdresseMail()) && a.getMotDePasse().equals(getMotDePasse()))
				return true;
			else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getAdresseMail().hashCode() + this.getMotDePasse().hashCode();
	}

	@Override
	public String toString() {
		if (this.getSexe() == 'M')
			return "M." + this.getNom() + " " + this.getPrenom() + " né le " + this.getDateNaissance() + "\nDomicile : "
					+ this.getNumero() + " " + this.getRue() + " " + this.getCodePostal() + " " + this.getVille()
					+ "\nPoste : " + this.getPoste();
		else
			return "Mme." + this.getNom() + " " + this.getPrenom() + " né le " + this.getDateNaissance()
					+ "\nDomicile : " + this.getNumero() + " " + this.getRue() + " " + this.getCodePostal() + " "
					+ this.getVille() + "\nPoste : " + this.getPoste();
	}
}
