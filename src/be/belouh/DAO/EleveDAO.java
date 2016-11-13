package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import be.belouh.POJO.Client;
import be.belouh.POJO.Eleve;

public class EleveDAO extends DAO<Eleve> {

	@Override
	public ArrayList<Integer> compter() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		String sql = "SELECT PERSONNE.IDPERSONNE FROM PERSONNE JOIN ELEVE ON PERSONNE.IDPERSONNE = ELEVE.IDELEVE";

		try {
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				tab.add(rs.getInt("IDPERSONNE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tab;
	}

	@Override
	public boolean supprimer(Eleve obj) {
		boolean res = false;
		String sqlEleve = "DELETE FROM ELEVE WHERE IDELEVE = ?";
		String sqlPersonne = "DELETE FROM PERSONNE WHERE IDPERSONNE = ?";

		try {
			PreparedStatement stmtEleve = c.prepareStatement(sqlEleve);
			PreparedStatement stmtPers = c.prepareStatement(sqlPersonne);

			stmtEleve.setInt(1, obj.getId());
			stmtPers.setInt(1, obj.getId());

			stmtEleve.executeUpdate();
			stmtPers.executeUpdate();

			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Eleve inserer(Eleve obj) {
		String sqlPersonne = "INSERT INTO PERSONNE (NOM, PRENOM, DATENAISSANCE, SEXE, VILLE, CODEPOSTAL, NUMERO, RUE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlEleve = "INSERT INTO ELEVE VALUES (?, ?, ?)";

		try {
			PreparedStatement stmtPers = c.prepareStatement(sqlPersonne, Statement.RETURN_GENERATED_KEYS);

			stmtPers.setString(1, obj.getNom());
			stmtPers.setString(2, obj.getPrenom());
			stmtPers.setString(3, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDateNaissance())));
			stmtPers.setString(4, String.valueOf(obj.getSexe()));
			stmtPers.setString(5, obj.getVille());
			stmtPers.setString(6, obj.getCodePostal());
			stmtPers.setString(7, obj.getNumero());
			stmtPers.setString(8, obj.getRue());

			stmtPers.executeUpdate();

			try {
				ResultSet generatedKeys = stmtPers.getGeneratedKeys();

				if (generatedKeys.next()) {
					obj.setId(generatedKeys.getInt(1));

					PreparedStatement stmtEleve = c.prepareStatement(sqlEleve);

					stmtEleve.setInt(1, obj.getId());
					stmtEleve.setString(2, String.valueOf(obj.getAssurance()));
					stmtEleve.setInt(3, obj.getClient().getId());

					stmtEleve.executeUpdate();
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
	public Eleve mettreAJour(Eleve obj) {
		String sqlPersonne = "UPDATE PERSONNE SET NOM = ?, PRENOM = ?, DATENAISSANCE = ?, SEXE = ?, VILLE = ?, CODEPOSTAL = ?, NUMERO = ?, RUE = ? WHERE IDPERSONNE = ?";
		String sqlEleve = "UPDATE ELEVE SET ASSURANCE = ?, IDCLIENT = ? WHERE IDELEVE = ?";

		try {
			PreparedStatement stmtPers = c.prepareStatement(sqlPersonne);
			PreparedStatement stmtEleve = c.prepareStatement(sqlEleve);

			stmtPers.setString(1, obj.getNom());
			stmtPers.setString(2, obj.getPrenom());
			stmtPers.setString(3, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDateNaissance())));
			stmtPers.setString(4, String.valueOf(obj.getSexe()));
			stmtPers.setString(5, obj.getVille());
			stmtPers.setString(6, obj.getCodePostal());
			stmtPers.setString(7, obj.getNumero());
			stmtPers.setString(8, obj.getRue());
			stmtPers.setInt(9, obj.getId());
			stmtEleve.setString(1, String.valueOf(obj.getAssurance()));
			stmtEleve.setInt(2, obj.getClient().getId());
			stmtEleve.setInt(3, obj.getId());

			stmtPers.executeUpdate();
			stmtEleve.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Eleve trouver(int id) {
		DAO<Client> cl = new ClientDAO();

		String sql = "SELECT PERSONNE.IDPERSONNE, PERSONNE.NOM, PERSONNE.PRENOM, PERSONNE.DATENAISSANCE, PERSONNE.SEXE, PERSONNE.VILLE, PERSONNE.CODEPOSTAL, PERSONNE.NUMERO, PERSONNE.RUE, ELEVE.ASSURANCE, ELEVE.IDCLIENT FROM PERSONNE JOIN ELEVE ON PERSONNE.IDPERSONNE = ELEVE.IDELEVE WHERE PERSONNE.IDPERSONNE = ?";

		Eleve obj = new Eleve();
		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				obj.setId(rs.getInt("IDPERSONNE"));
				obj.setNom(rs.getString("NOM"));
				obj.setPrenom(rs.getString("PRENOM"));
				obj.setDateNaissance(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("DATENAISSANCE")));
				obj.setSexe(rs.getString("SEXE").charAt(0));
				obj.setVille(rs.getString("VILLE"));
				obj.setCodePostal(rs.getString("CODEPOSTAL"));
				obj.setNumero(rs.getString("NUMERO"));
				obj.setRue(rs.getString("RUE"));
				obj.setAssurance(Boolean.parseBoolean(rs.getString("ASSURANCE")));
				obj.setClient(cl.trouver(rs.getInt("IDCLIENT")));
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
