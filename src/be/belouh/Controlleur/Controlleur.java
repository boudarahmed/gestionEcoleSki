package be.belouh.Controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import be.belouh.Modele.UtilisateurM;
import be.belouh.Vue.Fenetre;

public class Controlleur {
	private Fenetre vue;
	//private UtilisateurM modele;
	
	public Controlleur(Fenetre vue) {
		this.vue = vue;
		
		this.vue.addbuttonConnexionListener(new ButtonConnexionlistener());
	}
	
	class ButtonConnexionlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			vue.videEcran();
			/*modele.getUtilisateur().setAdresseMail(vue.getAdresseMail());
			modele.getUtilisateur().setMotDePasse(vue.getMotDePasse());
			if(modele.connexion())
				JOptionPane.showMessageDialog(vue, "Connexion réussie voici votre id : " + modele.getUtilisateur().getId());
			else
				vue.afficheErreurConnexion();*/
		}
		
	}
}
