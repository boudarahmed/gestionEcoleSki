package be.belouh.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import be.belouh.POJO.Accreditation;
import be.belouh.POJO.Moniteur;
import be.belouh.POJO.Semaine;

public class MoniteurDAO extends DAO<Moniteur> {

	@Override
	public ArrayList<Integer> compter() {
		ArrayList<Integer> tab = new ArrayList<Integer>();
		String sql = "SELECT PERSONNE.IDPERSONNE FROM PERSONNE JOIN UTILISATEUR ON PERSONNE.IDPERSONNE = UTILISATEUR.IDUTILISATEUR JOIN MONITEUR ON UTILISATEUR.IDUTILISATEUR = MONITEUR.IDMONITEUR";

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
	public boolean supprimer(Moniteur obj) {
		boolean res = false;
		String sqlMoniteur = "DELETE FROM MONITEUR WHERE IDMONITEUR = ?";
		String sqlUtilisateur = "DELETE FROM UTILISATEUR WHERE IDUTILISATEUR = ?";
		String sqlPersonne = "DELETE FROM PERSONNE WHERE IDPERSONNE = ?";

		try {
			PreparedStatement stmtMoniteur = c.prepareStatement(sqlMoniteur);
			PreparedStatement stmtUtil = c.prepareStatement(sqlUtilisateur);
			PreparedStatement stmtPers = c.prepareStatement(sqlPersonne);

			stmtMoniteur.setInt(1, obj.getId());
			stmtUtil.setInt(1, obj.getId());
			stmtPers.setInt(1, obj.getId());

			stmtMoniteur.executeUpdate();
			stmtUtil.executeUpdate();
			stmtPers.executeUpdate();

			res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Moniteur inserer(Moniteur obj) {
		String sqlPersonne = "INSERT INTO PERSONNE (NOM, PRENOM, DATENAISSANCE, SEXE, VILLE, CODEPOSTAL, NUMERO, RUE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlUtilisateur = "INSERT INTO UTILISATEUR VALUES (?, ?, ?)";
		String sqlMoniteur = "INSERT INTO MONITEUR VALUES (?, ?, ?)";

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
					PreparedStatement stmtMoniteur = c.prepareStatement(sqlMoniteur);

					stmtUtil.setInt(1, obj.getId());
					stmtUtil.setString(2, obj.getAdresseMail());
					stmtUtil.setString(3, obj.getMotDePasse());
					stmtMoniteur.setInt(1, obj.getId());
					stmtMoniteur.setDouble(2, obj.getSalaireHoraire());
					stmtMoniteur.setString(3, String.valueOf(obj.getCoursParticulier()));

					stmtUtil.executeUpdate();
					stmtMoniteur.executeUpdate();
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
	public Moniteur mettreAJour(Moniteur obj) {
		DAO<Accreditation> accreditationDao = new AccreditationDAO();
		DAO<Semaine> semaineDao = new SemaineDAO();

		ArrayList<Accreditation> listeA = new ArrayList<Accreditation>();
		ArrayList<Semaine> listeS = new ArrayList<Semaine>();

		String sqlPersonne = "UPDATE PERSONNE SET NOM = ?, PRENOM = ?, DATENAISSANCE = ?, SEXE = ?, VILLE = ?, CODEPOSTAL = ?, NUMERO = ?, RUE = ? WHERE IDPERSONNE = ?";
		String sqlUtilisateur = "UPDATE UTILISATEUR SET ADRESSEMAIL = ?, MOTDEPASSE = ? WHERE IDUTILISATEUR = ?";
		String sqlMoniteur = "UPDATE MONITEUR SET SALAIREHORAIRE = ?, COURSPARTICULIER = ? WHERE IDMONITEUR = ?";
		String sqlAccreditation = "SELECT ACCREDITATION.IDACCREDITATION FROM LIGNEACCREDITATION JOIN ACCREDITATION ON LIGNEACCREDITATION.IDACCREDITATION = ACCREDITATION.IDACCREDITATION WHERE LIGNEACCREDITATION.IDMONITEUR = ?";
		String sqlSemaine = "SELECT SEMAINE.IDSEMAINE FROM LIGNESEMAINE JOIN SEMAINE ON LIGNESEMAINE.IDSEMAINE = SEMAINE.IDSEMAINE WHERE LIGNESEMAINE.IDMONITEUR = ?";

		try {
			PreparedStatement stmtPers = c.prepareStatement(sqlPersonne);
			PreparedStatement stmtUtil = c.prepareStatement(sqlUtilisateur);
			PreparedStatement stmtMoniteur = c.prepareStatement(sqlMoniteur);
			PreparedStatement stmtAccreditation = c.prepareStatement(sqlAccreditation);
			PreparedStatement stmtSemaine = c.prepareStatement(sqlSemaine);

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
			stmtMoniteur.setDouble(1, obj.getSalaireHoraire());
			stmtMoniteur.setString(2, String.valueOf(obj.getCoursParticulier()));
			stmtMoniteur.setInt(3, obj.getId());
			stmtAccreditation.setInt(1, obj.getId());
			stmtSemaine.setInt(1, obj.getId());

			stmtPers.executeUpdate();
			stmtUtil.executeUpdate();
			stmtMoniteur.executeUpdate();
			ResultSet rs = stmtAccreditation.executeQuery();

			while (rs.next()) {
				listeA.add(accreditationDao.trouver(rs.getInt("IDACCREDITATION")));
			}
			listeA.removeAll(obj.getListeAccreditation());
			Iterator<Accreditation> itAccreditation = listeA.iterator();
			while (itAccreditation.hasNext()) {
				String sqlLigneAccreditation = "INSERT INTO LIGNEACCREDITATION VALUES (?, ?)";
				try {
					PreparedStatement stmtLigneAccreditation = c.prepareStatement(sqlLigneAccreditation);

					stmtLigneAccreditation.setInt(1, obj.getId());
					stmtLigneAccreditation.setInt(1, itAccreditation.next().getId());

					stmtLigneAccreditation.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

			rs = stmtSemaine.executeQuery();

			while (rs.next()) {
				listeS.add(semaineDao.trouver(rs.getInt("IDSEMAINE")));
			}
			listeS.removeAll(obj.getListeIndisponibilitee());
			Iterator<Semaine> itSemaine = listeS.iterator();
			while (itSemaine.hasNext()) {
				String sqlLigneSemaine = "INSERT INTO LIGNESEMAINE VALUES (?, ?)";
				try {
					PreparedStatement stmtLigneSemaine = c.prepareStatement(sqlLigneSemaine);

					stmtLigneSemaine.setInt(1, obj.getId());
					stmtLigneSemaine.setInt(1, itSemaine.next().getId());

					stmtLigneSemaine.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Moniteur trouver(int id) {
		DAO<Accreditation> accreditationDao = new AccreditationDAO();
		DAO<Semaine> semaineDao = new SemaineDAO();

		ArrayList<Accreditation> listeA = new ArrayList<Accreditation>();
		ArrayList<Semaine> listeS = new ArrayList<Semaine>();

		String sqlMoniteur = "SELECT PERSONNE.IDPERSONNE, PERSONNE.NOM, PERSONNE.PRENOM, PERSONNE.DATENAISSANCE, PERSONNE.SEXE, PERSONNE.VILLE, PERSONNE.CODEPOSTAL, PERSONNE.NUMERO, PERSONNE.RUE, UTILISATEUR.ADRESSEMAIL, UTILISATEUR.MOTDEPASSE, MONITEUR.SALAIREHORAIRE, MONITEUR.COURSPARTICULIER FROM PERSONNE JOIN UTILISATEUR ON PERSONNE.IDPERSONNE = UTILISATEUR.IDUTILISATEUR JOIN MONITEUR ON UTILISATEUR.IDUTILISATEUR = MONITEUR.IDMONITEUR WHERE PERSONNE.IDPERSONNE = ?";
		String sqlAccreditation = "SELECT ACCREDITATION.IDACCREDITATION FROM LIGNEACCREDITATION JOIN ACCREDITATION ON LIGNEACCREDITATION.IDACCREDITATION = ACCREDITATION.IDACCREDITATION WHERE LIGNEACCREDITATION.IDMONITEUR = ?";
		String sqlSemaine = "SELECT SEMAINE.IDSEMAINE FROM LIGNESEMAINE JOIN SEMAINE ON LIGNESEMAINE.IDSEMAINE = SEMAINE.IDSEMAINE WHERE LIGNESEMAINE.IDMONITEUR = ?";

		Moniteur obj = new Moniteur();
		try {
			PreparedStatement stmt = c.prepareStatement(sqlMoniteur);
			PreparedStatement stmtAccreditation = c.prepareStatement(sqlAccreditation);
			PreparedStatement stmtSemaine = c.prepareStatement(sqlSemaine);

			stmt.setInt(1, id);
			stmtAccreditation.setInt(1, obj.getId());
			stmtSemaine.setInt(1, obj.getId());

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
				obj.setSalaireHoraire(rs.getDouble("SALAIREHORAIRE"));
				obj.setCoursParticulier(Boolean.parseBoolean(rs.getString("CONGESCOLAIRE")));

				rs = stmtAccreditation.executeQuery();

				while (rs.next()) {
					listeA.add(accreditationDao.trouver(rs.getInt("IDACCREDITATION")));
				}

				obj.setListeAccreditation(listeA);

				rs = stmtSemaine.executeQuery();

				while (rs.next()) {
					listeS.add(semaineDao.trouver(rs.getInt("IDSEMAINE")));
				}

				obj.setListeIndisponibilitee(listeS);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
