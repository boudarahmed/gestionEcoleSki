package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.EleveDAO;
import be.belouh.DAO.DAO;
import be.belouh.POJO.Eleve;

public class ListeEleveS {
	private ArrayList<Eleve> liste = new ArrayList<Eleve>();

	private static ListeEleveS instance = null;

	private ListeEleveS() {
		DAO<Eleve> m = new EleveDAO();
		ArrayList<Integer> id = m.compter();
		Iterator<Integer> it = id.iterator();

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
	}

	public static ListeEleveS getInstance() {
		if (instance == null)
			instance = new ListeEleveS();
		return instance;
	}

	public ArrayList<Eleve> getListe() {
		return liste;
	}
}
