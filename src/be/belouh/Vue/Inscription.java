package be.belouh.Vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Inscription extends Fenetre {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel(null);
	private JLabel labelTitre = new JLabel("Inscription client");
	private JSeparator ligne = new JSeparator(JSeparator.HORIZONTAL);
	private JTextField textFieldNom = new JTextField("Nom");
	private JTextField textFieldPrenom = new JTextField("Pr�nom");
	private JTextField textFieldAdresseMail = new JTextField("Email");
	private JPasswordField passwordFieldMotDePasse = new JPasswordField("Mot de passe");
	private JPasswordField passwordFieldConfirmMotDePasse = new JPasswordField("Confirmer mot de passe");
	private JTextField textFieldNumero = new JTextField("Num�ro");
	private JTextField textFieldRue = new JTextField("Rue");
	private JTextField textFieldCodePostal = new JTextField("Code postal");
	private JTextField textFieldVille = new JTextField("Ville");
	private String[] items = { "M.", "Mme" };
	private JComboBox<String> comboSexe = new JComboBox<String>(items);
	private UtilDateModel modelDateNaissance = new UtilDateModel();
	private JDatePanelImpl datePanelDateNaissance = new JDatePanelImpl(modelDateNaissance);
	private JDatePickerImpl datePickerDateNaissance = new JDatePickerImpl(datePanelDateNaissance);
	private JTextField textFieldNumeroCompte = new JTextField("Num�ro de compte");
	private JButton buttonValider = new JButton("Valider");
	private JButton buttonAnnuler = new JButton("Annuler");

	public Inscription() {
		creationPanel();

		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Projet Java");
		Image img = new ImageIcon("icon.png").getImage();
		setIconImage(img);
		setVisible(true);
	}

	// RECUPERATION DES CHAMPS DE L'ECRAN DE CONNEXION
	public String getNom() {
		return textFieldNom.getText();
	}

	public String getPrenom() {
		return textFieldPrenom.getText();
	}

	public String getAdresseMail() {
		return textFieldAdresseMail.getText();
	}

	public String getMotDePasse() {
		return String.valueOf(passwordFieldMotDePasse.getPassword());
	}

	public String getConfirmMotDePasse() {
		return String.valueOf(passwordFieldConfirmMotDePasse.getPassword());
	}

	public String getNumero() {
		return textFieldNumero.getText();
	}

	public String getRue() {
		return textFieldRue.getText();
	}

	public String getCodePostal() {
		return textFieldCodePostal.getText();
	}

	public String getVille() {
		return textFieldVille.getText();
	}

	public char getSexe() {
		if (((String) comboSexe.getSelectedItem()).equals("M."))
			return 'M';
		else
			return 'F';
	}

	public Date getDateNaissance() {
		return (Date) datePickerDateNaissance.getModel().getValue();
	}

	public String getNumeroCompte() {
		return textFieldNumeroCompte.getText();
	}

	// METHODES D'AFFICHAGE DE L'ECRAN DE CONNEXION
	public void creationPanel() {
		panel.setBackground(Color.WHITE);
		panel.setOpaque(true);

		labelTitre.setForeground(Color.BLACK);
		labelTitre.setFont(new Font("SansSerif", Font.PLAIN, 22));
		labelTitre.setSize(155, 30);
		labelTitre.setLocation(30, 15);

		ligne.setSize(540, 1);
		ligne.setLocation(30, 55);
		ligne.setOpaque(true);

		textFieldNom.setForeground(Color.LIGHT_GRAY);
		textFieldNom.setSize(265, 30);
		textFieldNom.setLocation(30, 75);

		textFieldPrenom.setForeground(Color.LIGHT_GRAY);
		textFieldPrenom.setSize(265, 30);
		textFieldPrenom.setLocation(305, 75);

		textFieldAdresseMail.setForeground(Color.LIGHT_GRAY);
		textFieldAdresseMail.setSize(540, 30);
		textFieldAdresseMail.setLocation(30, 110);

		passwordFieldMotDePasse.setForeground(Color.LIGHT_GRAY);
		passwordFieldMotDePasse.setSize(265, 30);
		passwordFieldMotDePasse.setLocation(30, 145);
		passwordFieldMotDePasse.setEchoChar((char) 0);

		passwordFieldConfirmMotDePasse.setForeground(Color.LIGHT_GRAY);
		passwordFieldConfirmMotDePasse.setSize(265, 30);
		passwordFieldConfirmMotDePasse.setLocation(305, 145);
		passwordFieldConfirmMotDePasse.setEchoChar((char) 0);

		textFieldNumero.setForeground(Color.LIGHT_GRAY);
		textFieldNumero.setSize(65, 30);
		textFieldNumero.setLocation(30, 180);

		textFieldRue.setForeground(Color.LIGHT_GRAY);
		textFieldRue.setSize(465, 30);
		textFieldRue.setLocation(105, 180);

		textFieldCodePostal.setForeground(Color.LIGHT_GRAY);
		textFieldCodePostal.setSize(100, 30);
		textFieldCodePostal.setLocation(30, 215);

		textFieldVille.setForeground(Color.LIGHT_GRAY);
		textFieldVille.setSize(430, 30);
		textFieldVille.setLocation(140, 215);

		comboSexe.setSize(60, 30);
		comboSexe.setLocation(30, 250);

		datePickerDateNaissance.setBackground(Color.WHITE);
		datePickerDateNaissance.setSize(150, 30);
		datePickerDateNaissance.setLocation(100, 252);

		textFieldNumeroCompte.setForeground(Color.LIGHT_GRAY);
		textFieldNumeroCompte.setSize(310, 30);
		textFieldNumeroCompte.setLocation(260, 252);

		buttonValider.setSize(100, 40);
		buttonValider.setLocation(470, 305);
		buttonValider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		buttonAnnuler.setSize(100, 40);
		buttonAnnuler.setLocation(360, 305);
		buttonAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		panel.add(labelTitre);
		panel.add(ligne);
		panel.add(textFieldNom);
		panel.add(textFieldPrenom);
		panel.add(textFieldAdresseMail);
		panel.add(passwordFieldMotDePasse);
		panel.add(passwordFieldConfirmMotDePasse);
		panel.add(textFieldNumero);
		panel.add(textFieldRue);
		panel.add(textFieldCodePostal);
		panel.add(textFieldVille);
		panel.add(comboSexe);
		panel.add(datePickerDateNaissance);
		panel.add(textFieldNumeroCompte);
		panel.add(buttonValider);
		panel.add(buttonAnnuler);
	}

	// LISTENER DE L'ECRAN D'INSCRIPTION
	public void addbuttonValiderListener(ActionListener ButtonValiderListener) {
		buttonValider.addActionListener(ButtonValiderListener);
	}

	public void addbuttonAnnulerListener(ActionListener ButtonAnnulerListener) {
		buttonAnnuler.addActionListener(ButtonAnnulerListener);
	}

	public void addFocusFieldListenr(FocusListener FocusFieldListener) {
		textFieldNom.addFocusListener(FocusFieldListener);
		textFieldPrenom.addFocusListener(FocusFieldListener);
		textFieldAdresseMail.addFocusListener(FocusFieldListener);
		passwordFieldMotDePasse.addFocusListener(FocusFieldListener);
		passwordFieldConfirmMotDePasse.addFocusListener(FocusFieldListener);
		textFieldNumero.addFocusListener(FocusFieldListener);
		textFieldRue.addFocusListener(FocusFieldListener);
		textFieldCodePostal.addFocusListener(FocusFieldListener);
		textFieldVille.addFocusListener(FocusFieldListener);
		textFieldNumeroCompte.addFocusListener(FocusFieldListener);
	}
}
