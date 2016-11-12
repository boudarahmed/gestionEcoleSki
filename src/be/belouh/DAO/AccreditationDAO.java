package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.belouh.POJO.Accreditation;

public class AccreditationDAO extends DAO<Accreditation> {

	@Override
	public ArrayList<Integer> compter() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		String sql = "SELECT IDACCREDITATION FROM ACCREDITATION";

		try {
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				tab.add(rs.getInt("IDACCREDITATION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tab;
	}

	@Override
	public boolean supprimer(Accreditation obj) {
		boolean res = false;
		String sql = "DELETE FROM ACCREDITATION WHERE IDACCREDITATION = ?";

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
	public Accreditation inserer(Accreditation obj) {
		String sql = "INSERT INTO ACCREDITATION (SPORT, AGEMIN, AGEMAX) VALUES (?, ?, ?)";

		try {
			PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, obj.getSport());
			stmt.setInt(2, obj.getAgeMin());
			stmt.setInt(3, obj.getAgeMax());

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
	public Accreditation mettreAJour(Accreditation obj) {
		String sql = "UPDATE ACCREDITATION SET SPORT = ?, AGEMIN = ?, AGEMAX = ? WHERE IDACCREDITATION = ?";

		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setString(1, obj.getSport());
			stmt.setInt(2, obj.getAgeMin());
			stmt.setInt(3, obj.getAgeMax());
			stmt.setInt(4, obj.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Accreditation trouver(int id) {
		String sql = "SELECT * FROM ACCREDITATION WHERE IDACCREDITATION = ?";
		Accreditation obj = new Accreditation();
		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				obj.setId(rs.getInt("IDACCREDITATION"));
				obj.setSport(rs.getString("SPORT"));
				obj.setAgeMin(rs.getInt("AGEMIN"));
				obj.setAgeMax(rs.getInt("AGEMAX"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
