package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.belouh.POJO.Horaire;

public class HoraireDAO extends DAO<Horaire> {

	@Override
	public ArrayList<Integer> compter() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		String sql = "SELECT IDHORAIRE FROM HORAIRE";

		try {
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				tab.add(rs.getInt("IDHORAIRE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tab;
	}

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
	public Horaire trouver(int id) {
		String sql = "SELECT * FROM HORAIRE WHERE IDHORAIRE = ?";
		Horaire obj = new Horaire();
		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				obj.setId(rs.getInt("IDHORAIRE"));
				obj.setHeureDeb(rs.getInt("HEUREDEB"));
				obj.setHeureFin(rs.getInt("HEUREFIN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
