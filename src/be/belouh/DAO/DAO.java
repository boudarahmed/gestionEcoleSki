package be.belouh.DAO;

import java.sql.Connection;

public abstract class DAO<T> {
	// ATTRIBUTS
	protected Connection c = ConnectionDAO.getInstance();

	// METHODES
	public abstract boolean supprimer(T obj);

	public abstract T inserer(T obj);

	public abstract T mettreAJour(T obj);

	public abstract T trouver(T obj);
}
