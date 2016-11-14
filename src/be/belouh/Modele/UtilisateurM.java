package be.belouh.Modele;

import be.belouh.POJO.Utilisateur;

public abstract class UtilisateurM {
	protected Utilisateur utilisateur;

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public abstract boolean connexion();
}
