package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.DAO;
import be.belouh.DAO.EleveDAO;
import be.belouh.POJO.Eleve;
import be.belouh.POJO.Reservation;

public class ListeEleveS {
	private ArrayList<Eleve> liste = new ArrayList<Eleve>();

	private static ListeEleveS instance = null;

	private ListeEleveS() {
		//On récupere tous les eleves de la base de données et on rempli leur liste de reservation grace au singleton liste de reservation
		DAO<Eleve> m = new EleveDAO();
		ArrayList<Integer> id = m.compter();
		ListeReservationS listeReservation = ListeReservationS.getInstance();
		Iterator<Integer> it = id.iterator();
		Iterator<Eleve> itE;

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
		
		itE = liste.iterator();
		while(itE.hasNext()){
			Eleve eleve = itE.next();
			for (Reservation reservation : listeReservation.getListe()) {
				if(reservation.getEleve().equals(eleve))
					eleve.getListeReservation().add(reservation);
			}
		}
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
