package be.belouh.Vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JPanel panelConnexion = new JPanel();
	private GridBagLayout layoutConnexion = new GridBagLayout();
	private GridBagConstraints gbcConnexion = new GridBagConstraints();
	private JTextField textFieldAdresseMail = new JTextField(20);
	private JPasswordField passwordFieldMotDePasse = new JPasswordField(20);
	private JButton buttonConnexion = new JButton("Connexion");

	public Fenetre() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.panelConnexion.setLayout(layoutConnexion);
		
		gbcConnexion.insets = new Insets(5, 5, 5, 5);
		this.panelConnexion.setLayout(new GridBagLayout());
		this.panelConnexion.add(textFieldAdresseMail);
		this.panelConnexion.add(passwordFieldMotDePasse);
		this.panelConnexion.add(buttonConnexion);
		this.panel.add(panelConnexion);
		this.setContentPane(panel);
		this.setVisible(true);
	}

	public String getAdresseMail() {
		return textFieldAdresseMail.getText();
	}

	public String getMotDePasse() {
		return String.valueOf(passwordFieldMotDePasse.getPassword());
	}
	public void videEcran(){
		panel.removeAll();
		panel.updateUI();
	}
	public void afficheErreurConnexion(){
		JOptionPane.showMessageDialog(this, "Echec de la connexion");
	}
	
	public void addbuttonConnexionListener(ActionListener ButtonConnexionlistener) {
		buttonConnexion.addActionListener(ButtonConnexionlistener);
	}
}
