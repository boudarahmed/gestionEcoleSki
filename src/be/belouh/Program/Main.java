package be.belouh.Program;

import java.util.Calendar;

import be.belouh.DAO.ClientDAO;
import be.belouh.DAO.DAO;
import be.belouh.DAO.EleveDAO;
import be.belouh.POJO.Client;
import be.belouh.POJO.Eleve;

public class Main {
	public static void main(String[] args) {
		//new Controlleur(new Fenetre());
		Calendar cal = Calendar.getInstance();
		cal.set(1981, Calendar.JANUARY, 13);
		
		DAO<Eleve> el = new EleveDAO();
		DAO<Client> cl = new ClientDAO();
		
		Client c = cl.trouver(11);
		
		Eleve e = new Eleve();
		e.setNom("Pellerin");
		e.setPrenom("Agate");
		e.setDateNaissance(cal.getTime());
		e.setSexe('F');
		e.setVille("Melden");
		e.setCodePostal("9700");
		e.setNumero("376");
		e.setRue("Hauwaart");
		e.setAssurance(true);
		e.setClient(c);
		
		el.inserer(e);
	}
}
