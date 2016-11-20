package be.belouh.Controlleur;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.JTextComponent;

import be.belouh.Modele.ClientM;
import be.belouh.POJO.Accreditation;
import be.belouh.POJO.Client;
import be.belouh.POJO.Cours;
import be.belouh.POJO.CoursCollectif;
import be.belouh.POJO.CoursParticulier;
import be.belouh.POJO.Eleve;
import be.belouh.POJO.Horaire;
import be.belouh.POJO.Moniteur;
import be.belouh.POJO.Reservation;
import be.belouh.POJO.Semaine;
import be.belouh.POJO.TypeCours;
import be.belouh.Singleton.ListeAccreditationS;
import be.belouh.Singleton.ListeCoursS;
import be.belouh.Singleton.ListeHoraireS;
import be.belouh.Singleton.ListeMoniteurS;
import be.belouh.Singleton.ListeReservationS;
import be.belouh.Singleton.ListeSemaineS;
import be.belouh.Singleton.ListeTypeCoursS;
import be.belouh.Vue.EcranClient;
import be.belouh.Vue.FenetreModaleInscriptionEleve;
import be.belouh.Vue.FenetreModaleReservation;

public class ControlleurClient {
	private EcranClient vue;
	private ClientM modele;
	private String StatutCourant = Reservation.COMMANDER;
	private Eleve eleveCourant;
	private String champReservation[] = { "Client", "Date réservation", "Prix" };
	private Object[][] data;

	// AJOUT ELEVE
	private FenetreModaleInscriptionEleve modaleEleve;
	private String text;
	private String[] tabText = { "Nom", "Prénom", "Email", "Numéro", "Rue", "Code postal", "Ville",
			"Confirmer mot de passe", "Mot de passe" };
	private ArrayList<String> texts = new ArrayList<String>(Arrays.asList(tabText));

	// AJOUT RESERVATION
	private FenetreModaleReservation modaleCours;
	private String[] choixCours = { "Cours collectif", "Cours particulier" };

	public ControlleurClient(ClientM c) {
		vue = new EcranClient();
		modele = c;

		if (modele.getListeEleve().size() >= 1) {
			eleveCourant = modele.getListeEleve().get(0);

			for (Eleve eleve : modele.getListeEleve()) {
				vue.getComboEleve().addItem(eleve);
			}

			miseAjourData();
		}

		vue.addmenu1Item1Listener(e -> {
			if (vue.demande("Voulez-vous vous déconnecter?")) {
				vue.dispose();
				new ControlleurConnexion();
			}
		});

		vue.addmenu2Item1Listener(e -> {
			effectuerReservation();
			miseAjourData();
		});

		vue.addmenu3Item1Listener(e -> {
			modaleEleve = new FenetreModaleInscriptionEleve(vue, "Ajouter un élève");
			AjoutEventModaleInscriptionEleve();
			modaleEleve.setVisible(true);
		});

		vue.addmenu3Item2Listener(e -> {
			if (modele.getListeEleve().size() >= 1) {
				StringBuilder msg = new StringBuilder("");
				for (Eleve eleve : modele.getListeEleve()) {
					msg.append("- " + eleve.toString());
				}
				vue.afficheMessage(msg.toString(), "Votre liste d'élèves", JOptionPane.PLAIN_MESSAGE);
			} else {
				vue.afficheMessage("Vous n'avez encore aucun élève", "Votre liste d'élèves", JOptionPane.PLAIN_MESSAGE);
			}
		});

		vue.addComboEleveListener(e -> {
			if (modele.getListeEleve().size() >= 1) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					eleveCourant = (Eleve) e.getItem();
					miseAjourData();
				}
			}
		});

		vue.addbuttonRadioListener(e -> {
			if (modele.getListeEleve().size() >= 1) {
				switch (vue.getRadioStatut()) {
				case "Réservations commandées":
					StatutCourant = Reservation.COMMANDER;
					miseAjourData();
					break;
				case "Réservations réservées":
					StatutCourant = Reservation.RESERVER;
					miseAjourData();
					break;
				case "Réservations payées":
					StatutCourant = Reservation.PAYER;
					miseAjourData();
					break;
				default:
					break;
				}
			}
		});

		vue.addListeListener(e -> {
			if (!e.getValueIsAdjusting()) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (!lsm.isSelectionEmpty()) {
					int selectedRow = lsm.getMinSelectionIndex();
					StringBuilder msg = new StringBuilder("");
					Reservation r = modele.getReservation(data[selectedRow]);
					if (r.getStatutReservation().equals(Reservation.RESERVER)) {
						if (vue.demande("Voulez-vous payer cette réservation?")) {
							r.setStatutReservation(Reservation.PAYER);
							modele.miseAjourReservation(r);
							vue.afficheMessage("Votre payement a bien été pris en compte", "Merci",
									JOptionPane.INFORMATION_MESSAGE);
							miseAjourData();
						}
					} else {
						for (Cours cours : r.getListeCours()) {
							msg.append(cours.toString());
						}
						vue.afficheMessage(msg.toString(), "Liste de cours concernant cette réservation",
								JOptionPane.PLAIN_MESSAGE);
					}
					lsm.clearSelection();
				}
			}
		});

	}

	// EVENT MODALE AJOUT ELEVE
	public void AjoutEventModaleInscriptionEleve() {
		modaleEleve.addbuttonValiderListener(e -> {
			String verif = validationFormulaire();
			if (!verif.equals(""))
				vue.afficheMessage(verif, "Erreur", JOptionPane.ERROR_MESSAGE);
			else {
				Eleve eleve = new Eleve();
				eleve.setNom(modaleEleve.getNom());
				eleve.setPrenom(modaleEleve.getPrenom());
				eleve.setDateNaissance(modaleEleve.getDateNaissance());
				eleve.setNumero(modaleEleve.getNumero());
				eleve.setRue(modaleEleve.getRue());
				eleve.setCodePostal(modaleEleve.getCodePostal());
				eleve.setVille(modaleEleve.getVille());
				eleve.setSexe(modaleEleve.getSexe());
				eleve.setAssurance(modaleEleve.getAssurance());
				eleve.setClient((Client) modele.getUtilisateur());
				if (modele.ajoutEleve(eleve)) {
					modaleEleve.afficheMessage("Élève ajouter à votre liste", "Information",
							JOptionPane.INFORMATION_MESSAGE);
					modaleEleve.dispose();
					vue.getComboEleve().addItem(eleve);
				}
			}
		});

		modaleEleve.addbuttonAnnulerListener(e -> {
			modaleEleve.dispose();
		});

		modaleEleve.addFocusFieldListenr(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (((JTextComponent) e.getSource()).getText().equals("")) {
					((JTextComponent) e.getSource()).setText(text);
					((JTextComponent) e.getSource()).setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (texts.contains(((JTextComponent) e.getSource()).getText())) {
					text = ((JTextComponent) e.getSource()).getText();
					((JTextComponent) e.getSource()).setText("");
					((JTextComponent) e.getSource()).setForeground(Color.BLACK);
				}
			}
		});
	}

	// VALIDATION FORMULAIRE AJOUT ELEVE
	public String validationFormulaire() {
		StringBuilder res = new StringBuilder("Le(s) champ(s) suivant(s) ne sont pas remplis : ");
		if (texts.contains(modaleEleve.getNom()))
			res.append("Nom, ");
		if (texts.contains(modaleEleve.getPrenom()))
			res.append("Prénom, ");
		if (texts.contains(modaleEleve.getNumero()))
			res.append("Numéro, ");
		if (texts.contains(modaleEleve.getRue()))
			res.append("Rue, ");
		if (texts.contains(modaleEleve.getCodePostal()))
			res.append("Code postal, ");
		if (texts.contains(modaleEleve.getVille()))
			res.append("Ville, ");
		if (modaleEleve.getDateNaissance() == null)
			res.append("Date de naissance, ");
		if (res.toString().equals("Le(s) champ(s) suivant(s) ne sont pas remplis : "))
			res.replace(0, res.length(), "");
		else
			res = new StringBuilder(res.substring(0, res.length() - 2));
		return res.toString();
	}

	// METHODE ECRAN MONITEUR
	public void miseAjourData() {
		ArrayList<Reservation> liste = modele.triReservation(StatutCourant, eleveCourant);
		String label = "Voici la liste de " + vue.getRadioStatut().toLowerCase() + " correspondant à l'élève "
				+ eleveCourant;
		data = new Object[liste.size()][4];
		for (int i = 0; i < liste.size(); i++) {
			data[i][0] = liste.get(i).getClient().getNom() + " " + liste.get(i).getClient().getPrenom();
			data[i][1] = new SimpleDateFormat("dd/MM/yyyy").format(liste.get(i).getDateReservation());
			data[i][2] = liste.get(i).getPrix() + "\u20AC";
			data[i][3] = liste.get(i).getId();
		}
		vue.afficherliste(data, champReservation, label);
	}

	// EVENT MODALE AJOUT COURS
	public void AjoutEventModaleCours(Eleve eleve, boolean coursCollectif, Reservation reservation) {
		if (coursCollectif) {
			modaleCours.addComboSportListener(e -> {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String sport = (String) e.getItem();
					int age = Period
							.between(eleve.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
									new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
							.getYears();
					ArrayList<TypeCours> liste = (ArrayList<TypeCours>) ListeTypeCoursS.getInstance().getListe()
							.stream()
							.filter(x -> x.getAccreditation().getSport().equals(sport)
									&& age >= x.getAccreditation().getAgeMin()
									&& age < x.getAccreditation().getAgeMax())
							.collect(Collectors.toList());
					modaleCours.getComboNiveau().removeAllItems();
					for (TypeCours typeCours : liste) {
						modaleCours.getComboNiveau().addItem(typeCours.getNiveau());
					}
				}
			});
			modaleCours.addbuttonValiderListener(e -> {
				validationCoursCollectif(eleve, reservation);
			});
			modaleCours.getComboSport().setSelectedIndex(1);
			modaleCours.getComboSport().setSelectedIndex(0);
		} else {
			modaleCours.addbuttonValiderListener(e -> {
				validationCoursParticulier(eleve, reservation);
			});
		}
		modaleCours.addbuttonAnnulerListener(e -> {
			modaleCours.dispose();
		});
	}

	// VALIDATION AJOUT COURS
	public void validationCoursCollectif(Eleve eleve, Reservation reservation) {
		TypeCours typeCours = ListeTypeCoursS.getInstance().getListe().stream()
				.filter(x -> x.getAccreditation().getSport().equals(modaleCours.getSport())
						&& x.getNiveau().equals(modaleCours.getNiveau()))
				.findAny().orElse(null);
		Semaine semaine = modaleCours.getSemaine();
		Horaire horaire = modaleCours.getHoraire();

		CoursCollectif cours = (CoursCollectif) ListeCoursS.getInstance().getListe().stream()
				.filter(x -> x instanceof CoursCollectif && ((CoursCollectif) x).getTypeCours().equals(typeCours)
						&& ((CoursCollectif) x).getSemaine().equals(semaine)
						&& ((CoursCollectif) x).getHoraire().equals(horaire))
				.findAny().orElse(null);
		if (cours != null) {
			if (cours.getListeEleve().size() >= typeCours.getMaxEleve()) {
				cours.setStatutCours(Cours.FERMER);
				ListeCoursS.getInstance().mettreAjourCours(cours);
				modaleCours.afficheMessage("Ce cours est déjà plein veuillez choisir une autre date", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (!cours.getListeEleve().contains(eleve)) {
					reservation.getListeCours().add(cours);
					modele.miseAjourReservation(reservation);
					cours.getListeEleve().add(eleve);
					if (cours.getListeEleve().size() >= typeCours.getMinEleve()) {
						cours.setStatutCours(Cours.OUVERT);
						ListeCoursS.getInstance().mettreAjourCours(cours);
					}
					modaleCours.afficheMessage("Le cours a bien été ajouté à la réservation", "Information",
							JOptionPane.INFORMATION_MESSAGE);
					modaleCours.dispose();
				} else {
					modaleCours.afficheMessage("Cet élève est déjà inscrit à ce cours", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			Moniteur m = null;
			boolean flag;
			ArrayList<Moniteur> listeM = (ArrayList<Moniteur>) ListeMoniteurS.getInstance().getListe().stream()
					.filter(x -> x.getListeAccreditation().contains(typeCours.getAccreditation())
							&& !(x.getListeIndisponibilitee().contains(semaine)))
					.collect(Collectors.toList());
			for (Moniteur moniteur : listeM) {
				flag = true;
				for (Cours coursCo : moniteur.getListeCours()) {
					if (coursCo instanceof CoursCollectif && ((CoursCollectif) coursCo).getSemaine().equals(semaine)
							&& ((CoursCollectif) coursCo).getHoraire().equals(horaire)) {
						flag = false;
					}
				}
				if (flag)
					m = moniteur;
			}
			if (m != null) {
				CoursCollectif coursCollectif = new CoursCollectif();
				coursCollectif.setMoniteur(m);
				coursCollectif.getListeEleve().add(eleve);
				coursCollectif.setTypeCours(typeCours);
				coursCollectif.setSemaine(semaine);
				coursCollectif.setHoraire(horaire);
				coursCollectif.setStatutCours(Cours.ATTENTE);
				ListeCoursS.getInstance().ajouterCours(coursCollectif);
				reservation.getListeCours().add(coursCollectif);
				modele.miseAjourReservation(reservation);
				modaleCours.afficheMessage("Le cours a bien été ajouté à la réservation", "Information",
						JOptionPane.INFORMATION_MESSAGE);
				modaleCours.dispose();
			} else {
				modaleCours.afficheMessage("Il n'y a aucun moniteur disponible pour ce cours", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void validationCoursParticulier(Eleve eleve, Reservation reservation) {
		if (modaleCours.getDateCours() != null) {
			int age = Period.between(eleve.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getYears();
			Accreditation accreditation = ListeAccreditationS.getInstance().getListe().stream().filter(
					x -> x.getSport().equals(modaleCours.getSport()) && age >= x.getAgeMin() && age < x.getAgeMax())
					.findAny().orElse(null);
			Date date = modaleCours.getDateCours();
			Horaire horaire = modaleCours.getHoraire();

			CoursParticulier cours = (CoursParticulier) ListeCoursS.getInstance().getListe().stream()
					.filter(x -> x instanceof CoursParticulier
							&& ((CoursParticulier) x).getAccreditation().equals(accreditation)
							&& dateConvert(((CoursParticulier) x).getDate()).equals(dateConvert(date))
							&& ((CoursParticulier) x).getHoraire().equals(horaire))
					.findAny().orElse(null);
			if (cours != null) {
				if (cours.getListeEleve().size() >= CoursParticulier.MAX_ELEVE) {
					cours.setStatutCours(Cours.FERMER);
					ListeCoursS.getInstance().mettreAjourCours(cours);
					modaleCours.afficheMessage("Ce cours est déjà plein veuillez choisir une autre date", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (!cours.getListeEleve().contains(eleve)) {
						reservation.getListeCours().add(cours);
						modele.miseAjourReservation(reservation);
						cours.getListeEleve().add(eleve);
						if (cours.getListeEleve().size() >= CoursParticulier.MIN_ELEVE) {
							cours.setStatutCours(Cours.OUVERT);
							ListeCoursS.getInstance().mettreAjourCours(cours);
						}
						modaleCours.afficheMessage("Le cours a bien été ajouté à la réservation", "Information",
								JOptionPane.INFORMATION_MESSAGE);
						modaleCours.dispose();
					} else {
						modaleCours.afficheMessage("Cet élève est déjà inscrit à ce cours", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				Moniteur m = null;
				boolean flag;
				boolean flag2 = false;
				boolean conge = false;
				ArrayList<Moniteur> listeM = (ArrayList<Moniteur>) ListeMoniteurS.getInstance().getListe().stream()
						.filter(x -> x.getListeAccreditation().contains(accreditation) && x.getCoursParticulier())
						.collect(Collectors.toList());
				for (Moniteur moniteur : listeM) {
					flag = true;
					for (Semaine semaine : moniteur.getListeIndisponibilitee()) {
						if (date.after(semaine.getDateDeb()) && date.before(semaine.getDateFin()))
							flag = false;
					}
					for (Cours coursPa : moniteur.getListeCours()) {
						if (coursPa instanceof CoursParticulier
								&& dateConvert(((CoursParticulier) coursPa).getDate()).equals(dateConvert(date))
								&& ((CoursParticulier) coursPa).getHoraire().equals(horaire)) {
							flag = false;
						}
					}
					if (flag)
						m = moniteur;
				}
				for (Semaine semaine : ListeSemaineS.getInstance().getListe()) {
					if (date.after(semaine.getDateDeb()) && date.before(semaine.getDateFin())) {
						flag2 = true;
						conge = semaine.getCongeScolaire();
					}
				}
				if (conge && daysBetween(reservation.getDateReservation(), date) >= 7
						|| !conge && daysBetween(reservation.getDateReservation(), date) >= 31) {
					if (m != null && flag2) {
						CoursParticulier coursParticulier = new CoursParticulier();
						coursParticulier.setMoniteur(m);
						coursParticulier.getListeEleve().add(eleve);
						coursParticulier.setAccreditation(accreditation);
						coursParticulier.setDate(date);
						coursParticulier.setHoraire(horaire);
						coursParticulier.setStatutCours(Cours.OUVERT);
						coursParticulier.setCongeScolaire(conge);
						ListeCoursS.getInstance().ajouterCours(coursParticulier);
						reservation.getListeCours().add(coursParticulier);
						modele.miseAjourReservation(reservation);
						modaleCours.afficheMessage("Le cours a bien été ajouté à la réservation", "Information",
								JOptionPane.INFORMATION_MESSAGE);
						modaleCours.dispose();
					} else {
						modaleCours.afficheMessage("Il n'y a aucun moniteur disponible pour ce cours", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					modaleCours.afficheMessage("Il trop tard pour pouvoir reserver à cette date", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			modaleCours.afficheMessage("Veuillez choisir une date", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	static public Date dateConvert(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

	// METHODE RESERVATION
	public void effectuerReservation() {
		Eleve eleve = (Eleve) vue.Chosis(modele.getListeEleve().toArray(),
				"Effectuer une nouvelle réservation pour l'élève : ", "Choisissez un élève");
		Reservation reservation = new Reservation();
		reservation.setClient((Client) modele.getUtilisateur());
		reservation.setEleve(eleve);
		reservation.setDateReservation(new Date());
		reservation.setStatutReservation(Reservation.COMMANDER);
		if (modele.ajoutReservation(reservation)) {
			do {
				Object choix = vue.Chosis(choixCours, "Ajouter un : ", "Choisissez un type de cours");
				if (choix != null) {
					boolean coursCollectif = ((String) choix).equals("Cours collectif") ? true : false;
					modaleCours = new FenetreModaleReservation(vue, "Ajout de cours", coursCollectif);
					remplirComboBox(eleve, coursCollectif);
					AjoutEventModaleCours(eleve, coursCollectif, reservation);
					modaleCours.setVisible(true);
				}
			} while (vue.demande("Voulez-vous réserver un autre cours?"));
			if (!(reservation.getListeCours().size() >= 1)) {
				vue.afficheMessage("Votre réservation n'a pas été prise en compte car elle ne concernait aucun cours",
						"Erreur", JOptionPane.ERROR_MESSAGE);
				modele.supprimerReservation(reservation);
			} else {
				ListeReservationS.getInstance().calculPrix(reservation);
				modele.miseAjourReservation(reservation);
			}
		} else {
			vue.afficheMessage("Impossible d'effectuer cette reservation", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void remplirComboBox(Eleve eleve, boolean coursCollectif) {
		int age = Period.between(eleve.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getYears();

		for (Accreditation accreditation : ListeAccreditationS.getInstance().getListe()) {
			if (age >= accreditation.getAgeMin() && age < accreditation.getAgeMax())
				modaleCours.getComboSport().addItem(accreditation.getSport());
		}
		if (coursCollectif) {
			for (Semaine semaine : ListeSemaineS.getInstance().getListe()) {
				modaleCours.getComboSemaine().addItem(semaine);
			}

			for (Horaire horaire : ListeHoraireS.getInstance().getListe()) {
				if (horaire.getHeureFin() - horaire.getHeureDeb() > 2)
					modaleCours.getComboHoraire().addItem(horaire);
			}
		} else {
			for (Horaire horaire : ListeHoraireS.getInstance().getListe()) {
				if (horaire.getHeureFin() - horaire.getHeureDeb() <= 2)
					modaleCours.getComboHoraire().addItem(horaire);
			}
		}
	}
}
