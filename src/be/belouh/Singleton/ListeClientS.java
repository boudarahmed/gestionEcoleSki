package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.ClientDAO;
import be.belouh.DAO.DAO;
import be.belouh.POJO.Client;

public class ListeClientS {
	private ArrayList<Client> liste = new ArrayList<Client>();

	private static ListeClientS instance = null;

	private ListeClientS() {
		DAO<Client> m = new ClientDAO();
		ArrayList<Integer> id = m.compter();
		Iterator<Integer> it = id.iterator();

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
	}

	public static ListeClientS getInstance() {
		if (instance == null)
			instance = new ListeClientS();
		return instance;
	}

	public ArrayList<Client> getListe() {
		return liste;
	}
}
