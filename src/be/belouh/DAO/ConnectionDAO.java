package be.belouh.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionDAO {
	private static String url = "jdbc:sqlite:DataBase.db";
	private static Connection c = null;

	private ConnectionDAO() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Impossible de trouver le driver pour la base de donn�es!");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Impossible de se connecter � la base de donn�es!");
		}
		
		if(c==null){
			JOptionPane.showMessageDialog(null, "La base de donn�es est innaccessible, fermeture du programme");
			System.exit(0);
		}
	}
	
	public static Connection getInstance(){
		if(c == null)
			new ConnectionDAO();
		return c;
	}
}
