package be.belouh.POJO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Semaine {
	// ATTRIBUTS
	private int id;
	private Date dateDeb;
	private Date dateFin;
	private boolean congeScolaire;

	// CONSTRUCTEURS
	public Semaine() {

	}

	// GETTERS ET SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

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

	// REDEFINITION
	@Override
	public boolean equals(Object obj) {
		Semaine s;

		if (obj == null || obj.getClass() != this.getClass())
			return false;
		else {
			s = (Semaine) obj;
			if (s.getDateDeb().equals(getDateDeb()) && s.getDateFin().equals(getDateFin())
					&& s.getCongeScolaire() == getCongeScolaire())
				return true;
			else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return this.getDateDeb().hashCode() + this.getDateFin().hashCode()
				+ Boolean.valueOf(this.getCongeScolaire()).hashCode();
	}

	@Override
	public String toString() {
		return new SimpleDateFormat("dd/MM/yyyy").format(this.getDateDeb()) + " au " + new SimpleDateFormat("dd/MM/yyyy").format(this.getDateFin());
	}
}
