package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.SemaineDAO;
import be.belouh.DAO.DAO;
import be.belouh.POJO.Semaine;

public class ListeSemaineS {
	private ArrayList<Semaine> liste = new ArrayList<Semaine>();

	private static ListeSemaineS instance = null;

	private ListeSemaineS() {
		DAO<Semaine> m = new SemaineDAO();
		ArrayList<Integer> id = m.compter();
		Iterator<Integer> it = id.iterator();

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
	}

	public static ListeSemaineS getInstance() {
		if (instance == null)
			instance = new ListeSemaineS();
		return instance;
	}

	public ArrayList<Semaine> getListe() {
		return liste;
	}
}
