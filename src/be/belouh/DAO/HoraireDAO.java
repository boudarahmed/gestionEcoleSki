package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import be.belouh.POJO.Horaire;

public class HoraireDAO extends DAO<Horaire> {

	@Override
	public boolean supprimer(Horaire obj) {
		boolean res = false;
		String sql = "DELETE FROM HORAIRE WHERE IDHORAIRE = ?";

		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, obj.getId());

			stmt.executeUpdate();

			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Horaire inserer(Horaire obj) {
		String sql = "INSERT INTO HORAIRE (HEUREDEB, HEUREFIN) VALUES (?, ?)";

		try {
			PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, obj.getHeureDeb());
			stmt.setInt(2, obj.getHeureFin());

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
	public Horaire mettreAJour(Horaire obj) {
		String sql = "UPDATE HORAIRE SET HEUREDEB = ?, HEUREFIN = ? WHERE IDHORAIRE = ?";

		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, obj.getHeureDeb());
			stmt.setInt(2, obj.getHeureFin());
			stmt.setInt(3, obj.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Horaire trouver(Horaire obj) {
		String sql = "SELECT * FROM HORAIRE WHERE HEUREDEB = ? AND HEUREFIN = ?";

		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, obj.getHeureDeb());
			stmt.setInt(2, obj.getHeureFin());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				obj.setId(rs.getInt("IDHORAIRE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public HashSet<Horaire> recupererListeHoraire() {
		HashSet<Horaire> liste = new HashSet<Horaire>();
		String sql = "SELECT * FROM HORAIRE";

		try {
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				liste.add(new Horaire(rs.getInt("IDHORAIRE"), rs.getInt("HEUREDEB"), rs.getInt("HEUREFIN")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
}
