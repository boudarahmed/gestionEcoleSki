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

public class EcranMoniteur extends Fenetre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel(null);
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu1 = new JMenu("Moniteur");
	private JMenu menu2 = new JMenu("Cours");
	private JMenu menu3 = new JMenu("Semaine");
	private JMenuItem menu1Item1 = new JMenuItem("Déconnexion");
	private JMenuItem menu2Item1 = new JMenuItem("Cours collectif");
	private JMenuItem menu2Item2 = new JMenuItem("Cours particulier");
	private JMenuItem menu3Item1 = new JMenuItem("Choisir une semaine");
	private DefaultTableModel modele = new DefaultTableModel();
	private JTable listeCours = new JTable(modele);
	private JScrollPane scrollPanel = new JScrollPane(listeCours);
	private JLabel labelInfo = new JLabel("");

	public EcranMoniteur() {
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

	// RECUPERATION DONNEES DE L'ECRAN MONITEUR
	public Object[] getDataSelection(int l, int c) {
		Object[] res = new Object[c];
		for(int i = 0; i < c; i++){
			res[i] = listeCours.getModel().getValueAt(l, i);
		}
		return res;
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
		menu3.add(menu3Item1);
		menu1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu1Item1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2Item1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu2Item2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu3Item1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		listeCours.setRowHeight(25);

		scrollPanel.setSize(800, 530);
		scrollPanel.setLocation(-3, -3);

		labelInfo.setFont(new Font("SansSerif", Font.BOLD, 11));
		labelInfo.setSize(350, 30);
		labelInfo.setLocation(440, 520);

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

	public void addmenu3Item1Listener(ActionListener menu3Item1Listener) {
		menu3Item1.addActionListener(menu3Item1Listener);
	}

	public void addListeListener(ListSelectionListener listeListener) {
		listeCours.getSelectionModel().addListSelectionListener(listeListener);
	}
}
