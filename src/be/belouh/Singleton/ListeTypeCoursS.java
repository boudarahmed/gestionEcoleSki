package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.TypeCoursDAO;
import be.belouh.DAO.DAO;
import be.belouh.POJO.TypeCours;

public class ListeTypeCoursS {
	private ArrayList<TypeCours> liste = new ArrayList<TypeCours>();

	private static ListeTypeCoursS instance = null;

	private ListeTypeCoursS() {
		DAO<TypeCours> m = new TypeCoursDAO();
		ArrayList<Integer> id = m.compter();
		Iterator<Integer> it = id.iterator();

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
	}

	public static ListeTypeCoursS getInstance() {
		if (instance == null)
			instance = new ListeTypeCoursS();
		return instance;
	}

	public ArrayList<TypeCours> getListe() {
		return liste;
	}
}
