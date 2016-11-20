package be.belouh.Vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FenetreModaleInscriptionEleve extends Modale {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel(null);
	private JTextField textFieldNom = new JTextField("Nom");
	private JTextField textFieldPrenom = new JTextField("Prénom");
	private JTextField textFieldNumero = new JTextField("Numéro");
	private JTextField textFieldRue = new JTextField("Rue");
	private JTextField textFieldCodePostal = new JTextField("Code postal");
	private JTextField textFieldVille = new JTextField("Ville");
	private String[] items = { "M.", "Mme" };
	private JComboBox<String> comboSexe = new JComboBox<String>(items);
	private UtilDateModel modelDateNaissance = new UtilDateModel();
	private JDatePanelImpl datePanelDateNaissance = new JDatePanelImpl(modelDateNaissance);
	private JDatePickerImpl datePickerDateNaissance = new JDatePickerImpl(datePanelDateNaissance);
	private JCheckBox checkAssurance = new JCheckBox("Assurance");
	private JButton buttonValider = new JButton("Ajouter");
	private JButton buttonAnnuler = new JButton("Annuler");

	public FenetreModaleInscriptionEleve(Fenetre f, String title) {
		super(f, title);

		creationPanel();

		setContentPane(panel);
		setSize(600, 285);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	// RECUPERATION DES CHAMPS MODALE AJOUT ELEVE
	public String getNom() {
		return textFieldNom.getText();
	}

	public String getPrenom() {
		return textFieldPrenom.getText();
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

	public boolean getAssurance() {
		return checkAssurance.isSelected();
	}

	// METHODES D'AFFICHAGE MODALE AJOUT ELEVE
	public void creationPanel() {
		panel.setOpaque(true);

		textFieldNom.setForeground(Color.LIGHT_GRAY);
		textFieldNom.setSize(265, 30);
		textFieldNom.setLocation(30, 25);

		textFieldPrenom.setForeground(Color.LIGHT_GRAY);
		textFieldPrenom.setSize(265, 30);
		textFieldPrenom.setLocation(305, 25);

		textFieldNumero.setForeground(Color.LIGHT_GRAY);
		textFieldNumero.setSize(65, 30);
		textFieldNumero.setLocation(30, 60);

		textFieldRue.setForeground(Color.LIGHT_GRAY);
		textFieldRue.setSize(465, 30);
		textFieldRue.setLocation(105, 60);

		textFieldCodePostal.setForeground(Color.LIGHT_GRAY);
		textFieldCodePostal.setSize(100, 30);
		textFieldCodePostal.setLocation(30, 95);

		textFieldVille.setForeground(Color.LIGHT_GRAY);
		textFieldVille.setSize(430, 30);
		textFieldVille.setLocation(140, 95);

		comboSexe.setSize(60, 30);
		comboSexe.setLocation(30, 130);

		datePickerDateNaissance.setSize(250, 30);
		datePickerDateNaissance.setLocation(100, 132);

		checkAssurance.setSize(100, 30);
		checkAssurance.setLocation(360, 132);

		buttonValider.setSize(100, 40);
		buttonValider.setLocation(470, 185);
		buttonValider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		buttonAnnuler.setSize(100, 40);
		buttonAnnuler.setLocation(360, 185);
		buttonAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		panel.add(textFieldNom);
		panel.add(textFieldPrenom);
		panel.add(textFieldNumero);
		panel.add(textFieldRue);
		panel.add(textFieldCodePostal);
		panel.add(textFieldVille);
		panel.add(comboSexe);
		panel.add(datePickerDateNaissance);
		panel.add(checkAssurance);
		panel.add(buttonValider);
		panel.add(buttonAnnuler);
	}

	// LISTENER MODALE AJOUT ELEVE
	public void addbuttonValiderListener(ActionListener ButtonValiderListener) {
		buttonValider.addActionListener(ButtonValiderListener);
	}

	public void addbuttonAnnulerListener(ActionListener ButtonAnnulerListener) {
		buttonAnnuler.addActionListener(ButtonAnnulerListener);
	}

	public void addFocusFieldListenr(FocusListener FocusFieldListener) {
		textFieldNom.addFocusListener(FocusFieldListener);
		textFieldPrenom.addFocusListener(FocusFieldListener);
		textFieldNumero.addFocusListener(FocusFieldListener);
		textFieldRue.addFocusListener(FocusFieldListener);
		textFieldCodePostal.addFocusListener(FocusFieldListener);
		textFieldVille.addFocusListener(FocusFieldListener);
	}
}
