package be.belouh.Vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import be.belouh.POJO.Horaire;
import be.belouh.POJO.Semaine;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FenetreModaleReservation extends Modale {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel(null);
	private JLabel sport = new JLabel("Sport");
	private JComboBox<String> comboSport = new JComboBox<String>();
	private JLabel niveau = new JLabel("Niveau");
	private JComboBox<String> comboNiveau = new JComboBox<String>();
	private JLabel semaine = new JLabel("Semaine");
	private JComboBox<Semaine> comboSemaine = new JComboBox<Semaine>();
	private JLabel horaire = new JLabel("Horaire");
	private JComboBox<Horaire> comboHoraire = new JComboBox<Horaire>();
	private JLabel date = new JLabel("Date");
	private UtilDateModel modelDateCours = new UtilDateModel();
	private JDatePanelImpl datePanelDateCours = new JDatePanelImpl(modelDateCours);
	private JDatePickerImpl datePickerDateCours = new JDatePickerImpl(datePanelDateCours);
	private JButton buttonValider = new JButton("Ajouter");
	private JButton buttonAnnuler = new JButton("Annuler");

	public FenetreModaleReservation(Fenetre f, String title, boolean coursCollectif) {
		super(f, title);

		if (coursCollectif)
			creationPanelCoursCollectif();
		else
			creationPanelCoursParticulier();

		setContentPane(panel);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	// RECUPERATION DES CHAMPS MODALE AJOUT COURS
	public JComboBox<String> getComboSport() {
		return comboSport;
	}

	public JComboBox<String> getComboNiveau() {
		return comboNiveau;
	}

	public JComboBox<Semaine> getComboSemaine() {
		return comboSemaine;
	}

	public JComboBox<Horaire> getComboHoraire() {
		return comboHoraire;
	}

	public String getSport() {
		return (String) comboSport.getSelectedItem();
	}

	public String getNiveau() {
		return (String) comboNiveau.getSelectedItem();
	}

	public Semaine getSemaine() {
		return (Semaine) comboSemaine.getSelectedItem();
	}

	public Horaire getHoraire() {
		return (Horaire) comboHoraire.getSelectedItem();
	}

	public Date getDateCours() {
		return (Date) datePickerDateCours.getModel().getValue();
	}

	// METHODES D'AFFICHAGE MODALE AJOUT COURS
	public void creationPanelCoursCollectif() {
		panel.setOpaque(true);

		sport.setSize(40, 25);
		sport.setLocation(79, 35);
		sport.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sport.setForeground(Color.BLACK);

		comboSport.setSize(200, 30);
		comboSport.setLocation(125, 35);

		niveau.setSize(50, 25);
		niveau.setLocation(70, 70);
		niveau.setFont(new Font("SansSerif", Font.PLAIN, 14));
		niveau.setForeground(Color.BLACK);

		comboNiveau.setSize(200, 30);
		comboNiveau.setLocation(125, 70);

		semaine.setSize(60, 25);
		semaine.setLocation(60, 105);
		semaine.setFont(new Font("SansSerif", Font.PLAIN, 14));
		semaine.setForeground(Color.BLACK);

		comboSemaine.setSize(200, 30);
		comboSemaine.setLocation(125, 105);

		horaire.setSize(50, 25);
		horaire.setLocation(67, 140);
		horaire.setFont(new Font("SansSerif", Font.PLAIN, 14));
		horaire.setForeground(Color.BLACK);

		comboHoraire.setSize(200, 30);
		comboHoraire.setLocation(125, 140);

		buttonValider.setSize(100, 40);
		buttonValider.setLocation(205, 200);
		buttonValider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		buttonAnnuler.setSize(100, 40);
		buttonAnnuler.setLocation(105, 200);
		buttonAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		panel.add(sport);
		panel.add(comboSport);
		panel.add(niveau);
		panel.add(comboNiveau);
		panel.add(semaine);
		panel.add(comboSemaine);
		panel.add(horaire);
		panel.add(comboHoraire);
		panel.add(buttonValider);
		panel.add(buttonAnnuler);
	}

	public void creationPanelCoursParticulier() {
		panel.setOpaque(true);

		sport.setSize(40, 25);
		sport.setLocation(79, 35);
		sport.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sport.setForeground(Color.BLACK);

		comboSport.setSize(200, 30);
		comboSport.setLocation(125, 35);

		date.setSize(40, 25);
		date.setLocation(81, 70);
		date.setFont(new Font("SansSerif", Font.PLAIN, 14));
		date.setForeground(Color.BLACK);

		datePickerDateCours.setSize(200, 30);
		datePickerDateCours.setLocation(125, 70);

		horaire.setSize(50, 25);
		horaire.setLocation(67, 105);
		horaire.setFont(new Font("SansSerif", Font.PLAIN, 14));
		horaire.setForeground(Color.BLACK);

		comboHoraire.setSize(200, 30);
		comboHoraire.setLocation(125, 105);

		buttonValider.setSize(100, 40);
		buttonValider.setLocation(205, 200);
		buttonValider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		buttonAnnuler.setSize(100, 40);
		buttonAnnuler.setLocation(105, 200);
		buttonAnnuler.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		panel.add(sport);
		panel.add(comboSport);
		panel.add(date);
		panel.add(datePickerDateCours);
		panel.add(horaire);
		panel.add(comboHoraire);
		panel.add(buttonValider);
		panel.add(buttonAnnuler);
	}

	// LISTENER DE LA MODALE RESERVATION
	public void addbuttonValiderListener(ActionListener ButtonValiderListener) {
		buttonValider.addActionListener(ButtonValiderListener);
	}

	public void addbuttonAnnulerListener(ActionListener ButtonAnnulerListener) {
		buttonAnnuler.addActionListener(ButtonAnnulerListener);
	}

	public void addComboSportListener(ItemListener comboSportListener) {
		comboSport.addItemListener(comboSportListener);
	}
}
