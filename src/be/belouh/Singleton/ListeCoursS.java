package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.CoursCollectifDAO;
import be.belouh.DAO.CoursParticulierDAO;
import be.belouh.DAO.DAO;
import be.belouh.POJO.Cours;
import be.belouh.POJO.CoursCollectif;
import be.belouh.POJO.CoursParticulier;
import be.belouh.POJO.Reservation;

public class ListeCoursS {
	private ArrayList<Cours> liste = new ArrayList<Cours>();

	private static ListeCoursS instance = null;

	private ListeCoursS() {
		// On récupere tous les cours de la base de données et on rempli leur
		// liste d'eleve grace au singleton liste de reservation
		DAO<CoursCollectif> m = new CoursCollectifDAO();
		DAO<CoursParticulier> c = new CoursParticulierDAO();
		ArrayList<Integer> id = m.compter();
		ListeReservationS listeReservation = ListeReservationS.getInstance();
		Iterator<Integer> it = id.iterator();
		Iterator<Cours> itC;

		while (it.hasNext())
			liste.add(m.trouver(it.next()));

		id = c.compter();
		it = id.iterator();

		while (it.hasNext())
			liste.add(c.trouver(it.next()));

		itC = liste.iterator();
		while (itC.hasNext()) {
			Cours cours = itC.next();
			for (Reservation reservation : listeReservation.getListe()) {
				if (reservation.getListeCours().contains(cours))
					cours.getListeEleve().add(reservation.getEleve());
			}
		}
	}

	public static ListeCoursS getInstance() {
		if (instance == null)
			instance = new ListeCoursS();
		return instance;
	}

	public Cours ajouterCours(Cours c) {
		if (c instanceof CoursCollectif) {
			DAO<CoursCollectif> cC = new CoursCollectifDAO();
			c = cC.inserer((CoursCollectif) c);
			liste.add(c);
			return c;
		} else {
			DAO<CoursParticulier> cP = new CoursParticulierDAO();
			c = cP.inserer((CoursParticulier) c);
			liste.add(c);
			return c;
		}
	}

	public Cours mettreAjourCours(Cours c) {
		if (c instanceof CoursCollectif) {
			DAO<CoursCollectif> cC = new CoursCollectifDAO();
			cC.mettreAJour((CoursCollectif) c);
			return c;
		} else {
			DAO<CoursParticulier> cP = new CoursParticulierDAO();
			c = cP.mettreAJour((CoursParticulier) c);
			return c;
		}
	}

	public ArrayList<Cours> getListe() {
		return liste;
	}
}
