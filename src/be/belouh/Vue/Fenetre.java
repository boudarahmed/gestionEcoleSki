package be.belouh.Vue;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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

	public Fenetre() {
		panel.setOpaque(true);
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Gestion école ski");
		setVisible(true);
	}

	public String getAdresseMail() {
		return textFieldAdresseMail.getText();
	}

	public String getMotDePasse() {
		return String.valueOf(passwordFieldMotDePasse.getPassword());
	}
	
	public String getRadioUtilisateur(){
		if(radioClient.isSelected())
			return radioClient.getText();
		else if(radioMoniteur.isSelected())
			return radioMoniteur.getText();
		else
			return radioAdministrateur.getText();
	}

	public void AfficheEcranConnexion() {
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

	public void videEcran() {
		panel.removeAll();
		panel.updateUI();
	}

	public void affiche(String error) {
		JOptionPane.showMessageDialog(this, error);
	}

	public void addbuttonConnexionListener(ActionListener ButtonConnexionlistener) {
		buttonConnexion.addActionListener(ButtonConnexionlistener);
	}

	public void addbuttonInscriptionClientListener(ActionListener ButtonInscriptionListener) {
		buttonInscriptionClient.addActionListener(ButtonInscriptionListener);
	}
}
