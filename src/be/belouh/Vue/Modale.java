package be.belouh.Vue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public abstract class Modale extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Modale(Fenetre f, String title) {
		super(f, title, true);
	}

	public void afficheMessage(String msg, String title, int type) {
		JOptionPane.showMessageDialog(this, msg, title, type);
	}

	public boolean demande(String text) {
		if (JOptionPane.showConfirmDialog(this, text) == 0)
			return true;
		else
			return false;
	}
}
