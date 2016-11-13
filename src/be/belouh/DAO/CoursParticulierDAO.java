package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import be.belouh.POJO.Accreditation;
import be.belouh.POJO.CoursParticulier;
import be.belouh.POJO.Horaire;
import be.belouh.POJO.Moniteur;

public class CoursParticulierDAO extends DAO<CoursParticulier> {

	@Override
	public ArrayList<Integer> compter() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		String sql = "SELECT COURS.IDCOURS FROM COURS JOIN COURSPARTICULIER ON COURS.IDCOURS = COURSPARTICULIER.IDCOURSPARTICULIER";

		try {
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				tab.add(rs.getInt("IDCOURS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tab;
	}

	@Override
	public boolean supprimer(CoursParticulier obj) {
		boolean res = false;
		String sqlCoursParticulier = "DELETE FROM COURSPARTICULIER WHERE IDCOURSPARTICULIER = ?";
		String sqlCours = "DELETE FROM COURS WHERE IDCOURS = ?";

		try {
			PreparedStatement stmtCoursParticulier = c.prepareStatement(sqlCoursParticulier);
			PreparedStatement stmtCours = c.prepareStatement(sqlCours);

			stmtCoursParticulier.setInt(1, obj.getId());
			stmtCours.setInt(1, obj.getId());

			stmtCoursParticulier.executeUpdate();
			stmtCours.executeUpdate();

			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public CoursParticulier inserer(CoursParticulier obj) {
		String sqlCours = "INSERT INTO COURS (STATUTCOURS, IDMONITEUR, IDHORAIRE) VALUES (?, ?, ?)";
		String sqlCoursParticulier = "INSERT INTO COURSPARTICULIER VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmtCours = c.prepareStatement(sqlCours, Statement.RETURN_GENERATED_KEYS);

			stmtCours.setString(1, obj.getStatutCours());
			stmtCours.setInt(2, obj.getMoniteur().getId());
			stmtCours.setInt(3, obj.getHoraire().getId());

			stmtCours.executeUpdate();

			try {
				ResultSet generatedKeys = stmtCours.getGeneratedKeys();

				if (generatedKeys.next()) {
					obj.setId(generatedKeys.getInt(1));

					PreparedStatement stmtCoursParticulier = c.prepareStatement(sqlCoursParticulier);

					stmtCoursParticulier.setInt(1, obj.getId());
					stmtCoursParticulier.setString(2, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDate())));
					stmtCoursParticulier.setString(3, String.valueOf(obj.getCongeScolaire()));
					stmtCoursParticulier.setDouble(4, obj.getPrix());
					stmtCoursParticulier.setInt(5, obj.getAccreditation().getId());

					stmtCoursParticulier.executeUpdate();
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
	public CoursParticulier mettreAJour(CoursParticulier obj) {
		String sqlCours = "UPDATE COURS SET STATUTCOURS = ?, IDMONITEUR = ?, IDHORAIRE = ? WHERE IDCOURS = ?";
		String sqlCoursParticulier = "UPDATE COURSPARTICULIER SET DATE = ?, CONGESCOLAIRE = ?, PRIX = ?, IDACCREDITATION = ? WHERE IDCOURSPARTICULIER = ?";

		try {
			PreparedStatement stmtCours = c.prepareStatement(sqlCours);
			PreparedStatement stmtCoursParticulier = c.prepareStatement(sqlCoursParticulier);

			stmtCours.setString(1, obj.getStatutCours());
			stmtCours.setInt(2, obj.getMoniteur().getId());
			stmtCours.setInt(3, obj.getHoraire().getId());
			stmtCours.setInt(4, obj.getId());
			stmtCoursParticulier.setString(1, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDate())));
			stmtCoursParticulier.setString(2, String.valueOf(obj.getCongeScolaire()));
			stmtCoursParticulier.setDouble(3, obj.getPrix());
			stmtCoursParticulier.setInt(4, obj.getAccreditation().getId());
			stmtCoursParticulier.setInt(5, obj.getId());

			stmtCours.executeUpdate();
			stmtCoursParticulier.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public CoursParticulier trouver(int id) {
		DAO<Moniteur> m = new MoniteurDAO();
		DAO<Horaire> h = new HoraireDAO();
		DAO<Accreditation> a = new AccreditationDAO();

		String sql = "SELECT COURS.IDCOURS, COURS.STATUTCOURS, COURS.IDMONITEUR, COURS.IDHORAIRE, COURSPARTICULIER.DATE, COURSPARTICULIER.CONGESCOLAIRE, COURSPARTICULIER.PRIX, COURSPARTICULIER.IDACCREDITATION FROM COURS JOIN COURSPARTICULIER ON COURS.IDCOURS = COURSPARTICULIER.IDCOURSPARTICULIER WHERE COURS.IDCOURS = ?";

		CoursParticulier obj = new CoursParticulier();
		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				obj.setId(rs.getInt("IDCOURS"));
				obj.setStatutCours(rs.getString("STATUTCOURS"));
				obj.setMoniteur(m.trouver(rs.getInt("IDMONITEUR")));
				obj.setHoraire(h.trouver(rs.getInt("IDHORAIRE")));
				obj.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("DATE")));
				obj.setCongeScolaire(Boolean.parseBoolean(rs.getString("CONGESCOLAIRE")));
				obj.setPrix(rs.getDouble("PRIX"));
				obj.setAccreditation(a.trouver(rs.getInt("IDACCREDITATION")));
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
