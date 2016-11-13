package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import be.belouh.POJO.Client;

public class ClientDAO extends DAO<Client> {

	@Override
	public ArrayList<Integer> compter() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		String sql = "SELECT PERSONNE.IDPERSONNE FROM PERSONNE JOIN UTILISATEUR ON PERSONNE.IDPERSONNE = UTILISATEUR.IDUTILISATEUR JOIN CLIENT ON UTILISATEUR.IDUTILISATEUR = CLIENT.IDCLIENT";

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
	public boolean supprimer(Client obj) {
		boolean res = false;
		String sqlClient = "DELETE FROM CLIENT WHERE IDCLIENT = ?";
		String sqlUtilisateur = "DELETE FROM UTILISATEUR WHERE IDUTILISATEUR = ?";
		String sqlPersonne = "DELETE FROM PERSONNE WHERE IDPERSONNE = ?";

		try {
			PreparedStatement stmtClient = c.prepareStatement(sqlClient);
			PreparedStatement stmtUtil = c.prepareStatement(sqlUtilisateur);
			PreparedStatement stmtPers = c.prepareStatement(sqlPersonne);

			stmtClient.setInt(1, obj.getId());
			stmtUtil.setInt(1, obj.getId());
			stmtPers.setInt(1, obj.getId());

			stmtClient.executeUpdate();
			stmtUtil.executeUpdate();
			stmtPers.executeUpdate();

			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Client inserer(Client obj) {
		String sqlPersonne = "INSERT INTO PERSONNE (NOM, PRENOM, DATENAISSANCE, SEXE, VILLE, CODEPOSTAL, NUMERO, RUE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlUtilisateur = "INSERT INTO UTILISATEUR VALUES (?, ?, ?)";
		String sqlClient = "INSERT INTO CLIENT VALUES (?, ?)";

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

					PreparedStatement stmtUtil = c.prepareStatement(sqlUtilisateur);
					PreparedStatement stmtClient = c.prepareStatement(sqlClient);

					stmtUtil.setInt(1, obj.getId());
					stmtUtil.setString(2, obj.getAdresseMail());
					stmtUtil.setString(3, obj.getMotDePasse());
					stmtClient.setInt(1, obj.getId());
					stmtClient.setString(2, obj.getNumeroCompte());

					stmtUtil.executeUpdate();
					stmtClient.executeUpdate();
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
	public Client mettreAJour(Client obj) {
		String sqlPersonne = "UPDATE PERSONNE SET NOM = ?, PRENOM = ?, DATENAISSANCE = ?, SEXE = ?, VILLE = ?, CODEPOSTAL = ?, NUMERO = ?, RUE = ? WHERE IDPERSONNE = ?";
		String sqlUtilisateur = "UPDATE UTILISATEUR SET ADRESSEMAIL = ?, MOTDEPASSE = ? WHERE IDUTILISATEUR = ?";
		String sqlClient = "UPDATE CLIENT SET NUMEROCOMPTE = ? WHERE IDCLIENT = ?";

		try {
			PreparedStatement stmtPers = c.prepareStatement(sqlPersonne);
			PreparedStatement stmtUtil = c.prepareStatement(sqlUtilisateur);
			PreparedStatement stmtClient = c.prepareStatement(sqlClient);

			stmtPers.setString(1, obj.getNom());
			stmtPers.setString(2, obj.getPrenom());
			stmtPers.setString(3, new SimpleDateFormat("yyyy-MM-dd").format((obj.getDateNaissance())));
			stmtPers.setString(4, String.valueOf(obj.getSexe()));
			stmtPers.setString(5, obj.getVille());
			stmtPers.setString(6, obj.getCodePostal());
			stmtPers.setString(7, obj.getNumero());
			stmtPers.setString(8, obj.getRue());
			stmtPers.setInt(9, obj.getId());
			stmtUtil.setString(1, obj.getAdresseMail());
			stmtUtil.setString(2, obj.getMotDePasse());
			stmtUtil.setInt(3, obj.getId());
			stmtClient.setString(1, obj.getNumeroCompte());
			stmtClient.setInt(2, obj.getId());

			stmtPers.executeUpdate();
			stmtUtil.executeUpdate();
			stmtClient.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Client trouver(int id) {
		String sql = "SELECT PERSONNE.IDPERSONNE, PERSONNE.NOM, PERSONNE.PRENOM, PERSONNE.DATENAISSANCE, PERSONNE.SEXE, PERSONNE.VILLE, PERSONNE.CODEPOSTAL, PERSONNE.NUMERO, PERSONNE.RUE, UTILISATEUR.ADRESSEMAIL, UTILISATEUR.MOTDEPASSE, CLIENT.NUMEROCOMPTE FROM PERSONNE JOIN UTILISATEUR ON PERSONNE.IDPERSONNE = UTILISATEUR.IDUTILISATEUR JOIN CLIENT ON UTILISATEUR.IDUTILISATEUR = CLIENT.IDCLIENT WHERE PERSONNE.IDPERSONNE = ?";
		Client obj = new Client();
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
				obj.setAdresseMail(rs.getString("ADRESSEMAIL"));
				obj.setMotDePasse(rs.getString("MOTDEPASSE"));
				obj.setNumeroCompte(rs.getString("NUMEROCOMPTE"));
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
