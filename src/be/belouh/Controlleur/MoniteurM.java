package be.belouh.Controlleur;

import be.belouh.Modele.UtilisateurM;
import be.belouh.POJO.Moniteur;
import be.belouh.Singleton.ListeMoniteurS;

public class MoniteurM extends UtilisateurM {
	public MoniteurM() {
		utilisateur = new Moniteur();
	}

	@Override
	public boolean connexion() {
		ListeMoniteurS liste = ListeMoniteurS.getInstance();
		int index = liste.getListe().indexOf(this.utilisateur);

		if (index == -1)
			return false;
		else {
			this.utilisateur = liste.getListe().get(index);
			return true;
		}
	}

}
