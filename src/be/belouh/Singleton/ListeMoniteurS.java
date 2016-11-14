package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.DAO;
import be.belouh.DAO.MoniteurDAO;
import be.belouh.POJO.Moniteur;

public class ListeMoniteurS {
	private ArrayList<Moniteur> liste = new ArrayList<Moniteur>();

	private static ListeMoniteurS instance = null;

	private ListeMoniteurS() {
		DAO<Moniteur> m = new MoniteurDAO();
		ArrayList<Integer> id = m.compter();
		Iterator<Integer> it = id.iterator();

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
	}

	public static ListeMoniteurS getInstance() {
		if (instance == null)
			instance = new ListeMoniteurS();
		return instance;
	}
	
	public ArrayList<Moniteur> getListe() {
		return liste;
	}
}
