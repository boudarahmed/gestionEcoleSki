package be.belouh.Program;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import be.belouh.Controlleur.ControlleurConnexion;

public class Main {
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// Si nimbus n'est pas disponible on laisse le theme par defaut
		}
		new ControlleurConnexion();
	}
}
