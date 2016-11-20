package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.POJO.Client;
import be.belouh.POJO.Cours;
import be.belouh.POJO.CoursCollectif;
import be.belouh.POJO.CoursParticulier;
import be.belouh.POJO.Eleve;
import be.belouh.POJO.Reservation;

public class ReservationDAO extends DAO<Reservation> {

	@Override
	public ArrayList<Integer> compter() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		String sql = "SELECT IDRESERVATION FROM RESERVATION";

		try {
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				tab.add(rs.getInt("IDRESERVATION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tab;
	}

	@Override
	public boolean supprimer(Reservation obj) {
		boolean res = false;
		String sqlReservation = "DELETE FROM RESERVATION WHERE IDRESERVATION = ?";
		String sqlLigneReservation = "DELETE FROM LIGNERESERVATION WHERE IDRESERVATION = ?";

		try {
			PreparedStatement stmtReservation = c.prepareStatement(sqlReservation);
			PreparedStatement stmtLigneReservation = c.prepareStatement(sqlLigneReservation);

			stmtReservation.setInt(1, obj.getId());
			stmtLigneReservation.setInt(1, obj.getId());

			stmtReservation.executeUpdate();
			stmtLigneReservation.executeUpdate();

			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Reservation inserer(Reservation obj) {
		String sql = "INSERT INTO RESERVATION (DATERESERVATION, STATUTRESERVATION, PRIX, IDCLIENT, IDELEVE) VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDateReservation())));
			stmt.setString(2, obj.getStatutReservation());
			stmt.setDouble(3, obj.getPrix());
			stmt.setInt(4, obj.getClient().getId());
			stmt.setInt(5, obj.getEleve().getId());

			stmt.executeUpdate();

			try {
				ResultSet generatedKeys = stmt.getGeneratedKeys();

				if (generatedKeys.next()) {
					obj.setId(generatedKeys.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	@Override
	public Reservation mettreAJour(Reservation obj) {
		DAO<CoursCollectif> cl = new CoursCollectifDAO();
		DAO<CoursParticulier> cp = new CoursParticulierDAO();
		
		ArrayList<Cours> listeC = new ArrayList<Cours>();
		
		String sqlReservation = "UPDATE RESERVATION SET DATERESERVATION = ?, STATUTRESERVATION = ?, PRIX = ?, IDCLIENT = ?, IDELEVE = ? WHERE IDRESERVATION = ?";
		String sqlCours = "SELECT COURS.IDCOURS FROM LIGNERESERVATION JOIN COURS ON LIGNERESERVATION.IDCOURS = COURS.IDCOURS WHERE LIGNERESERVATION.IDRESERVATION = ?";

		try {
			PreparedStatement stmtReservation = c.prepareStatement(sqlReservation);
			PreparedStatement stmtCours = c.prepareStatement(sqlCours);

			stmtReservation.setString(1, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDateReservation())));
			stmtReservation.setString(2, obj.getStatutReservation());
			stmtReservation.setDouble(3, obj.getPrix());
			stmtReservation.setInt(4, obj.getClient().getId());
			stmtReservation.setInt(5, obj.getEleve().getId());
			stmtReservation.setInt(6, obj.getId());
			stmtCours.setInt(1, obj.getId());

			stmtReservation.executeUpdate();
			ResultSet rs = stmtCours.executeQuery();
			
			while (rs.next()) {
				int  idcours = rs.getInt("IDCOURS");
				
				if(cl.trouver(idcours).getId() != 0)
					listeC.add(cl.trouver(idcours));
				else
					listeC.add(cp.trouver(idcours));
			}
			
			ArrayList<Cours> listetemp = new ArrayList<Cours>(obj.getListeCours());
			
			listetemp.removeAll(listeC);
			Iterator<Cours> itCours = listetemp.iterator();
			while (itCours.hasNext()) {
				String sqlLigneReservation = "INSERT INTO LIGNERESERVATION VALUES (?, ?)";
				try {
					PreparedStatement stmtLigneReservation = c.prepareStatement(sqlLigneReservation);

					stmtLigneReservation.setInt(1, obj.getId());
					stmtLigneReservation.setInt(2, itCours.next().getId());

					stmtLigneReservation.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Reservation trouver(int id) {
		DAO<CoursCollectif> cl = new CoursCollectifDAO();
		DAO<CoursParticulier> cp = new CoursParticulierDAO();
		DAO<Client> cli = new ClientDAO();
		DAO<Eleve> el = new EleveDAO();
		
		ArrayList<Cours> listeC = new ArrayList<Cours>();

		String sqlReservation = "SELECT * FROM RESERVATION WHERE IDRESERVATION = ?";
		String sqlCours = "SELECT COURS.IDCOURS FROM LIGNERESERVATION JOIN COURS ON LIGNERESERVATION.IDCOURS = COURS.IDCOURS WHERE LIGNERESERVATION.IDRESERVATION = ?";

		Reservation obj = new Reservation();
		try {
			PreparedStatement stmtReservation = c.prepareStatement(sqlReservation);
			PreparedStatement stmtCours = c.prepareStatement(sqlCours);

			stmtReservation.setInt(1, id);

			ResultSet rs = stmtReservation.executeQuery();

			if (rs.next()) {
				obj.setId(rs.getInt("IDRESERVATION"));
				obj.setDateReservation(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("DATERESERVATION")));
				obj.setStatutReservation(rs.getString("STATUTRESERVATION"));
				obj.setPrix(rs.getDouble("PRIX"));
				obj.setClient(cli.trouver(rs.getInt("IDCLIENT")));
				obj.setEleve(el.trouver(rs.getInt("IDELEVE")));
				
				stmtCours.setInt(1, obj.getId());

				rs = stmtCours.executeQuery();

				while (rs.next()) {
					int  idcours = rs.getInt("IDCOURS");
					
					if(cl.trouver(idcours).getId() != 0)
						listeC.add(cl.trouver(idcours));
					else
						listeC.add(cp.trouver(idcours));
				}
				
				obj.setListeCours(listeC);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
