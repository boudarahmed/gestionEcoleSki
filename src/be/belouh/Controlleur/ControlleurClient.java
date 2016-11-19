package be.belouh.Controlleur;

import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.belouh.Modele.ClientM;
import be.belouh.POJO.Eleve;
import be.belouh.POJO.Reservation;
import be.belouh.Vue.EcranClient;

public class ControlleurClient {
	private EcranClient vue;
	private ClientM modele;
	private String StatutCourant = Reservation.COMMANDER;
	private Eleve eleveCourant;
	private String champReservation[] = { "Client", "Date r�servation", "Prix" };

	public ControlleurClient(ClientM c) {
		vue = new EcranClient();
		modele = c;

		eleveCourant = modele.getListeEleve().get(0);

		for (Eleve eleve : modele.getListeEleve()) {
			vue.getComboEleve().addItem(eleve);
		}
		
		miseAjourData();

		vue.addmenu1Item1Listener(e -> {
			vue.dispose();
			new ControlleurConnexion();
		});

		vue.addmenu2Item1Listener(e -> {
			// R�servation
		});

		vue.addmenu3Item1Listener(e -> {
			// Ajout �l�ve
		});

		vue.addmenu3Item2Listener(e -> {
			StringBuilder msg = new StringBuilder("");
			for (Eleve eleve : modele.getListeEleve()) {
				msg.append(eleve.toString());
			}
			vue.afficheMessage(msg.toString(), "Votre liste d'�l�ves", JOptionPane.PLAIN_MESSAGE);
		});

		vue.addComboEleveListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				eleveCourant = (Eleve) e.getItem();
				miseAjourData();
			}
		});

		vue.addbuttonRadioListener(e -> {
			switch (vue.getRadioStatut()) {
			case "R�servations command�es":
				StatutCourant = Reservation.COMMANDER;
				miseAjourData();
				break;
			case "R�servations r�serv�es":
				StatutCourant = Reservation.RESERVER;
				miseAjourData();
				break;
			case "R�servations pay�es":
				StatutCourant = Reservation.PAYER;
				miseAjourData();
				break;
			default:
				break;
			}
		});

		vue.addListeListener(e -> {
			// Gestion d'un clique sur la liste
		});
	}

	public void miseAjourData() {
		ArrayList<Reservation> liste = modele.triReservation(StatutCourant, eleveCourant);
		String label = "Voici la liste de " + vue.getRadioStatut().toLowerCase() + " correspondant � l'�l�ve " + eleveCourant;
		Object[][] data = new Object[liste.size()][3];
		for(int i = 0; i < liste.size(); i++){
			data[i][0] = liste.get(i).getClient().getNom() + " " + liste.get(i).getClient().getPrenom();
			data[i][1] = new SimpleDateFormat("dd/MM/yyyy").format(liste.get(i).getDateReservation());
			data[i][2] = liste.get(i).getPrix() + "\u20AC";
		}
		vue.afficherliste(data, champReservation, label);
	}
}
