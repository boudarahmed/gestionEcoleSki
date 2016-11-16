package be.belouh.Controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import be.belouh.Modele.AdministrateurM;
import be.belouh.Modele.ClientM;
import be.belouh.Modele.MoniteurM;
import be.belouh.Modele.UtilisateurM;
import be.belouh.Singleton.ListeSemaineS;
import be.belouh.Vue.Fenetre;

public class Controlleur {
	private Fenetre vue;
	private UtilisateurM modele;

	public Controlleur(Fenetre _vue) {
		vue = _vue;
		vue.afficheEcranConnexion();
		vue.addbuttonConnexionListener(new ButtonConnexionlistener());
		vue.addbuttonInscriptionListener(new ButtonInscriptionListener());
		vue.addbuttonRadioConnexionListener(new ButtoRadioConnexionListener());
		vue.addbuttonValiderInscriptionListener(new ButtonValiderInscriptionListener());
		vue.addbuttonAnnulerInscriptionListener(new ButtonAnnulerInscriptionListener());
	}

	public String verificationChampInscription() {
		String res = "Le(s) champ(s) suivant(s) ne sont pas remplis : ";
		if (vue.getNomInscription().equals(""))
			res += "\nNom, ";
		if (vue.getPrenomInscription().equals(""))
			res += "Prénom, ";
		if (vue.getDateNaissanceInscription() == null)
			res += "Date de Naissance, ";
		if (vue.getNumeroInscription().equals(""))
			res += "Numéro, ";
		if (vue.getRueInscription().equals(""))
			res += "Rue, ";
		if (vue.getCodePostalInscription().equals(""))
			res += "CodePostal, ";
		if (vue.getVilleInscription().equals(""))
			res += "Ville, ";
		if (vue.getAdresseEmailInscription().equals(""))
			res += "Adresse e-mail, ";
		if (vue.getMotDePasseInscription().equals(""))
			res += "Mot de passe, ";
		if (vue.getConfirmMotDePasseInscription().equals(""))
			res += "Confirmer le mot de passe, ";
		if (vue.getNumeroCompteInscription().equals(""))
			res += "Numéro de compte, ";
		if (res.equals("Le(s) champ(s) suivant(s) ne sont pas remplis : "))
			res = "";
		else
			res = res.substring(0, res.length() - 2);
		return res;
	}

	public String verificationMotDePasseInscription() {
		String res = "";
		if (!vue.getMotDePasseInscription().equals(vue.getConfirmMotDePasseInscription()))
			res = "Les mots de passe ne correspondent pas";
		return res;
	}

	// CLASS LISTENER DE L'ECRAN DE CONNEXION
	class ButtonConnexionlistener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (vue.getRadioUtilisateur()) {
			case "Client":
				modele = new ClientM();
				modele.getUtilisateur().setAdresseMail(vue.getAdresseMail());
				modele.getUtilisateur().setMotDePasse(vue.getMotDePasse());
				if (modele.connexion()){
					vue.videChampConnexion();
					vue.videEcran();
					vue.affiche("Connexion réussie");
				}
				else
					vue.affiche("échec connexion");
				break;
			case "Moniteur":
				modele = new MoniteurM();
				modele.getUtilisateur().setAdresseMail(vue.getAdresseMail());
				modele.getUtilisateur().setMotDePasse(vue.getMotDePasse());
				if (modele.connexion()){
					vue.videChampConnexion();
					vue.videEcran();
					vue.afficheEcranMoniteur(ListeSemaineS.getInstance().getListe());
				}
				else
					vue.affiche("échec connexion");
				break;
			case "Administrateur":
				modele = new AdministrateurM();
				modele.getUtilisateur().setAdresseMail(vue.getAdresseMail());
				modele.getUtilisateur().setMotDePasse(vue.getMotDePasse());
				if (modele.connexion()){
					vue.videChampConnexion();
					vue.videEcran();
					vue.affiche("Connexion réussie");
				}
				else
					vue.affiche("échec connexion");
				break;
			default:
				break;
			}
		}

	}

	class ButtonInscriptionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			vue.videChampConnexion();
			vue.videEcran();
			vue.afficheEcranInscription();
		}

	}

	class ButtoRadioConnexionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AbstractButton button = (AbstractButton) e.getSource();
			switch (button.getText()) {
			case "Client":
				vue.afficheInscrire();
				break;
			case "Moniteur":
				vue.cacheInscrire();
				break;
			case "Administrateur":
				vue.cacheInscrire();
				break;
			default:
				break;
			}
		}
	}

	// CLASS LISTENER DE L'ECRAN INSCRIPTION
	class ButtonValiderInscriptionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String verifChamp = verificationChampInscription();
			String verifMotDePasse = verificationMotDePasseInscription();
			if (!verifChamp.equals(""))
				vue.affiche(verifChamp);
			else if (!verifMotDePasse.equals(""))
				vue.affiche(verifMotDePasse);
			else {
				modele = new ClientM(vue.getNomInscription(), vue.getPrenomInscription(),
						vue.getDateNaissanceInscription(), vue.getSexeInscription(), vue.getNumeroInscription(),
						vue.getRueInscription(), vue.getCodePostalInscription(), vue.getVilleInscription(),
						vue.getAdresseEmailInscription(), vue.getMotDePasseInscription(),
						vue.getNumeroCompteInscription());

				if (((ClientM) modele).Inscription()){
					vue.videChampInscription();
					vue.videEcran();
					vue.affiche("Inscription effectuée avec succès");
				}
				else
					vue.affiche("Cette adresse e-mail est déjà attribué à un autre client");
			}
		}

	}

	class ButtonAnnulerInscriptionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (vue.demande("Voulez-vous revenir à l'écran de connexion?")) {
				vue.videChampInscription();
				vue.videEcran();
				vue.afficheEcranConnexion();
			}

		}

	}
}
