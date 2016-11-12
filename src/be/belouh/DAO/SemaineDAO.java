package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import be.belouh.POJO.Semaine;

public class SemaineDAO extends DAO<Semaine> {

	@Override
	public ArrayList<Integer> compter() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		String sql = "SELECT IDSEMAINE FROM SEMAINE";

		try {
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				tab.add(rs.getInt("IDSEMAINE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tab;
	}

	@Override
	public boolean supprimer(Semaine obj) {
		boolean res = false;
		String sql = "DELETE FROM SEMAINE WHERE IDSEMAINE = ?";

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
	public Semaine inserer(Semaine obj) {
		String sql = "INSERT INTO SEMAINE (DATEDEB, DATEFIN, CONGESCOLAIRE) VALUES (?, ?, ?)";

		try {
			PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDateDeb())));
			stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDateFin())));
			stmt.setString(3, String.valueOf(obj.getCongeScolaire()));

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
	public Semaine mettreAJour(Semaine obj) {
		String sql = "UPDATE SEMAINE SET DATEDEB = ?, DATEFIN = ?, CONGESCOLAIRE = ? WHERE IDSEMAINE = ?";

		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setString(1, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDateDeb())));
			stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDateFin())));
			stmt.setString(3, String.valueOf(obj.getCongeScolaire()));
			stmt.setInt(4, obj.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Semaine trouver(int id) {
		String sql = "SELECT * FROM SEMAINE WHERE IDSEMAINE = ?";
		Semaine obj = new Semaine();

		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				obj.setId(rs.getInt("IDSEMAINE"));
				obj.setDateDeb(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("DATEDEB")));
				obj.setDateFin(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("DATEFIN")));
				obj.setCongeScolaire(Boolean.parseBoolean(rs.getString("CONGESCOLAIRE")));
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
