package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.ReservationDAO;
import be.belouh.DAO.DAO;
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

	public ArrayList<Reservation> getListe() {
		return liste;
	}
}
