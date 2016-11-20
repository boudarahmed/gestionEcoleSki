package be.belouh.Modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import be.belouh.POJO.Client;
import be.belouh.POJO.Eleve;
import be.belouh.POJO.Reservation;
import be.belouh.Singleton.ListeClientS;
import be.belouh.Singleton.ListeEleveS;
import be.belouh.Singleton.ListeReservationS;

public class ClientM extends UtilisateurM {
	public ClientM() {
		utilisateur = new Client();
	}

	public ClientM(String nom, String prenom, Date dateNaissance, char sexe, String numero, String rue,
			String codePostal, String ville, String adresseMail, String motDePasse, String numeroCompte) {
		utilisateur = new Client();
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setDateNaissance(dateNaissance);
		utilisateur.setSexe(sexe);
		utilisateur.setNumero(numero);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setAdresseMail(adresseMail);
		utilisateur.setMotDePasse(motDePasse);
		((Client) utilisateur).setNumeroCompte(numeroCompte);
	}

	public boolean Inscription() {
		ListeClientS liste = ListeClientS.getInstance();
		utilisateur = liste.ajouterClient((Client) utilisateur);

		if (utilisateur == null)
			return false;
		else
			return true;
	}

	public ArrayList<Eleve> getListeEleve() {
		return ((Client) getUtilisateur()).getListeEleve();
	}

	public ArrayList<Reservation> triReservation(String statut, Eleve eleve) {
		ArrayList<Reservation> liste = (ArrayList<Reservation>) ((Client) getUtilisateur()).getListeReservation()
				.stream().filter(x -> x.getStatutReservation().equals(statut) && x.getEleve().equals(eleve))
				.collect(Collectors.toList());
		return liste;
	}

	public Reservation getReservation(Object[] data) {
		return ((Client) getUtilisateur()).getListeReservation().stream().filter(x -> x.getId() == (Integer) data[3])
				.findAny().orElse(null);
	}

	public boolean ajoutEleve(Eleve e) {
		ListeEleveS liste = ListeEleveS.getInstance();
		e = liste.ajouterEleve(e);

		if (e == null)
			return false;
		else {
			((Client) utilisateur).getListeEleve().add(e);
			return true;
		}
	}

	public boolean ajoutReservation(Reservation r) {
		ListeReservationS liste = ListeReservationS.getInstance();
		r = liste.ajouterReservation(r);

		if (r == null)
			return false;
		else {
			((Client) utilisateur).getListeReservation().add(r);
			return true;
		}
	}

	public void miseAjourReservation(Reservation r) {
		ListeReservationS liste = ListeReservationS.getInstance();
		r = liste.mettreAjourReservation(r);
	}

	public boolean supprimerReservation(Reservation r) {
		ListeReservationS liste = ListeReservationS.getInstance();
		if (liste.supprimerReservation(r)) {
			((Client) utilisateur).getListeReservation().remove(r);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean connexion() {
		ListeClientS liste = ListeClientS.getInstance();
		int index = liste.getListe().indexOf(this.utilisateur);

		if (index == -1)
			return false;
		else {
			this.utilisateur = liste.getListe().get(index);
			return true;
		}
	}

}
