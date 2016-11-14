package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.AdministrateurDAO;
import be.belouh.DAO.DAO;
import be.belouh.POJO.Administrateur;

public class ListeAdministrateurS {
	private ArrayList<Administrateur> liste = new ArrayList<Administrateur>();

	private static ListeAdministrateurS instance = null;

	private ListeAdministrateurS() {
		DAO<Administrateur> m = new AdministrateurDAO();
		ArrayList<Integer> id = m.compter();
		Iterator<Integer> it = id.iterator();

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
	}

	public static ListeAdministrateurS getInstance() {
		if (instance == null)
			instance = new ListeAdministrateurS();
		return instance;
	}
	
	public ArrayList<Administrateur> getListe() {
		return liste;
	}
}
