package be.belouh.Modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	public Cours getCours(Object[] data, boolean coursCollectif, Semaine s) {
		Cours res = null;
		ArrayList<Cours> liste = ((Moniteur) getUtilisateur()).getListeCours();
		int max, heureDeb, heureFin;
		if (coursCollectif) {
			max = (((String) data[2]).equals("adulte")) ? 0 : 12;
			heureDeb = Integer.parseInt(((String) data[3]).substring(0, ((String) data[3]).length() - 1));
			heureFin = Integer.parseInt(((String) data[4]).substring(0, ((String) data[4]).length() - 1));
			res = liste.stream().filter(x -> x instanceof CoursCollectif
					&& ((CoursCollectif) x).getTypeCours().getAccreditation().getSport().equals((String) data[0])
					&& ((CoursCollectif) x).getTypeCours().getNiveau().equals((String) data[1])
					&& ((CoursCollectif) x).getTypeCours().getAccreditation().getAgeMax() == max
					&& ((CoursCollectif) x).getHoraire().getHeureDeb() == heureDeb
					&& ((CoursCollectif) x).getHoraire().getHeureFin() == heureFin
					&& ((CoursCollectif) x).getListeEleve().size() == (Integer) data[5]).findAny().orElse(null);
		} else {
			max = (((String) data[1]).equals("adulte")) ? 0 : 12;
			heureDeb = Integer.parseInt(((String) data[2]).substring(0, ((String) data[2]).length() - 1));
			heureFin = Integer.parseInt(((String) data[3]).substring(0, ((String) data[3]).length() - 1));
			Date d = new Date();
			try {
				d = new SimpleDateFormat("dd/MM/yyyy").parse((String) data[5]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			final Date date = d;
			res = liste.stream()
					.filter(x -> x instanceof CoursParticulier
							&& ((CoursParticulier) x).getAccreditation().getSport().equals((String) data[0])
							&& ((CoursParticulier) x).getAccreditation().getAgeMax() == max
							&& ((CoursParticulier) x).getHoraire().getHeureDeb() == heureDeb
							&& ((CoursParticulier) x).getHoraire().getHeureFin() == heureFin
							&& ((CoursParticulier) x).getListeEleve().size() == (Integer) data[4]
							&& ((CoursParticulier) x).getDate().equals(date))
					.findAny().orElse(null);
		}
		return res;
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