package be.belouh.Controlleur;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.text.JTextComponent;

import be.belouh.Modele.AdministrateurM;
import be.belouh.Modele.ClientM;
import be.belouh.Modele.MoniteurM;
import be.belouh.Modele.UtilisateurM;
import be.belouh.Vue.Connexion;

public class ControlleurConnexion {
	private Connexion vue;
	private UtilisateurM modele;

	public ControlleurConnexion() {
		vue = new Connexion();

		vue.addbuttonConnexionListener(e -> {
			switch (vue.getRadioUtilisateur()) {
			case "Client":
				modele = new ClientM();
				modele.getUtilisateur().setAdresseMail(vue.getAdresseMail());
				modele.getUtilisateur().setMotDePasse(vue.getMotDePasse());
				if (modele.connexion())
					vue.dispose();
				else
					vue.afficheMessage("Votre email ou votre mot de passe est incorrect", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				break;
			case "Moniteur":
				modele = new MoniteurM();
				modele.getUtilisateur().setAdresseMail(vue.getAdresseMail());
				modele.getUtilisateur().setMotDePasse(vue.getMotDePasse());
				if (modele.connexion()) {
					vue.dispose();
					new ControlleurMoniteur((MoniteurM) modele);
				} else
					vue.afficheMessage("Votre email ou votre mot de passe est incorrect", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				break;
			case "Administrateur":
				modele = new AdministrateurM();
				modele.getUtilisateur().setAdresseMail(vue.getAdresseMail());
				modele.getUtilisateur().setMotDePasse(vue.getMotDePasse());
				if (modele.connexion())
					vue.dispose();
				else
					vue.afficheMessage("Votre email ou votre mot de passe est incorrect", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				break;
			default:
				break;
			}
		});

		vue.addbuttonInscriptionListener(e -> {
			vue.dispose();
			new ControlleurInscription();
		});

		vue.addbuttonRadioConnexionListener(e -> {
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
		});

		vue.addFocusFieldListenr(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (((JTextComponent) e.getSource()).getText().equals("")) {
					if (e.getSource().getClass().toString().equals("class javax.swing.JTextField")) {
						((JTextComponent) e.getSource()).setText("Email");
						((JTextComponent) e.getSource()).setForeground(Color.LIGHT_GRAY);
					} else {
						((JPasswordField) e.getSource()).setText("Mot de passe");
						((JPasswordField) e.getSource()).setForeground(Color.LIGHT_GRAY);
						((JPasswordField) e.getSource()).setEchoChar((char) 0);
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (((JTextComponent) e.getSource()).getText().equals("Email")) {
					((JTextComponent) e.getSource()).setText("");
					((JTextComponent) e.getSource()).setForeground(Color.BLACK);
				}
				if (((JTextComponent) e.getSource()).getText().equals("Mot de passe")) {
					((JPasswordField) e.getSource()).setText("");
					((JPasswordField) e.getSource()).setForeground(Color.BLACK);
					((JPasswordField) e.getSource()).setEchoChar('*');
				}
			}
		});
	}
}
