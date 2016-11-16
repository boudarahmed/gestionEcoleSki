package be.belouh.Vue;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import be.belouh.POJO.Semaine;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel(null);

	// COMPOSANTS DE L'ECRAN DE CONNEXION
	private JPanel panelConnexion = new JPanel(null);
	private JLabel labelAdresseMail = new JLabel("Adresse e-mail:");
	private JTextField textFieldAdresseMail = new JTextField();
	private JLabel labelMotDePasse = new JLabel("Mot de passe:");
	private JPasswordField passwordFieldMotDePasse = new JPasswordField();
	private JRadioButton radioClient = new JRadioButton("Client", true);
	private JRadioButton radioMoniteur = new JRadioButton("Moniteur");
	private JRadioButton radioAdministrateur = new JRadioButton("Administrateur");
	private ButtonGroup buttonGroupUtilisateur = new ButtonGroup();
	private JButton buttonInscriptionClient = new JButton("S'inscrire");
	private JButton buttonConnexion = new JButton("Connexion");

	// COMPOSANTS DE L'ECRAN D'INSCRIPTION
	private JPanel panelInscription = new JPanel(null);
	private JLabel labelInscriptionNom = new JLabel("Nom");
	private JTextField textFieldInscriptionNom = new JTextField();
	private JLabel labelInsciptionPrenom = new JLabel("Prénom");
	private JTextField textFieldInscriptionPrenom = new JTextField();
	private JLabel labelInscriptionDateNaissance = new JLabel("Date de naissance");
	private UtilDateModel modelInscriptionDateNaissance = new UtilDateModel();
	private JDatePanelImpl datePanelInscriptionDateNaissance = new JDatePanelImpl(modelInscriptionDateNaissance);
	private JDatePickerImpl datePickerInscriptionDateNaissance = new JDatePickerImpl(datePanelInscriptionDateNaissance);
	private JLabel labelInscriptionSexe = new JLabel("Sexe");
	private JRadioButton radioMInscriptionSexe = new JRadioButton("M", true);
	private JRadioButton radioFInscriptionSexe = new JRadioButton("F");
	private ButtonGroup buttonGroupInscriptionSexe = new ButtonGroup();
	private JLabel labelInscriptionNumero = new JLabel("Numéro");
	private JTextField textFieldInscriptionNumero = new JTextField();
	private JLabel labelInscriptionRue = new JLabel("Rue");
	private JTextField textFieldInscriptionRue = new JTextField();
	private JLabel labelInscriptionCodePostal = new JLabel("Code postal");
	private JTextField textFieldInscriptionCodePostal = new JTextField();
	private JLabel labelInscriptionVille = new JLabel("Ville");
	private JTextField textFieldInscriptionVille = new JTextField();
	private JLabel labelInscriptionAdresseMail = new JLabel("Adresse e-mail");
	private JTextField textFieldInscriptionAdresseMail = new JTextField();
	private JLabel labelInscriptionMotDePasse = new JLabel("Mot de passe");
	private JPasswordField passwordFieldInscriptionMotDePasse = new JPasswordField();
	private JLabel labelInscriptionConfirmMotDePasse = new JLabel("Confirmer le mot de passe");
	private JPasswordField passwordFieldInscriptionConfirmMotDePasse = new JPasswordField();
	private JLabel labelInscriptionNumeroCompte = new JLabel("Numéro de compte");
	private JTextField textFieldInscriptionNumeroCompte = new JTextField();
	private JButton buttonInscriptionValidation = new JButton("Valider");
	private JButton buttonInscriptionAnnulation = new JButton("Annuler");
	
	// COMPOSANTS DE L'ECRAN MONITEUR
	private JPanel panelMoniteur = new JPanel(null);
	private JScrollPane scrollPaneCoursMoniteur;
	private JTable tableauCoursMoniteur;
	private DefaultTableModel tableauCoursMoniteurModele;
	private JComboBox<Semaine> comboSemaineCoursMoniteur = new JComboBox<Semaine>();
	private JRadioButton radioCoursCollectifMoniteur = new JRadioButton("Cours collectif", true);
	private JRadioButton radioCoursParticulierMoniteur = new JRadioButton("Cours particulier");
	private ButtonGroup buttonGroupCoursMoniteur = new ButtonGroup();

	public Fenetre() {
		panel.setOpaque(true);
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Projet Java");
		setVisible(true);
	}

	// RECUPERATION DES CHAMPS DE L'ECRAN DE CONNEXION
	public String getAdresseMail() {
		return textFieldAdresseMail.getText();
	}

	public String getMotDePasse() {
		return String.valueOf(passwordFieldMotDePasse.getPassword());
	}

	public String getRadioUtilisateur() {
		if (radioClient.isSelected())
			return radioClient.getText();
		else if (radioMoniteur.isSelected())
			return radioMoniteur.getText();
		else
			return radioAdministrateur.getText();
	}

	// RECUPERATION DES CHAMPS DE L'ECRAN D'INSCRIPTION
	public String getNomInscription() {
		return textFieldInscriptionNom.getText();
	}

	public String getPrenomInscription() {
		return textFieldInscriptionPrenom.getText();
	}

	public Date getDateNaissanceInscription() {
		return (Date) datePickerInscriptionDateNaissance.getModel().getValue();
	}

	public char getSexeInscription() {
		if (radioMInscriptionSexe.isSelected())
			return radioMInscriptionSexe.getText().toCharArray()[0];
		else
			return radioFInscriptionSexe.getText().toCharArray()[0];
	}

	public String getNumeroInscription() {
		return textFieldInscriptionNumero.getText();
	}

	public String getRueInscription() {
		return textFieldInscriptionRue.getText();
	}

	public String getCodePostalInscription() {
		return textFieldInscriptionCodePostal.getText();
	}

	public String getVilleInscription() {
		return textFieldInscriptionVille.getText();
	}

	public String getAdresseEmailInscription() {
		return textFieldInscriptionAdresseMail.getText();
	}

	public String getMotDePasseInscription() {
		return String.valueOf(passwordFieldInscriptionMotDePasse.getPassword());
	}

	public String getConfirmMotDePasseInscription() {
		return String.valueOf(passwordFieldInscriptionConfirmMotDePasse.getPassword());
	}

	public String getNumeroCompteInscription() {
		return textFieldInscriptionNumeroCompte.getText();
	}

	// METHODES D'AFFICHAGE DE L'ECRAN DE CONNEXION
	public void afficheEcranConnexion() {
		panelConnexion.setSize(400, 300);
		panelConnexion.setLocation(200, 125);

		labelAdresseMail.setSize(360, 25);
		labelAdresseMail.setLocation(20, 30);
		textFieldAdresseMail.setSize(360, 40);
		textFieldAdresseMail.setLocation(20, 60);
		labelMotDePasse.setSize(360, 25);
		labelMotDePasse.setLocation(20, 110);
		passwordFieldMotDePasse.setSize(360, 40);
		passwordFieldMotDePasse.setLocation(20, 140);

		radioClient.setSize(60, 20);
		radioClient.setLocation(20, 200);
		radioMoniteur.setSize(80, 20);
		radioMoniteur.setLocation(85, 200);
		radioAdministrateur.setSize(110, 20);
		radioAdministrateur.setLocation(170, 200);
		buttonGroupUtilisateur.add(radioClient);
		buttonGroupUtilisateur.add(radioMoniteur);
		buttonGroupUtilisateur.add(radioAdministrateur);

		buttonInscriptionClient.setForeground(Color.BLUE);
		buttonInscriptionClient.setBorderPainted(false);
		buttonInscriptionClient.setContentAreaFilled(false);
		buttonInscriptionClient.setSize(90, 20);
		buttonInscriptionClient.setLocation(5, 260);

		buttonConnexion.setSize(120, 40);
		buttonConnexion.setLocation(260, 240);

		panelConnexion.add(labelAdresseMail);
		panelConnexion.add(textFieldAdresseMail);
		panelConnexion.add(labelMotDePasse);
		panelConnexion.add(passwordFieldMotDePasse);
		panelConnexion.add(radioClient);
		panelConnexion.add(radioMoniteur);
		panelConnexion.add(radioAdministrateur);
		panelConnexion.add(buttonInscriptionClient);
		panelConnexion.add(buttonConnexion);
		panel.add(panelConnexion);
	}

	public void afficheInscrire() {
		buttonInscriptionClient.setText("S'inscrire");
	}

	public void cacheInscrire() {
		buttonInscriptionClient.setText("");
	}
	
	public void videChampConnexion(){
		textFieldAdresseMail.setText("");
		passwordFieldMotDePasse.setText("");
	}
	
	// METHODES D'AFFICHAGE DE L'ECRAN D'INSCRIPTION
	public void afficheEcranInscription() {
		panelInscription.setSize(800, 600);
		panelInscription.setLocation(0, 0);

		labelInscriptionNom.setSize(30, 25);
		labelInscriptionNom.setLocation(210, 65);
		labelInsciptionPrenom.setSize(50, 25);
		labelInsciptionPrenom.setLocation(190, 100);
		labelInscriptionDateNaissance.setSize(110, 25);
		labelInscriptionDateNaissance.setLocation(130, 135);
		labelInscriptionSexe.setSize(30, 25);
		labelInscriptionSexe.setLocation(205, 170);
		labelInscriptionNumero.setSize(50, 25);
		labelInscriptionNumero.setLocation(190, 205);
		labelInscriptionRue.setSize(30, 25);
		labelInscriptionRue.setLocation(210, 240);
		labelInscriptionCodePostal.setSize(70, 25);
		labelInscriptionCodePostal.setLocation(165, 275);
		labelInscriptionVille.setSize(30, 25);
		labelInscriptionVille.setLocation(205, 310);
		labelInscriptionAdresseMail.setSize(90, 25);
		labelInscriptionAdresseMail.setLocation(145, 345);
		labelInscriptionMotDePasse.setSize(80, 25);
		labelInscriptionMotDePasse.setLocation(155, 380);
		labelInscriptionConfirmMotDePasse.setSize(160, 25);
		labelInscriptionConfirmMotDePasse.setLocation(80, 415);
		labelInscriptionNumeroCompte.setSize(120, 25);
		labelInscriptionNumeroCompte.setLocation(120, 450);
		buttonInscriptionValidation.setSize(80, 30);
		buttonInscriptionValidation.setLocation(670, 505);
		buttonInscriptionAnnulation.setSize(80, 30);
		buttonInscriptionAnnulation.setLocation(580, 505);

		textFieldInscriptionNom.setSize(300, 25);
		textFieldInscriptionNom.setLocation(250, 65);
		textFieldInscriptionPrenom.setSize(300, 25);
		textFieldInscriptionPrenom.setLocation(250, 100);
		datePickerInscriptionDateNaissance.setSize(300, 26);
		datePickerInscriptionDateNaissance.setLocation(250, 135);
		radioMInscriptionSexe.setSize(40, 25);
		radioFInscriptionSexe.setSize(40, 25);
		radioMInscriptionSexe.setLocation(250, 170);
		radioFInscriptionSexe.setLocation(290, 170);
		buttonGroupInscriptionSexe.add(radioMInscriptionSexe);
		buttonGroupInscriptionSexe.add(radioFInscriptionSexe);
		textFieldInscriptionNumero.setSize(300, 25);
		textFieldInscriptionNumero.setLocation(250, 205);
		textFieldInscriptionRue.setSize(300, 25);
		textFieldInscriptionRue.setLocation(250, 240);
		textFieldInscriptionCodePostal.setSize(300, 25);
		textFieldInscriptionCodePostal.setLocation(250, 275);
		textFieldInscriptionVille.setSize(300, 25);
		textFieldInscriptionVille.setLocation(250, 310);
		textFieldInscriptionAdresseMail.setSize(300, 25);
		textFieldInscriptionAdresseMail.setLocation(250, 345);
		passwordFieldInscriptionMotDePasse.setSize(300, 25);
		passwordFieldInscriptionMotDePasse.setLocation(250, 380);
		passwordFieldInscriptionConfirmMotDePasse.setSize(300, 25);
		passwordFieldInscriptionConfirmMotDePasse.setLocation(250, 415);
		textFieldInscriptionNumeroCompte.setSize(300, 25);
		textFieldInscriptionNumeroCompte.setLocation(250, 450);

		panelInscription.add(labelInscriptionNom);
		panelInscription.add(labelInsciptionPrenom);
		panelInscription.add(labelInscriptionDateNaissance);
		panelInscription.add(labelInscriptionSexe);
		panelInscription.add(labelInscriptionNumero);
		panelInscription.add(labelInscriptionRue);
		panelInscription.add(labelInscriptionCodePostal);
		panelInscription.add(labelInscriptionVille);
		panelInscription.add(labelInscriptionAdresseMail);
		panelInscription.add(labelInscriptionMotDePasse);
		panelInscription.add(labelInscriptionConfirmMotDePasse);
		panelInscription.add(labelInscriptionNumeroCompte);
		panelInscription.add(buttonInscriptionValidation);
		panelInscription.add(buttonInscriptionAnnulation);

		panelInscription.add(textFieldInscriptionNom);
		panelInscription.add(textFieldInscriptionPrenom);
		panelInscription.add(datePickerInscriptionDateNaissance);
		panelInscription.add(radioMInscriptionSexe);
		panelInscription.add(radioFInscriptionSexe);
		panelInscription.add(textFieldInscriptionNumero);
		panelInscription.add(textFieldInscriptionRue);
		panelInscription.add(textFieldInscriptionCodePostal);
		panelInscription.add(textFieldInscriptionVille);
		panelInscription.add(textFieldInscriptionAdresseMail);
		panelInscription.add(passwordFieldInscriptionMotDePasse);
		panelInscription.add(passwordFieldInscriptionConfirmMotDePasse);
		panelInscription.add(textFieldInscriptionNumeroCompte);

		panel.add(panelInscription);
	}
	
	public void videChampInscription(){
		textFieldInscriptionNom.setText("");
		textFieldInscriptionPrenom.setText("");
		modelInscriptionDateNaissance.setSelected(false);
		radioMInscriptionSexe.setSelected(true);
		textFieldInscriptionNumero.setText("");
		textFieldInscriptionRue.setText("");
		textFieldInscriptionCodePostal.setText("");
		textFieldInscriptionVille.setText("");
		textFieldInscriptionAdresseMail.setText("");
		passwordFieldInscriptionMotDePasse.setText("");
		passwordFieldInscriptionConfirmMotDePasse.setText("");
		textFieldInscriptionNumeroCompte.setText("");
	}
	
	// METHODES D'AFFICHAGE DE L'ECRAN MONITEUR
	public void afficheEcranMoniteur(ArrayList<Semaine> listSemaine){
		panelMoniteur.setSize(800, 600);
		panelMoniteur.setLocation(0, 0);
		
		comboSemaineCoursMoniteur.setSize(175, 30);
		comboSemaineCoursMoniteur.setLocation(10, 25);
		
		for (Semaine semaine : listSemaine) {
			comboSemaineCoursMoniteur.addItem(semaine);
		}
		
		radioCoursCollectifMoniteur.setSize(150, 25);
		radioCoursCollectifMoniteur.setLocation(10, 75);
		radioCoursParticulierMoniteur.setSize(150, 25);
		radioCoursParticulierMoniteur.setLocation(10, 110);
		buttonGroupCoursMoniteur.add(radioCoursCollectifMoniteur);
		buttonGroupCoursMoniteur.add(radioCoursParticulierMoniteur);
		tableauCoursMoniteurModele = new DefaultTableModel();
		tableauCoursMoniteur = new JTable(tableauCoursMoniteurModele);
		tableauCoursMoniteur.setRowHeight(25);
		scrollPaneCoursMoniteur = new JScrollPane(tableauCoursMoniteur);
		scrollPaneCoursMoniteur.setSize(600, 600);
		scrollPaneCoursMoniteur.setLocation(196, 0);
		
		panelMoniteur.add(radioCoursParticulierMoniteur);
		panelMoniteur.add(radioCoursCollectifMoniteur);
		panelMoniteur.add(comboSemaineCoursMoniteur);
		panelMoniteur.add(scrollPaneCoursMoniteur);
		
		panel.add(panelMoniteur);
	}

	// LISTENER DE L'ECRAN DE CONNEXION
	public void addbuttonConnexionListener(ActionListener ButtonConnexionlistener) {
		buttonConnexion.addActionListener(ButtonConnexionlistener);
	}

	public void addbuttonInscriptionListener(ActionListener ButtonInscriptionListener) {
		buttonInscriptionClient.addActionListener(ButtonInscriptionListener);
	}

	public void addbuttonRadioConnexionListener(ActionListener ButtoRadioConnexionListener) {
		radioClient.addActionListener(ButtoRadioConnexionListener);
		radioMoniteur.addActionListener(ButtoRadioConnexionListener);
		radioAdministrateur.addActionListener(ButtoRadioConnexionListener);
	}

	// LISTENER DE L'ECRAN D'INSCRIPTION
	public void addbuttonValiderInscriptionListener(ActionListener ButtonValiderInscriptionListener) {
		buttonInscriptionValidation.addActionListener(ButtonValiderInscriptionListener);
	}

	public void addbuttonAnnulerInscriptionListener(ActionListener ButtonAnnulerInscriptionListener) {
		buttonInscriptionAnnulation.addActionListener(ButtonAnnulerInscriptionListener);
	}

	// METHODES POUR LA FENETRE
	public void videEcran() {
		panel.removeAll();
		panel.updateUI();
	}

	public void affiche(String text) {
		JOptionPane.showMessageDialog(this, text);
	}

	public boolean demande(String text) {
		if (JOptionPane.showConfirmDialog(this, text) == 0)
			return true;
		else
			return false;
	}
}
