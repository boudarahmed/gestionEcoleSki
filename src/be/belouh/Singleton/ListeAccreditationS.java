package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.AccreditationDAO;
import be.belouh.DAO.DAO;
import be.belouh.POJO.Accreditation;

public class ListeAccreditationS {
	private ArrayList<Accreditation> liste = new ArrayList<Accreditation>();

	private static ListeAccreditationS instance = null;

	private ListeAccreditationS() {
		DAO<Accreditation> m = new AccreditationDAO();
		ArrayList<Integer> id = m.compter();
		Iterator<Integer> it = id.iterator();

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
	}

	public static ListeAccreditationS getInstance() {
		if (instance == null)
			instance = new ListeAccreditationS();
		return instance;
	}

	public ArrayList<Accreditation> getListe() {
		return liste;
	}
}
