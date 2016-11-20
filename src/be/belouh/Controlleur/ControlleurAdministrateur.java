package be.belouh.Controlleur;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ListSelectionModel;
import javax.swing.text.JTextComponent;

import be.belouh.Modele.AdministrateurM;
import be.belouh.POJO.Accreditation;
import be.belouh.POJO.Cours;
import be.belouh.POJO.CoursCollectif;
import be.belouh.POJO.CoursParticulier;
import be.belouh.POJO.Eleve;
import be.belouh.POJO.Moniteur;
import be.belouh.POJO.Semaine;
import be.belouh.Singleton.ListeCoursS;
import be.belouh.Singleton.ListeMoniteurS;
import be.belouh.Vue.EcranAdministrateur;
import be.belouh.Vue.FenetreModaleInscriptionMoniteur;

public class ControlleurAdministrateur {
	private EcranAdministrateur vue;
	private AdministrateurM modele;
	private FenetreModaleInscriptionMoniteur modaleMoniteur;
	private String statutListe = "Afficher la liste des moniteurs";
	private Object[][] data;
	private String champCoursCollectif[] = { "Sport", "Niveau", "Catégorie d'élève", "Heure début", "Heure fin",
			"Nombre d'élève", "Statut" };
	private String champCoursParticulier[] = { "Sport", "Catégorie d'élève", "Heure début", "Heure fin",
			"Nombre d'élève", "Date", "Statut" };
	private String champMoniteur[] = { "État civil", "Nom", "Prénom", "Age", "Salaire horaire" };

	// AJOUT MONITEUR
	private String[] tabText = { "Nom", "Prénom", "Email", "Numéro", "Rue", "Code postal", "Ville", "Salaire horaire",
			"Confirmer mot de passe", "Mot de passe" };
	private ArrayList<String> texts = new ArrayList<String>(Arrays.asList(tabText));
	private String text;

	public ControlleurAdministrateur(AdministrateurM a) {
		vue = new EcranAdministrateur();
		modele = a;

		miseAjourData();

		vue.addmenu1Item1Listener(e -> {
			if (vue.demande("Voulez-vous vous déconnecter?")) {
				vue.dispose();
				new ControlleurConnexion();
			}
		});

		vue.addmenu2Item1Listener(e -> {
			statutListe = "Afficher la liste des moniteurs";
			miseAjourData();
		});

		vue.addmenu2Item2Listener(e -> {
			modaleMoniteur = new FenetreModaleInscriptionMoniteur(vue, "Ajouter un moniteur");
			AjoutEventModaleInscriptionMoniteur();
			modaleMoniteur.setVisible(true);
		});

		vue.addmenu2Item3Listener(e -> {
			
		});

		vue.addmenu2Item4Listener(e -> {

		});

		vue.addmenu3Item1Listener(e -> {
			statutListe = "Afficher les cours collectifs";
			miseAjourData();
		});

		vue.addmenu3Item2Listener(e -> {
			statutListe = "Afficher les cours particuliers";
			miseAjourData();
		});

		vue.addListeListener(e -> {
			if (!e.getValueIsAdjusting()) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (!lsm.isSelectionEmpty()) {
					int selectedRow = lsm.getMinSelectionIndex();
					StringBuilder msg = new StringBuilder("");
					if (statutListe.equals("Afficher la liste des moniteurs")) {
						Moniteur m = modele.getMoniteur(data[selectedRow]);
						msg.append("Moniteur " + m.getNom() + " " + m.getPrenom() + " né(e) le "
								+ new SimpleDateFormat("dd/MM/yyyy").format(m.getDateNaissance()) + "\n");
						msg.append("Adresse : " + m.getNumero() + " " + m.getRue() + " " + m.getCodePostal() + " "
								+ m.getVille() + "\n");
						msg.append("Email : " + m.getAdresseMail() + "\n\n");
						msg.append("Accrédiations : \n");
						for (Accreditation accreditation : m.getListeAccreditation()) {
							msg.append("- " + accreditation.toString() + "\n");
						}
						msg.append("\nIndisponibilitées : \n");
						for (Semaine semaine : m.getListeIndisponibilitee()) {
							msg.append("- " + semaine.toString() + "\n");
						}
						vue.afficheMessage(msg.toString(), "Fiche du moniteur", JOptionPane.PLAIN_MESSAGE);
					} else {
						Cours c = modele.getCours(data[selectedRow]);
						for (Eleve eleve : c.getListeEleve()) {
							msg.append("- " + eleve.toString());
						}
						vue.afficheMessage(msg.toString(), "Liste d'élève de ce cours", JOptionPane.PLAIN_MESSAGE);
					}

					lsm.clearSelection();
				}
			}
		});
	}

	// AJOUT EVENT MODALE MONITEUR
	public void AjoutEventModaleInscriptionMoniteur() {
		modaleMoniteur.addbuttonValiderListener(e -> {
			String verif = validationFormulaire();
			if (!verif.equals(""))
				vue.afficheMessage(verif, "Erreur", JOptionPane.ERROR_MESSAGE);
			else {
				Moniteur moniteur = new Moniteur();
				moniteur.setNom(modaleMoniteur.getNom());
				moniteur.setPrenom(modaleMoniteur.getPrenom());
				moniteur.setAdresseMail(modaleMoniteur.getAdresseMail());
				moniteur.setMotDePasse(modaleMoniteur.getMotDePasse());
				moniteur.setNumero(modaleMoniteur.getNumero());
				moniteur.setRue(modaleMoniteur.getRue());
				moniteur.setCodePostal(modaleMoniteur.getCodePostal());
				moniteur.setVille(modaleMoniteur.getVille());
				moniteur.setSexe(modaleMoniteur.getSexe());
				moniteur.setDateNaissance(modaleMoniteur.getDateNaissance());
				moniteur.setSalaireHoraire(Double.parseDouble(modaleMoniteur.getSalaireHoraire()));
				moniteur.setCoursParticulier(modaleMoniteur.getCoursParticulier());
				if (modele.ajoutMoniteur(moniteur)) {
					modaleMoniteur.afficheMessage("Moniteur ajouté avec succès", "Information",
							JOptionPane.INFORMATION_MESSAGE);
					modaleMoniteur.dispose();
					miseAjourData();
				}
			}
		});

		modaleMoniteur.addbuttonAnnulerListener(e -> {
			modaleMoniteur.dispose();
		});

		modaleMoniteur.addFocusFieldListenr(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (((JTextComponent) e.getSource()).getText().equals("")) {
					((JTextComponent) e.getSource()).setText(text);
					((JTextComponent) e.getSource()).setForeground(Color.LIGHT_GRAY);
				}
				if (((JTextComponent) e.getSource()).getText().equals("Mot de passe")
						|| ((JTextComponent) e.getSource()).getText().equals("Confirmer mot de passe"))
					((JPasswordField) e.getSource()).setEchoChar((char) 0);
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (texts.contains(((JTextComponent) e.getSource()).getText())) {
					text = ((JTextComponent) e.getSource()).getText();
					((JTextComponent) e.getSource()).setText("");
					((JTextComponent) e.getSource()).setForeground(Color.BLACK);
				}
				if (text.equals("Mot de passe") || text.equals("Confirmer mot de passe"))
					((JPasswordField) e.getSource()).setEchoChar('*');
			}
		});
	}

	// VALIDATION FORMULAIRE
	public String validationFormulaire() {
		StringBuilder res = new StringBuilder("Le(s) champ(s) suivant(s) ne sont pas remplis : ");
		if (texts.contains(modaleMoniteur.getNom()))
			res.append("Nom, ");
		if (texts.contains(modaleMoniteur.getPrenom()))
			res.append("Prénom, ");
		if (texts.contains(modaleMoniteur.getAdresseMail()))
			res.append("Email, ");
		if (texts.contains(modaleMoniteur.getMotDePasse()))
			res.append("Mot de passe, ");
		if (texts.contains(modaleMoniteur.getConfirmMotDePasse()))
			res.append("Confirmer mot de passe, ");
		if (texts.contains(modaleMoniteur.getNumero()))
			res.append("Numéro, ");
		if (texts.contains(modaleMoniteur.getRue()))
			res.append("Rue, ");
		if (texts.contains(modaleMoniteur.getCodePostal()))
			res.append("Code postal, ");
		if (texts.contains(modaleMoniteur.getVille()))
			res.append("Ville, ");
		if (modaleMoniteur.getDateNaissance() == null)
			res.append("Date de naissance, ");
		if (texts.contains(modaleMoniteur.getSalaireHoraire()))
			res.append("Numéro de compte, ");
		if (res.toString().equals("Le(s) champ(s) suivant(s) ne sont pas remplis : "))
			if (!modaleMoniteur.getMotDePasse().equals(modaleMoniteur.getConfirmMotDePasse()))
				res.replace(0, res.length(), "Les mots de passe ne correspondent pas");
			else {
				try {
					Double.parseDouble(modaleMoniteur.getSalaireHoraire());
					res.replace(0, res.length(), "");
				} catch (NumberFormatException e) {
					res.replace(0, res.length(), "Rentrez un salaire sous cette forme : 12.00");
				}
			}
		else
			res = new StringBuilder(res.substring(0, res.length() - 2));
		return res.toString();
	}

	public void miseAjourData() {
		String label = "";
		switch (statutListe) {
		case "Afficher la liste des moniteurs":
			label = "Voici la liste des moniteurs";
			data = new Object[ListeMoniteurS.getInstance().getListe().size()][6];
			for (int i = 0; i < ListeMoniteurS.getInstance().getListe().size(); i++) {
				data[i][0] = (ListeMoniteurS.getInstance().getListe().get(i).getSexe() == 'M') ? "M." : "Mme";
				data[i][1] = ListeMoniteurS.getInstance().getListe().get(i).getNom();
				data[i][2] = ListeMoniteurS.getInstance().getListe().get(i).getPrenom();
				data[i][3] = Period
						.between(
								ListeMoniteurS.getInstance().getListe().get(i).getDateNaissance().toInstant()
										.atZone(ZoneId.systemDefault()).toLocalDate(),
								new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
						.getYears() + " ans";
				data[i][4] = ListeMoniteurS.getInstance().getListe().get(i).getSalaireHoraire() + "\u20AC/h";
				data[i][5] = ListeMoniteurS.getInstance().getListe().get(i).getId();
			}
			vue.afficherliste(data, champMoniteur, label);
			break;
		case "Afficher les cours collectifs":
			label = "Voici la liste des cours collectifs";
			ArrayList<Cours> listC = (ArrayList<Cours>) ListeCoursS.getInstance().getListe().stream()
					.filter(x -> x instanceof CoursCollectif).collect(Collectors.toList());
			data = new Object[listC.size()][8];
			for (int i = 0; i < listC.size(); i++) {
				data[i][0] = ((CoursCollectif) listC.get(i)).getTypeCours().getAccreditation().getSport();
				data[i][1] = ((CoursCollectif) listC.get(i)).getTypeCours().getNiveau();
				if (((CoursCollectif) listC.get(i)).getTypeCours().getAccreditation().getAgeMin() == 4
						|| ((CoursCollectif) listC.get(i)).getTypeCours().getAccreditation().getAgeMin() == 6)
					data[i][2] = "enfant";
				else
					data[i][2] = "adulte";
				data[i][3] = ((CoursCollectif) listC.get(i)).getHoraire().getHeureDeb() + "h";
				data[i][4] = ((CoursCollectif) listC.get(i)).getHoraire().getHeureFin() + "h";
				data[i][5] = ((CoursCollectif) listC.get(i)).getListeEleve().size();
				data[i][6] = ((CoursCollectif) listC.get(i)).getStatutCours();
				data[i][7] = ((CoursCollectif) listC.get(i)).getId();
			}
			vue.afficherliste(data, champCoursCollectif, label);
			break;
		case "Afficher les cours particuliers":
			label = "Voici la liste des cours particuliers";
			ArrayList<Cours> listP = (ArrayList<Cours>) ListeCoursS.getInstance().getListe().stream()
					.filter(x -> x instanceof CoursParticulier).collect(Collectors.toList());
			data = new Object[listP.size()][8];
			for (int i = 0; i < listP.size(); i++) {
				data[i][0] = ((CoursParticulier) listP.get(i)).getAccreditation().getSport();
				if (((CoursParticulier) listP.get(i)).getAccreditation().getAgeMin() == 4
						|| ((CoursParticulier) listP.get(i)).getAccreditation().getAgeMin() == 6)
					data[i][1] = "enfant";
				else
					data[i][1] = "adulte";
				data[i][2] = ((CoursParticulier) listP.get(i)).getHoraire().getHeureDeb() + "h";
				data[i][3] = ((CoursParticulier) listP.get(i)).getHoraire().getHeureFin() + "h";
				data[i][4] = ((CoursParticulier) listP.get(i)).getListeEleve().size();
				data[i][5] = new SimpleDateFormat("dd/MM/yyyy").format(((CoursParticulier) listP.get(i)).getDate());
				data[i][6] = ((CoursParticulier) listP.get(i)).getStatutCours();
				data[i][7] = ((CoursParticulier) listP.get(i)).getId();
			}
			vue.afficherliste(data, champCoursParticulier, label);
			break;
		default:
			break;
		}
	}
}
