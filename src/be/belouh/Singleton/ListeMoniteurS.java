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
		//On r�cupere tous les moniteurs de la base de donn�es et on rempli leur liste de cours grace au singleton liste de cours
		DAO<Moniteur> m = new MoniteurDAO();
		ArrayList<Integer> id = m.compter();
		ListeCoursS listeCours = ListeCoursS.getInstance();
		Iterator<Integer> it = id.iterator();
		Iterator<Moniteur> itM;

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
		
		itM = liste.iterator();
		while(itM.hasNext()){
			Moniteur moniteur = itM.next();
			for (Cours cours : listeCours.getListe()) {
				if(cours.getMoniteur().equals(moniteur))
					moniteur.getListeCours().add(cours);
			}
		}
			
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
