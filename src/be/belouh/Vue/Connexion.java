package be.belouh.Vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Connexion extends Fenetre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MonPanel panel = new MonPanel();
	private JLabel labelDomaine = new JLabel("Domaine Châtelet");
	private JTextField textFieldAdresseMail = new JTextField("Email");
	private JPasswordField passwordFieldMotDePasse = new JPasswordField("Mot de passe");
	private JRadioButton radioClient = new JRadioButton("Client", true);
	private JRadioButton radioMoniteur = new JRadioButton("Moniteur");
	private JRadioButton radioAdministrateur = new JRadioButton("Administrateur");
	private ButtonGroup buttonGroupUtilisateur = new ButtonGroup();
	private JButton buttonInscriptionClient = new JButton("Créer un compte client");
	private JButton buttonConnexion = new JButton("Connexion");

	public Connexion() {
		creationPanel();

		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Projet Java");
		Image img = new ImageIcon("icon.png").getImage();
		setIconImage(img);
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

	// METHODES D'AFFICHAGE DE L'ECRAN DE CONNEXION
	public void creationPanel() {
		panel.setBackground(Color.WHITE);
		panel.setOpaque(true);

		labelDomaine.setFont(new Font("SansSerif", Font.BOLD, 24));
		labelDomaine.setForeground(Color.WHITE);
		labelDomaine.setSize(210, 25);
		labelDomaine.setLocation(95, 100);

		textFieldAdresseMail.setSize(250, 30);
		textFieldAdresseMail.setLocation(75, 190);
		textFieldAdresseMail.setForeground(Color.LIGHT_GRAY);

		passwordFieldMotDePasse.setSize(250, 30);
		passwordFieldMotDePasse.setLocation(75, 240);
		passwordFieldMotDePasse.setForeground(Color.LIGHT_GRAY);
		passwordFieldMotDePasse.setEchoChar((char) 0);

		radioClient.setSize(55, 25);
		radioClient.setLocation(75, 290);

		radioMoniteur.setSize(70, 25);
		radioMoniteur.setLocation(135, 290);

		radioAdministrateur.setSize(105, 25);
		radioAdministrateur.setLocation(210, 290);

		buttonGroupUtilisateur.add(radioClient);
		buttonGroupUtilisateur.add(radioMoniteur);
		buttonGroupUtilisateur.add(radioAdministrateur);

		buttonConnexion.setSize(250, 40);
		buttonConnexion.setLocation(75, 340);
		buttonConnexion.setFont(new Font("SansSerif", Font.BOLD, 14));
		buttonConnexion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		buttonInscriptionClient.setSize(155, 30);
		buttonInscriptionClient.setLocation(0, 435);
		buttonInscriptionClient.setForeground(Color.BLUE);
		buttonInscriptionClient.setBorderPainted(false);
		buttonInscriptionClient.setContentAreaFilled(false);
		buttonInscriptionClient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Map<TextAttribute, Integer> attributes = new HashMap<TextAttribute, Integer>();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		buttonInscriptionClient.setFont(buttonInscriptionClient.getFont().deriveFont(attributes));

		panel.add(labelDomaine);
		panel.add(textFieldAdresseMail);
		panel.add(passwordFieldMotDePasse);
		panel.add(radioClient);
		panel.add(radioMoniteur);
		panel.add(radioAdministrateur);
		panel.add(buttonConnexion);
		panel.add(buttonInscriptionClient);
	}

	public void afficheInscrire() {
		buttonInscriptionClient.setText("Créer un compte client");
	}

	public void cacheInscrire() {
		buttonInscriptionClient.setText("");
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

	public void addFocusFieldListenr(FocusListener FocusFieldListener) {
		textFieldAdresseMail.addFocusListener(FocusFieldListener);
		passwordFieldMotDePasse.addFocusListener(FocusFieldListener);
	}

	// CLASSE PANEL PERSONNALISEE POUR AFFICHER UN BACKGROUND
	class MonPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MonPanel() {
			super(null);
		}

		@Override
		protected void paintComponent(Graphics g) {
			Image img = new ImageIcon("fondConnexion.png").getImage();
			g.drawImage(img, 0, 0, null);
		}
	}
}
