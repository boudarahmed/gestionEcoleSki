package be.belouh.Controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import be.belouh.Modele.AdministrateurM;
import be.belouh.Modele.ClientM;
import be.belouh.Modele.UtilisateurM;
import be.belouh.Vue.Fenetre;

public class Controlleur {
	private Fenetre vue;
	private UtilisateurM modele;
	
	public Controlleur(Fenetre vue) {
		this.vue = vue;
		this.vue.AfficheEcranConnexion();
		this.vue.addbuttonConnexionListener(new ButtonConnexionlistener());
	}
	
	class ButtonConnexionlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (vue.getRadioUtilisateur()) {
			case "Client":
				modele = new ClientM();
				modele.getUtilisateur().setAdresseMail(vue.getAdresseMail());
				modele.getUtilisateur().setMotDePasse(vue.getMotDePasse());
				if(modele.connexion())
					vue.affiche("Connexion réussie voici votre id : " + modele.getUtilisateur().getId());
				else
					vue.affiche("échec connexion");
				break;
			case "Moniteur":
				modele = new MoniteurM();
				modele.getUtilisateur().setAdresseMail(vue.getAdresseMail());
				modele.getUtilisateur().setMotDePasse(vue.getMotDePasse());
				if(modele.connexion())
					vue.affiche("Connexion réussie voici votre id : " + modele.getUtilisateur().getId());
				else
					vue.affiche("échec connexion");
				break;
			case "Administrateur":
				modele = new AdministrateurM();
				modele.getUtilisateur().setAdresseMail(vue.getAdresseMail());
				modele.getUtilisateur().setMotDePasse(vue.getMotDePasse());
				if(modele.connexion())
					vue.affiche("Connexion réussie voici votre id : " + modele.getUtilisateur().getId());
				else
					vue.affiche("échec connexion");
				break;
			default:
				break;
			}
		}
		
	}
}
