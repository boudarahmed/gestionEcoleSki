package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.HoraireDAO;
import be.belouh.DAO.DAO;
import be.belouh.POJO.Horaire;

public class ListeHoraireS {
	private ArrayList<Horaire> liste = new ArrayList<Horaire>();

	private static ListeHoraireS instance = null;

	private ListeHoraireS() {
		DAO<Horaire> m = new HoraireDAO();
		ArrayList<Integer> id = m.compter();
		Iterator<Integer> it = id.iterator();

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
	}

	public static ListeHoraireS getInstance() {
		if (instance == null)
			instance = new ListeHoraireS();
		return instance;
	}

	public ArrayList<Horaire> getListe() {
		return liste;
	}
}
