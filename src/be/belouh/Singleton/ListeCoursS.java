package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.CoursCollectifDAO;
import be.belouh.DAO.CoursParticulierDAO;
import be.belouh.DAO.DAO;
import be.belouh.POJO.Cours;
import be.belouh.POJO.CoursCollectif;
import be.belouh.POJO.CoursParticulier;

public class ListeCoursS {
	private ArrayList<Cours> liste = new ArrayList<Cours>();

	private static ListeCoursS instance = null;

	private ListeCoursS() {
		DAO<CoursCollectif> m = new CoursCollectifDAO();
		DAO<CoursParticulier> c = new CoursParticulierDAO();
		ArrayList<Integer> id = m.compter();
		Iterator<Integer> it = id.iterator();

		while (it.hasNext())
			liste.add(m.trouver(it.next()));

		id = c.compter();
		it = id.iterator();

		while (it.hasNext())
			liste.add(c.trouver(it.next()));
	}

	public static ListeCoursS getInstance() {
		if (instance == null)
			instance = new ListeCoursS();
		return instance;
	}

	public ArrayList<Cours> getListe() {
		return liste;
	}
}
