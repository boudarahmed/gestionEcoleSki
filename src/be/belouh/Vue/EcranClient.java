package be.belouh.Vue;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import be.belouh.POJO.Eleve;

public class EcranClient extends Fenetre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel(null);
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Client");
	private JMenu menu2 = new JMenu("Réservation");
	private JMenu menu3 = new JMenu("Élève");
	private JMenuItem menu1Item1 = new JMenuItem("Déconnexion");
	private JMenuItem menu2Item1 = new JMenuItem("Effectuer une nouvelle réservation");
	private JMenuItem menu3Item1 = new JMenuItem("Ajouter un élève");
	private JMenuItem menu3Item2 = new JMenuItem("Afficher ma liste d'élèves");
	private JComboBox<Eleve> comboEleve = new JComboBox<Eleve>();
	private JRadioButton radioCommander = new JRadioButton("Réservations commandées", true);
	private JRadioButton radioReserver = new JRadioButton("Réservations réservées");
	private JRadioButton radioPayer = new JRadioButton("Réservations payées");
	private ButtonGroup buttonGroupStatut = new ButtonGroup();
	private DefaultTableModel modele = new DefaultTableModel();
	private JTable listeReservation = new JTable(modele);
	private JScrollPane scrollPanel = new JScrollPane(listeReservation);
	private JLabel labelInfo = new JLabel("");

	public EcranClient() {
		creationPanel();

		setJMenuBar(menuBar);
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Projet Java");
		Image img = new ImageIcon("icon.png").getImage();
		setIconImage(img);
		setVisible(true);

	}

	// RECUPERATION COMPOSANT DE L'ECRAN CLIENT
	public JComboBox<Eleve> getComboEleve() {
		return comboEleve;
	}
	
	public String getRadioStatut() {
		if (radioCommander.isSelected())
			return radioCommander.getText();
		else if (radioReserver.isSelected())
			return radioReserver.getText();
		else
			return radioPayer.getText();
	}

	// METHODES D'AFFICHAGE DE L'ECRAN CLIENT
	public void creationPanel() {
		panel.setOpaque(true);

		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menu1.add(menu1Item1);
		menu2.add(menu2Item1);
		menu3.add(menu3Item1);
		menu3.add(menu3Item2);
		menu1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu1Item1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2Item1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu3Item1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu3Item2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		comboEleve.setSize(180, 30);
		comboEleve.setLocation(10, 25);

		radioCommander.setSize(180, 25);
		radioCommander.setLocation(10, 75);

		radioReserver.setSize(180, 25);
		radioReserver.setLocation(10, 110);

		radioPayer.setSize(180, 25);
		radioPayer.setLocation(10, 145);

		buttonGroupStatut.add(radioCommander);
		buttonGroupStatut.add(radioReserver);
		buttonGroupStatut.add(radioPayer);

		listeReservation.setRowHeight(25);

		scrollPanel.setSize(600, 530);
		scrollPanel.setLocation(200, -3);

		labelInfo.setFont(new Font("SansSerif", Font.BOLD, 11));
		labelInfo.setSize(600, 30);
		labelInfo.setLocation(205, 520);

		panel.add(comboEleve);
		panel.add(radioCommander);
		panel.add(radioReserver);
		panel.add(radioPayer);
		panel.add(scrollPanel);
		panel.add(labelInfo);
	}
	
	public void afficherliste(Object[][] data, String[] title, String label) {
		labelInfo.setText(label);
		modele.setDataVector(data, title);
		modele.fireTableDataChanged();
	}

	// LISTENER DE L'ECRAN CLIENT
	public void addmenu1Item1Listener(ActionListener menu1Item1Listener) {
		menu1Item1.addActionListener(menu1Item1Listener);
	}

	public void addmenu2Item1Listener(ActionListener menu2Item1Listener) {
		menu2Item1.addActionListener(menu2Item1Listener);
	}

	public void addmenu3Item1Listener(ActionListener menu3Item1Listener) {
		menu3Item1.addActionListener(menu3Item1Listener);
	}

	public void addmenu3Item2Listener(ActionListener menu3Item2Listener) {
		menu3Item2.addActionListener(menu3Item2Listener);
	}

	public void addComboEleveListener(ItemListener comboEleveListener) {
		comboEleve.addItemListener(comboEleveListener);
	}

	public void addbuttonRadioListener(ActionListener buttonRadioListener) {
		radioCommander.addActionListener(buttonRadioListener);
		radioReserver.addActionListener(buttonRadioListener);
		radioPayer.addActionListener(buttonRadioListener);
	}

	public void addListeListener(ListSelectionListener listeListener) {
		listeReservation.getSelectionModel().addListSelectionListener(listeListener);
	}
}
