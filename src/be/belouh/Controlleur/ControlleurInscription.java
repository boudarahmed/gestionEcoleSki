package be.belouh.Controlleur;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.text.JTextComponent;

import be.belouh.Modele.ClientM;
import be.belouh.Vue.Inscription;

public class ControlleurInscription {
	private Inscription vue;
	private ClientM modele;
	private String text;
	private String[] tabText = { "Nom", "Prénom", "Email", "Numéro", "Rue", "Code postal", "Ville", "Numéro de compte",
			"Confirmer mot de passe", "Mot de passe" };
	private ArrayList<String> texts = new ArrayList<String>(Arrays.asList(tabText));

	public ControlleurInscription() {
		vue = new Inscription();

		vue.addbuttonValiderListener(e -> {
			String verif = validationFormulaire();
			if (!verif.equals(""))
				vue.afficheMessage(verif, JOptionPane.ERROR_MESSAGE);
			else {
				modele = new ClientM(vue.getNom(), vue.getPrenom(), vue.getDateNaissance(), vue.getSexe(),
						vue.getNumero(), vue.getRue(), vue.getCodePostal(), vue.getVille(), vue.getAdresseMail(),
						vue.getMotDePasse(), vue.getNumeroCompte());
				if (modele.Inscription()) {
					vue.dispose();
				} else {
					vue.afficheMessage("Cette adresse e-mail est déjà utilisée par un autre client",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		vue.addbuttonAnnulerListener(e -> {
			if (vue.demande("Voulez-vous revenir à l'écran de connexion?")) {
				vue.dispose();
				new ControlleurConnexion();
			}
		});

		vue.addFocusFieldListenr(new FocusListener() {

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

	public String validationFormulaire() {
		StringBuilder res = new StringBuilder("Le(s) champ(s) suivant(s) ne sont pas remplis : ");
		if (texts.contains(vue.getNom()))
			res.append("Nom, ");
		if (texts.contains(vue.getPrenom()))
			res.append("Prénom, ");
		if (texts.contains(vue.getAdresseMail()))
			res.append("Email, ");
		if (texts.contains(vue.getMotDePasse()))
			res.append("Mot de passe, ");
		if (texts.contains(vue.getConfirmMotDePasse()))
			res.append("Confirmer mot de passe, ");
		if (texts.contains(vue.getNumero()))
			res.append("Numéro, ");
		if (texts.contains(vue.getRue()))
			res.append("Rue, ");
		if (texts.contains(vue.getCodePostal()))
			res.append("Code postal, ");
		if (texts.contains(vue.getVille()))
			res.append("Ville, ");
		if (vue.getDateNaissance() == null)
			res.append("Date de naissance, ");
		if (texts.contains(vue.getNumeroCompte()))
			res.append("Numéro de compte, ");
		if (res.toString().equals("Le(s) champ(s) suivant(s) ne sont pas remplis : "))
			if (!vue.getMotDePasse().equals(vue.getConfirmMotDePasse()))
				res.replace(0, res.length(), "Les mots de passe ne correspondent pas");
			else
				res.replace(0, res.length(), "");
		else
			res = new StringBuilder(res.substring(0, res.length() - 2));
		return res.toString();
	}
}
