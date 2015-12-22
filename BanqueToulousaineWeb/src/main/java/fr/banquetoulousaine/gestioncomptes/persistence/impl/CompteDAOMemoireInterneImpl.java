package fr.banquetoulousaine.gestioncomptes.persistence.impl;

import java.util.ArrayList;

import fr.banquetoulousaine.gestioncomptes.entite.Compte;
import fr.banquetoulousaine.gestioncomptes.exceptions.CompteIntrouvableException;
import fr.banquetoulousaine.gestioncomptes.persistence.ICompteDAO;

public class CompteDAOMemoireInterneImpl implements ICompteDAO {
	
	private ArrayList<Compte> listeComptes;
	
	public CompteDAOMemoireInterneImpl(){
		this.listeComptes = new ArrayList<Compte>();
	}

	public Compte findCompteByNumeroCompte(String numeroCompte) throws CompteIntrouvableException {
		for(Compte compte : this.listeComptes){
			if(compte.getNumeroCompte().equals(numeroCompte)){
				return compte;
			}
		}
		throw new CompteIntrouvableException();
	}

	public void enregistrerCompte(Compte compte) {
		if(compte != null){
			this.listeComptes.add(compte);
		}
	}

	public void supprimerCompte(String numeroCompte) {
		for (Compte compte : listeComptes) {
			if(compte.getNumeroCompte().equals(numeroCompte)){
				listeComptes.remove(compte);
			}
		}
	}

}
