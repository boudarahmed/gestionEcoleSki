package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.DAO;
import be.belouh.DAO.ReservationDAO;
import be.belouh.POJO.Cours;
import be.belouh.POJO.CoursCollectif;
import be.belouh.POJO.CoursParticulier;
import be.belouh.POJO.Reservation;
import be.belouh.POJO.Semaine;

public class ListeReservationS {
	private ArrayList<Reservation> liste = new ArrayList<Reservation>();

	private static ListeReservationS instance = null;

	private ListeReservationS() {
		DAO<Reservation> m = new ReservationDAO();
		ArrayList<Integer> id = m.compter();
		Iterator<Integer> it = id.iterator();

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
	}

	public static ListeReservationS getInstance() {
		if (instance == null)
			instance = new ListeReservationS();
		return instance;
	}

	public Reservation ajouterReservation(Reservation r) {
		DAO<Reservation> res = new ReservationDAO();
		boolean flag = false;
		for (Reservation reservation : liste) {
			if (reservation.equals(r))
				flag = true;
		}
		if (!flag) {
			r = res.inserer(r);
			liste.add(r);
			return r;
		} else
			return null;
	}

	public boolean supprimerReservation(Reservation r) {
		DAO<Reservation> res = new ReservationDAO();
		if (res.supprimer(r)) {
			liste.remove(r);
			return true;
		} else {
			return false;
		}
	}

	public Reservation mettreAjourReservation(Reservation r) {
		DAO<Reservation> res = new ReservationDAO();
		r = res.mettreAJour(r);
		return r;
	}

	public void calculPrix(Reservation r) {
		double prix = 0;
		ArrayList<Semaine> list = new ArrayList<Semaine>();
		boolean flag = false;
		for (Cours cours : r.getListeCours()) {
			if (cours instanceof CoursParticulier) {
				if ((((CoursParticulier) cours).getHoraire().getHeureFin()
						- ((CoursParticulier) cours).getHoraire().getHeureDeb()) == 2)
					prix += CoursParticulier.PRIX_2_HEURE;
				else
					prix += CoursParticulier.PRIX_1_HEURE;
			}
			if (cours instanceof CoursCollectif) {
				if (list.contains(((CoursCollectif) cours).getSemaine()))
					flag = true;
				else
					list.add(((CoursCollectif) cours).getSemaine());

				if (r.getEleve().getAssurance())
					prix += ((CoursCollectif) cours).getTypeCours().getPrix() + 15;
				else
					prix += ((CoursCollectif) cours).getTypeCours().getPrix();
			}
		}
		if(flag)
			prix = prix-(prix/100*15);
		r.setPrix(prix);
	}
	
	public void actualiserStatut(){
		for (Reservation reservation : liste) {
			boolean flag = true;
			for (Cours cours : reservation.getListeCours()) {
				if(cours.getStatutCours().equals(Cours.ATTENTE))
					flag = false;
			}
			if(flag){
				reservation.setStatutReservation(Reservation.RESERVER);
				mettreAjourReservation(reservation);
			}
		}
	}

	public ArrayList<Reservation> getListe() {
		return liste;
	}
}
