package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.belouh.POJO.CoursCollectif;
import be.belouh.POJO.Horaire;
import be.belouh.POJO.Moniteur;
import be.belouh.POJO.Semaine;
import be.belouh.POJO.TypeCours;

public class CoursCollectifDAO extends DAO<CoursCollectif> {

	@Override
	public ArrayList<Integer> compter() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		String sql = "SELECT COURS.IDCOURS FROM COURS JOIN COURSCOLLECTIF ON COURS.IDCOURS = COURSCOLLECTIF.IDCOURSCOLLECTIF";

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
	public boolean supprimer(CoursCollectif obj) {
		boolean res = false;
		String sqlCoursCollectif = "DELETE FROM COURSCOLLECTIF WHERE IDCOURSCOLLECTIF = ?";
		String sqlCours = "DELETE FROM COURS WHERE IDCOURS = ?";

		try {
			PreparedStatement stmtCoursCollectif = c.prepareStatement(sqlCoursCollectif);
			PreparedStatement stmtCours = c.prepareStatement(sqlCours);

			stmtCoursCollectif.setInt(1, obj.getId());
			stmtCours.setInt(1, obj.getId());

			stmtCoursCollectif.executeUpdate();
			stmtCours.executeUpdate();

			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public CoursCollectif inserer(CoursCollectif obj) {
		String sqlCours = "INSERT INTO COURS (STATUTCOURS, IDMONITEUR, IDHORAIRE) VALUES (?, ?, ?)";
		String sqlCoursCollectif = "INSERT INTO COURSCOLLECTIF VALUES (?, ?, ?)";

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

					PreparedStatement stmtCoursCollectif = c.prepareStatement(sqlCoursCollectif);

					stmtCoursCollectif.setInt(1, obj.getId());
					stmtCoursCollectif.setInt(2, obj.getSemaine().getId());
					stmtCoursCollectif.setInt(3, obj.getTypeCours().getId());

					stmtCoursCollectif.executeUpdate();
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
	public CoursCollectif mettreAJour(CoursCollectif obj) {
		String sqlCours = "UPDATE COURS SET STATUTCOURS = ?, IDMONITEUR = ?, IDHORAIRE = ? WHERE IDCOURS = ?";
		String sqlCoursCollectif = "UPDATE COURSCOLLECTIF SET IDSEMAINE = ?, IDTYPECOURS = ? WHERE IDCOURSCOLLECTIF = ?";

		try {
			PreparedStatement stmtCours = c.prepareStatement(sqlCours);
			PreparedStatement stmtCoursCollectif = c.prepareStatement(sqlCoursCollectif);

			stmtCours.setString(1, obj.getStatutCours());
			stmtCours.setInt(2, obj.getMoniteur().getId());
			stmtCours.setInt(3, obj.getHoraire().getId());
			stmtCours.setInt(4, obj.getId());
			stmtCoursCollectif.setInt(1, obj.getSemaine().getId());
			stmtCoursCollectif.setInt(2, obj.getTypeCours().getId());
			stmtCoursCollectif.setInt(3, obj.getId());

			stmtCours.executeUpdate();
			stmtCoursCollectif.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public CoursCollectif trouver(int id) {
		DAO<Moniteur> m = new MoniteurDAO();
		DAO<Horaire> h = new HoraireDAO();
		DAO<Semaine> s = new SemaineDAO();
		DAO<TypeCours> t = new TypeCoursDAO();
		
		String sql = "SELECT COURS.IDCOURS, COURS.STATUTCOURS, COURS.IDMONITEUR, COURS.IDHORAIRE, COURSCOLLECTIF.IDSEMAINE, COURSCOLLECTIF.IDTYPECOURS FROM COURS JOIN COURSCOLLECTIF ON COURS.IDCOURS = COURSCOLLECTIF.IDCOURSCOLLECTIF WHERE COURS.IDCOURS = ?";
		
		CoursCollectif obj = new CoursCollectif();
		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				obj.setId(rs.getInt("IDCOURS"));
				obj.setStatutCours(rs.getString("STATUTCOURS"));
				obj.setMoniteur(m.trouver(rs.getInt("IDMONITEUR")));
				obj.setHoraire(h.trouver(rs.getInt("IDHORAIRE")));
				obj.setSemaine(s.trouver(rs.getInt("IDSEMAINE")));
				obj.setTypeCours(t.trouver(rs.getInt("IDTYPECOURS")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
