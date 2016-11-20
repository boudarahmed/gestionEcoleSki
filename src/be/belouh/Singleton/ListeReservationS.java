package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.DAO;
import be.belouh.DAO.ReservationDAO;
import be.belouh.POJO.Reservation;

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

	public ArrayList<Reservation> getListe() {
		return liste;
	}
}
