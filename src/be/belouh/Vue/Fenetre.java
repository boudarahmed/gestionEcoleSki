package be.belouh.Vue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class Fenetre extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void afficheMessage(String msg, String title, int type) {
		JOptionPane.showMessageDialog(this, msg, title, type);
	}

	public boolean demande(String text) {
		if (JOptionPane.showConfirmDialog(this, text) == 0)
			return true;
		else
			return false;
	}

	public Object Chosis(Object[] valeurs) {
		return JOptionPane.showInputDialog(this, "Semaine du ", "Choisir une semaine", JOptionPane.PLAIN_MESSAGE, null,
				valeurs, valeurs[0]);
	}
}
