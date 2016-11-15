package be.belouh.Modele;

import be.belouh.POJO.Client;
import be.belouh.Singleton.ListeClientS;

public class ClientM extends UtilisateurM {
	public ClientM() {
		utilisateur = new Client();
	}

	@Override
	public boolean connexion() {
		ListeClientS liste = ListeClientS.getInstance();
		int index = liste.getListe().indexOf(this.utilisateur);

		if (index == -1)
			return false;
		else {
			this.utilisateur = liste.getListe().get(index);
			return true;
		}
	}

}
