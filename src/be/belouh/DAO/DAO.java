package be.belouh.DAO;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
	// ATTRIBUTS
	protected Connection c = ConnectionDAO.getInstance();

	// METHODES
	public abstract ArrayList<Integer> compter();
	
	public abstract boolean supprimer(T obj);

	public abstract T inserer(T obj);

	public abstract T mettreAJour(T obj);

	public abstract T trouver(int id);
}
