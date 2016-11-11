package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import be.belouh.POJO.Administrateur;

public class AdministrateurDAO extends DAO<Administrateur> {

	@Override
	public boolean supprimer(Administrateur obj) {
		boolean res = false;
		String sqlAdministrateur = "DELETE FROM ADMINISTRATEUR WHERE IDADMINISTRATEUR = ?";
		String sqlUtilisateur = "DELETE FROM UTILISATEUR WHERE IDUTILISATEUR = ?";
		String sqlPersonne = "DELETE FROM PERSONNE WHERE IDPERSONNE = ?";

		try {
			PreparedStatement stmtAdmin = c.prepareStatement(sqlAdministrateur);
			PreparedStatement stmtUtil = c.prepareStatement(sqlUtilisateur);
			PreparedStatement stmtPers = c.prepareStatement(sqlPersonne);

			stmtAdmin.setInt(1, obj.getId());
			stmtUtil.setInt(1, obj.getId());
			stmtPers.setInt(1, obj.getId());

			stmtAdmin.executeUpdate();
			stmtUtil.executeUpdate();
			stmtPers.executeUpdate();

			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Administrateur inserer(Administrateur obj) {
		String sqlPersonne = "INSERT INTO PERSONNE (NOM, PRENOM, DATENAISSANCE, SEXE, VILLE, CODEPOSTAL, NUMERO, RUE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlUtilisateur = "INSERT INTO UTILISATEUR VALUES (?, ?, ?)";
		String sqlAdministrateur = "INSERT INTO ADMINISTRATEUR VALUES (?, ?)";

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
					PreparedStatement stmtAdmin = c.prepareStatement(sqlAdministrateur);

					stmtUtil.setInt(1, obj.getId());
					stmtUtil.setString(2, obj.getAdresseMail());
					stmtUtil.setString(3, obj.getMotDePasse());
					stmtAdmin.setInt(1, obj.getId());
					stmtAdmin.setString(2, obj.getPoste());

					stmtUtil.executeUpdate();
					stmtAdmin.executeUpdate();
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
	public Administrateur mettreAJour(Administrateur obj) {
		String sqlPersonne = "UPDATE PERSONNE SET NOM = ?, PRENOM = ?, DATENAISSANCE = ?, SEXE = ?, VILLE = ?, CODEPOSTAL = ?, NUMERO = ?, RUE = ? WHERE IDPERSONNE = ?";
		String sqlUtilisateur = "UPDATE UTILISATEUR SET ADRESSEMAIL = ?, MOTDEPASSE = ? WHERE IDUTILISATEUR = ?";
		String sqlAdministrateur = "UPDATE ADMINISTRATEUR SET POSTE = ? WHERE IDADMINISTRATEUR = ?";

		try {
			PreparedStatement stmtPers = c.prepareStatement(sqlPersonne);
			PreparedStatement stmtUtil = c.prepareStatement(sqlUtilisateur);
			PreparedStatement stmtAdmin = c.prepareStatement(sqlAdministrateur);

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
			stmtAdmin.setString(1, obj.getPoste());
			stmtAdmin.setInt(2, obj.getId());

			stmtPers.executeUpdate();
			stmtUtil.executeUpdate();
			stmtAdmin.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Administrateur trouver(Administrateur obj) {
		String sql = "SELECT PERSONNE.IDPERSONNE, PERSONNE.NOM, PERSONNE.PRENOM, PERSONNE.DATENAISSANCE, PERSONNE.SEXE, PERSONNE.VILLE, PERSONNE.CODEPOSTAL, PERSONNE.NUMERO, PERSONNE.RUE, ADMINISTRATEUR.POSTE FROM PERSONNE JOIN UTILISATEUR ON PERSONNE.IDPERSONNE = UTILISATEUR.IDUTILISATEUR JOIN ADMINISTRATEUR ON UTILISATEUR.IDUTILISATEUR = ADMINISTRATEUR.IDADMINISTRATEUR WHERE UTILISATEUR.ADRESSEMAIL = ? AND UTILISATEUR.MOTDEPASSE = ?";

		try {
			PreparedStatement stmt = c.prepareStatement(sql);

			stmt.setString(1, obj.getAdresseMail());
			stmt.setString(2, obj.getMotDePasse());

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
				obj.setPoste(rs.getString("POSTE"));
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
