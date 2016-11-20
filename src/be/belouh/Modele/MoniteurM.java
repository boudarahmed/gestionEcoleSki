package be.belouh.Modele;

import java.util.ArrayList;
import java.util.stream.Collectors;

import be.belouh.POJO.Cours;
import be.belouh.POJO.CoursCollectif;
import be.belouh.POJO.CoursParticulier;
import be.belouh.POJO.Moniteur;
import be.belouh.POJO.Semaine;
import be.belouh.Singleton.ListeMoniteurS;

public class MoniteurM extends UtilisateurM {
	public MoniteurM() {
		utilisateur = new Moniteur();
	}

	public ArrayList<Cours> triCours(boolean coursCollectif, Semaine s) {
		ArrayList<Cours> liste = null;
		Moniteur m = (Moniteur) getUtilisateur();

		if (coursCollectif) {
			liste = (ArrayList<Cours>) m.getListeCours().stream()
					.filter(x -> x instanceof CoursCollectif && ((CoursCollectif) x).getSemaine().equals(s))
					.collect(Collectors.toList());
		} else {
			liste = (ArrayList<Cours>) m.getListeCours().stream()
					.filter(x -> x instanceof CoursParticulier && ((CoursParticulier) x).getDate().after(s.getDateDeb())
							&& ((CoursParticulier) x).getDate().before(s.getDateFin()))
					.collect(Collectors.toList());
		}

		return liste;
	}

	public Cours getCours(Object[] data) {
		ArrayList<Cours> liste = ((Moniteur) getUtilisateur()).getListeCours();
		return liste.stream().filter(x -> x.getId() == (Integer) data[6]).findAny().orElse(null);
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