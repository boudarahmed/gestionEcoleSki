package be.belouh.Modele;

import be.belouh.POJO.Administrateur;
import be.belouh.Singleton.ListeAdministrateurS;

public class AdministrateurM extends UtilisateurM {
	public AdministrateurM() {
		utilisateur = new Administrateur();
	}

	@Override
	public boolean connexion() {
		ListeAdministrateurS liste = ListeAdministrateurS.getInstance();
		int index = liste.getListe().indexOf(this.utilisateur);

		if (index == -1)
			return false;
		else {
			this.utilisateur = liste.getListe().get(index);
			return true;
		}
	}

}
