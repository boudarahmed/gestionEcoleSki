package be.belouh.Modele;

import java.util.Date;

import be.belouh.POJO.Client;
import be.belouh.Singleton.ListeClientS;

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

	public boolean Inscription(){
		ListeClientS liste = ListeClientS.getInstance();
		System.out.println(liste.getListe());
		utilisateur = liste.ajouterClient((Client)utilisateur);
		System.out.println(liste.getListe());
		if(utilisateur == null)
			return false;
		else
			return true;
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
