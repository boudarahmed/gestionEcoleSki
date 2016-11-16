package be.belouh.Singleton;

import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.DAO.ClientDAO;
import be.belouh.DAO.DAO;
import be.belouh.POJO.Client;
import be.belouh.POJO.Eleve;
import be.belouh.POJO.Reservation;

public class ListeClientS {
	private ArrayList<Client> liste = new ArrayList<Client>();

	private static ListeClientS instance = null;

	private ListeClientS() {
		//On récupere tous les clients de la base de données et on rempli leur liste de reservation grace au singleton liste de reservation et leurs liste d'eleve grace au singleton eleve
		DAO<Client> m = new ClientDAO();
		ArrayList<Integer> id = m.compter();
		ListeReservationS listeReservation = ListeReservationS.getInstance();
		ListeEleveS listeEleve = ListeEleveS.getInstance();
		Iterator<Integer> it = id.iterator();
		Iterator<Client> itC;

		while (it.hasNext())
			liste.add(m.trouver(it.next()));
		
		itC = liste.iterator();
		while(itC.hasNext()){
			Client client = itC.next();
			for (Reservation reservation : listeReservation.getListe()) {
				if(reservation.getClient().equals(client))
					client.getListeReservation().add(reservation);
			}
			for (Eleve eleve : listeEleve.getListe()) {
				if(eleve.getClient().equals(client))
					client.getListeEleve().add(eleve);
			}
		}
	}

	public static ListeClientS getInstance() {
		if (instance == null)
			instance = new ListeClientS();
		return instance;
	}
	
	public Client ajouterClient(Client c){
		DAO<Client> cl = new ClientDAO();
		boolean flag = false;
		for (Client client : liste) {
			if(client.getAdresseMail().equals(c.getAdresseMail()))
				flag = true;
		}
		if(!flag){
			c = cl.inserer(c);
			liste.add(c);
			return c;	
		}
		else
			return null;
	}
	
	public ArrayList<Client> getListe() {
		return liste;
	}
}
