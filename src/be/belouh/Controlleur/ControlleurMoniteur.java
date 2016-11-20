package be.belouh.Controlleur;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import be.belouh.Modele.MoniteurM;
import be.belouh.POJO.Cours;
import be.belouh.POJO.CoursCollectif;
import be.belouh.POJO.CoursParticulier;
import be.belouh.POJO.Eleve;
import be.belouh.POJO.Semaine;
import be.belouh.Singleton.ListeSemaineS;
import be.belouh.Vue.EcranMoniteur;

public class ControlleurMoniteur {
	private EcranMoniteur vue;
	private MoniteurM modele;
	private boolean coursCollectif = true;
	private Object[][] data;
	private Semaine semaineCourante = ListeSemaineS.getInstance().getListe().get(0);
	private String champCoursCollectif[] = { "Sport", "Niveau", "Catégorie d'élève", "Heure début", "Heure fin",
			"Nombre d'élève" };
	private String champCoursParticulier[] = { "Sport", "Catégorie d'élève", "Heure début", "Heure fin",
			"Nombre d'élève", "Date" };

	public ControlleurMoniteur(MoniteurM m) {
		vue = new EcranMoniteur();
		modele = m;

		miseAjourData();

		vue.addmenu1Item1Listener(e -> {
			if (vue.demande("Voulez-vous vous déconnecter?")) {
				vue.dispose();
				new ControlleurConnexion();
			}
		});

		vue.addmenu2Item1Listener(e -> {
			coursCollectif = true;
			miseAjourData();
		});

		vue.addmenu2Item2Listener(e -> {
			coursCollectif = false;
			miseAjourData();
		});

		vue.addmenu3Item1Listener(e -> {
			semaineCourante = (Semaine) vue.Chosis(ListeSemaineS.getInstance().getListe().toArray(), "Semaine du",
					"Choisir une semaine");
			miseAjourData();
		});

		vue.addListeListener(e -> {
			if (!e.getValueIsAdjusting()) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (!lsm.isSelectionEmpty()) {
					int selectedRow = lsm.getMinSelectionIndex();
					StringBuilder msg = new StringBuilder("");
					Cours c = modele.getCours(data[selectedRow]);
					for (Eleve eleve : c.getListeEleve()) {
						msg.append("- " + eleve.toString());
					}
					vue.afficheMessage(msg.toString(), "Liste d'élève de ce cours", JOptionPane.PLAIN_MESSAGE);
					lsm.clearSelection();
				}
			}
		});
	}

	public void miseAjourData() {
		if (semaineCourante != null) {
			String label;
			ArrayList<Cours> liste;
			if (coursCollectif) {
				label = "Voici votre liste de cours collectifs (" + semaineCourante.toString() + ")";
				liste = modele.triCours(coursCollectif, semaineCourante);

				data = new Object[liste.size()][7];

				for (int i = 0; i < liste.size(); i++) {
					data[i][0] = ((CoursCollectif) liste.get(i)).getTypeCours().getAccreditation().getSport();
					data[i][1] = ((CoursCollectif) liste.get(i)).getTypeCours().getNiveau();
					if (((CoursCollectif) liste.get(i)).getTypeCours().getAccreditation().getAgeMin() == 4
							|| ((CoursCollectif) liste.get(i)).getTypeCours().getAccreditation().getAgeMin() == 6)
						data[i][2] = "enfant";
					else
						data[i][2] = "adulte";
					data[i][3] = ((CoursCollectif) liste.get(i)).getHoraire().getHeureDeb() + "h";
					data[i][4] = ((CoursCollectif) liste.get(i)).getHoraire().getHeureFin() + "h";
					data[i][5] = ((CoursCollectif) liste.get(i)).getListeEleve().size();
					data[i][6] = ((CoursCollectif) liste.get(i)).getId();
				}
				vue.afficherliste(data, champCoursCollectif, label);
			} else {
				label = "Voici votre liste de cours particuliers (" + semaineCourante.toString() + ")";
				liste = modele.triCours(coursCollectif, semaineCourante);

				data = new Object[liste.size()][7];

				for (int i = 0; i < liste.size(); i++) {
					data[i][0] = ((CoursParticulier) liste.get(i)).getAccreditation().getSport();
					if (((CoursParticulier) liste.get(i)).getAccreditation().getAgeMin() == 4
							|| ((CoursParticulier) liste.get(i)).getAccreditation().getAgeMin() == 6)
						data[i][1] = "enfant";
					else
						data[i][1] = "adulte";
					data[i][2] = ((CoursParticulier) liste.get(i)).getHoraire().getHeureDeb() + "h";
					data[i][3] = ((CoursParticulier) liste.get(i)).getHoraire().getHeureFin() + "h";
					data[i][4] = ((CoursParticulier) liste.get(i)).getListeEleve().size();
					data[i][5] = new SimpleDateFormat("dd/MM/yyyy").format(((CoursParticulier) liste.get(i)).getDate());
					data[i][6] = ((CoursParticulier) liste.get(i)).getId();
				}
				vue.afficherliste(data, champCoursParticulier, label);
			}
		}
	}
}
