package be.belouh.POJO;

import java.util.Date;

public class Semaine {
	// ATTRIBUTS
	private int id;
	private Date dateDeb;
	private Date dateFin;
	private boolean congeScolaire;

	// CONSTRUCTEURS
	public Semaine(int id, Date dateDeb, Date dateFin, boolean congeScolaire) {
		this.id = id;
		setDateDeb(dateDeb);
		setDateFin(dateFin);
		setCongeScolaire(congeScolaire);
	}

	// GETTERS ET SETTERS
	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setCongeScolaire(boolean congeScolaire) {
		this.congeScolaire = congeScolaire;
	}

	public boolean getCongeScolaire() {
		return congeScolaire;
	}
}
