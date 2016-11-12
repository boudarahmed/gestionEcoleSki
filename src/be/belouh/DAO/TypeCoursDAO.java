package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.belouh.POJO.Accreditation;
import be.belouh.POJO.TypeCours;

public class TypeCoursDAO extends DAO<TypeCours> {

	@Override
	public ArrayList<Integer> compter() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		String sql = "SELECT IDTYPECOURS FROM TYPECOURS";

		try {
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				tab.add(rs.getInt("IDTYPECOURS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tab;
	}

	@Override
	public boolean supprimer(TypeCours obj) {
		boolean res = false;
		String sql = "DELETE FROM TYPECOURS WHERE IDTYPECOURS = ?";

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
	public TypeCours inserer(TypeCours obj) {
		String sql = "INSERT INTO TYPECOURS (NIVEAU, MINELEVE, MAXELEVE, PRIX, IDACCREDITATION) VALUES (?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, obj.getNiveau());
			stmt.setInt(2, obj.getMinEleve());
			stmt.setInt(3, obj.getMaxEleve());
			stmt.setDouble(4, obj.getPrix());
			stmt.setInt(5, obj.getAccreditation().getId());

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
	public TypeCours mettreAJour(TypeCours obj) {
		String sql = "UPDATE TYPECOURS SET NIVEAU = ?, MINELEVE = ?, MAXELEVE = ?, PRIX = ?, IDACCREDITATION = ? WHERE IDTYPECOURS = ?";

		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setString(1, obj.getNiveau());
			stmt.setInt(2, obj.getMinEleve());
			stmt.setInt(3, obj.getMaxEleve());
			stmt.setDouble(4, obj.getPrix());
			stmt.setInt(5, obj.getAccreditation().getId());
			stmt.setInt(6, obj.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public TypeCours trouver(int id) {
		String sql = "SELECT TYPECOURS.IDTYPECOURS, TYPECOURS.NIVEAU, TYPECOURS.MINELEVE, TYPECOURS.MAXELEVE, TYPECOURS.PRIX, TYPECOURS.IDACCREDITATION, ACCREDITATION.SPORT, ACCREDITATION.AGEMIN, ACCREDITATION.AGEMAX FROM TYPECOURS JOIN ACCREDITATION ON TYPECOURS.IDACCREDITATION = ACCREDITATION.IDACCREDITATION WHERE TYPECOURS.IDTYPECOURS = ?";
		TypeCours obj = new TypeCours();
		obj.setAccreditation(new Accreditation());
		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				obj.setId(rs.getInt("IDTYPECOURS"));
				obj.setNiveau(rs.getString("NIVEAU"));
				obj.setMinEleve(rs.getInt("MINELEVE"));
				obj.setMaxEleve(rs.getInt("MAXELEVE"));
				obj.setPrix(rs.getDouble("PRIX"));
				obj.getAccreditation().setId(rs.getInt("IDACCREDITATION"));
				obj.getAccreditation().setSport(rs.getString("SPORT"));
				obj.getAccreditation().setAgeMin(rs.getInt("AGEMIN"));
				obj.getAccreditation().setAgeMax(rs.getInt("AGEMAX"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
