package be.belouh.Modele;

import java.util.ArrayList;

import be.belouh.POJO.Administrateur;
import be.belouh.POJO.Cours;
import be.belouh.POJO.Moniteur;
import be.belouh.Singleton.ListeAdministrateurS;
import be.belouh.Singleton.ListeCoursS;
import be.belouh.Singleton.ListeMoniteurS;

public class AdministrateurM extends UtilisateurM {
	public AdministrateurM() {
		utilisateur = new Administrateur();
	}

	public Cours getCours(Object[] data) {
		ArrayList<Cours> liste = ListeCoursS.getInstance().getListe();
		return liste.stream().filter(x -> x.getId() == (Integer) data[7]).findAny().orElse(null);
	}

	public Moniteur getMoniteur(Object[] data) {
		ArrayList<Moniteur> liste = ListeMoniteurS.getInstance().getListe();
		return liste.stream().filter(x -> x.getId() == (Integer) data[5]).findAny().orElse(null);
	}

	public boolean ajoutMoniteur(Moniteur m) {
		ListeMoniteurS liste = ListeMoniteurS.getInstance();
		m = liste.ajouterMoniteur(m);

		if (m == null)
			return false;
		else {
			return true;
		}
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
