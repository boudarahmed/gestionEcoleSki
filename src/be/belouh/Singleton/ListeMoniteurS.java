package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.DAO;
import be.belouh.DAO.MoniteurDAO;
import be.belouh.POJO.Cours;
import be.belouh.POJO.Moniteur;

public class ListeMoniteurS {
	private ArrayList<Moniteur> liste = new ArrayList<Moniteur>();

	private static ListeMoniteurS instance = null;

	private ListeMoniteurS() {
		// On récupere tous les moniteurs de la base de données et on rempli
		// leur liste de cours grace au singleton liste de cours
		DAO<Moniteur> m = new MoniteurDAO();
		ArrayList<Integer> id = m.compter();
		ListeCoursS listeCours = ListeCoursS.getInstance();
		Iterator<Integer> it = id.iterator();
		Iterator<Moniteur> itM;

		while (it.hasNext())
			liste.add(m.trouver(it.next()));

		itM = liste.iterator();
		while (itM.hasNext()) {
			Moniteur moniteur = itM.next();
			for (Cours cours : listeCours.getListe()) {
				if (cours.getMoniteur().equals(moniteur))
					moniteur.getListeCours().add(cours);
			}
		}

	}

	public Moniteur ajouterMoniteur(Moniteur m) {
		DAO<Moniteur> mo = new MoniteurDAO();
		boolean flag = false;
		for (Moniteur moniteur : liste) {
			if (moniteur.equals(m))
				flag = true;
		}
		if (!flag) {
			m = mo.inserer(m);
			liste.add(m);
			return m;
		} else
			return null;
	}

	public Moniteur mettreAjourMoniteur(Moniteur m) {
		DAO<Moniteur> mo = new MoniteurDAO();
		m = mo.mettreAJour(m);
		return m;
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
