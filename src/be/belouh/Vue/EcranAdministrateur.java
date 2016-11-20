package be.belouh.Vue;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class EcranAdministrateur extends Fenetre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel(null);
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Administrateur");
	private JMenu menu2 = new JMenu("Moniteur");
	private JMenu menu3 = new JMenu("Cours");
	private JMenuItem menu1Item1 = new JMenuItem("Déconnexion");
	private JMenuItem menu2Item1 = new JMenuItem("Afficher la liste des moniteurs");
	private JMenuItem menu2Item2 = new JMenuItem("Ajouter un moniteur");
	private JMenuItem menu2Item3 = new JMenuItem("Ajouter une indisponibilité");
	private JMenuItem menu2Item4 = new JMenuItem("Ajouter une accreditation");
	private JMenuItem menu3Item1 = new JMenuItem("Afficher les cours collectifs");
	private JMenuItem menu3Item2 = new JMenuItem("Afficher les cours particuliers");
	private DefaultTableModel modele = new DefaultTableModel();
	private JTable liste = new JTable(modele);
	private JScrollPane scrollPanel = new JScrollPane(liste);
	private JLabel labelInfo = new JLabel("");

	public EcranAdministrateur() {
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

	// METHODES D'AFFICHAGE DE L'ECRAN MONITEUR
	public void creationPanel() {
		panel.setOpaque(true);

		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menu1.add(menu1Item1);
		menu2.add(menu2Item1);
		menu2.add(menu2Item2);
		menu2.add(menu2Item3);
		menu2.add(menu2Item4);
		menu3.add(menu3Item1);
		menu3.add(menu3Item2);
		menu1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu1Item1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2Item1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2Item2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2Item3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu3Item1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu3Item2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2Item4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		liste.setRowHeight(25);

		scrollPanel.setSize(800, 530);
		scrollPanel.setLocation(-3, -3);

		labelInfo.setFont(new Font("SansSerif", Font.BOLD, 11));
		labelInfo.setSize(350, 30);
		labelInfo.setLocation(5, 520);

		panel.add(scrollPanel);
		panel.add(labelInfo);
	}

	public void afficherliste(Object[][] data, String[] title, String label) {
		labelInfo.setText(label);
		modele.setDataVector(data, title);
		modele.fireTableDataChanged();
	}

	// LISTENER DE L'ECRAN MONITEUR
	public void addmenu1Item1Listener(ActionListener menu1Item1Listener) {
		menu1Item1.addActionListener(menu1Item1Listener);
	}

	public void addmenu2Item1Listener(ActionListener menu2Item1Listener) {
		menu2Item1.addActionListener(menu2Item1Listener);
	}

	public void addmenu2Item2Listener(ActionListener menu2Item2Listener) {
		menu2Item2.addActionListener(menu2Item2Listener);
	}

	public void addmenu2Item3Listener(ActionListener menu2Item3Listener) {
		menu2Item3.addActionListener(menu2Item3Listener);
	}
	
	public void addmenu2Item4Listener(ActionListener menu2Item4Listener) {
		menu2Item4.addActionListener(menu2Item4Listener);
	}

	public void addmenu3Item1Listener(ActionListener menu3Item1Listener) {
		menu3Item1.addActionListener(menu3Item1Listener);
	}

	public void addmenu3Item2Listener(ActionListener menu3Item2Listener) {
		menu3Item2.addActionListener(menu3Item2Listener);
	}

	public void addListeListener(ListSelectionListener listeListener) {
		liste.getSelectionModel().addListSelectionListener(listeListener);
	}

}
