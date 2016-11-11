package be.belouh.POJO;

public class TypeCours {
	// ATTRIBUTS
	private int id;
	private String niveau;
	private int minEleve;
	private int maxEleve;
	private double prix;
	private Accreditation accreditation;

	// CONSTRUCTEURS
	public TypeCours(int id, String niveau, int minEleve, int maxEleve, double prix, Accreditation accreditation) {
		this.id = id;
		setNiveau(niveau);
		setMinEleve(minEleve);
		setMaxEleve(maxEleve);
		setPrix(prix);
		setAccreditation(accreditation);
	}

	public TypeCours(String niveau, int minEleve, int maxEleve, double prix, Accreditation accreditation) {
		this.id = 0;
		setNiveau(niveau);
		setMinEleve(minEleve);
		setMaxEleve(maxEleve);
		setPrix(prix);
		setAccreditation(accreditation);
	}

	// GETTERS ET SETTERS
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setMinEleve(int minEleve) {
		this.minEleve = minEleve;
	}

	public int getMinEleve() {
		return minEleve;
	}

	public void setMaxEleve(int maxEleve) {
		this.maxEleve = maxEleve;
	}

	public int getMaxEleve() {
		return maxEleve;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getPrix() {
		return prix;
	}

	public void setAccreditation(Accreditation accreditation) {
		this.accreditation = accreditation;
	}

	public Accreditation getAccreditation() {
		return accreditation;
	}
}
