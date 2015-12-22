package fr.banquetoulousaine.gestioncomptes.service.impl;

import fr.banquetoulousaine.gestioncomptes.entite.Compte;
import fr.banquetoulousaine.gestioncomptes.exceptions.CompteIntrouvableException;
import fr.banquetoulousaine.gestioncomptes.exceptions.CreditRefuseException;
import fr.banquetoulousaine.gestioncomptes.exceptions.DebitRefuseException;
import fr.banquetoulousaine.gestioncomptes.exceptions.ProblemeTechniqueException;
import fr.banquetoulousaine.gestioncomptes.exceptions.VirementRefuseException;
import fr.banquetoulousaine.gestioncomptes.persistence.ICompteDAO;
import fr.banquetoulousaine.gestioncomptes.persistence.impl.CompteDAOFichierBinaireImpl;
import fr.banquetoulousaine.gestioncomptes.service.ICompteService;

public class CompteServiceImpl implements ICompteService {
	
	ICompteDAO compteDAO;
	
	public CompteServiceImpl(){
		this.compteDAO = new CompteDAOFichierBinaireImpl();
	}

	private void debiterCompte(String numeroCompte, int montant) throws DebitRefuseException, CompteIntrouvableException, ProblemeTechniqueException {
		if(montant <= 0){
			throw new DebitRefuseException();
		}
		if(Compte.DECOUVERT_AUTORISE > (this.consulterSolde(numeroCompte) - montant)){
			throw new DebitRefuseException();
		}
		Compte compteRecupere = this.compteDAO.findCompteByNumeroCompte(numeroCompte);
		compteRecupere.setSolde(compteRecupere.getSolde() - montant);
		this.compteDAO.enregistrerCompte(compteRecupere);
	}

	private void crediterCompte(String numeroCompte, int montant) throws CreditRefuseException, CompteIntrouvableException, ProblemeTechniqueException {
		if(montant <= 0){
			throw new CreditRefuseException();
		}
		Compte compteRecupere = this.compteDAO.findCompteByNumeroCompte(numeroCompte);
		compteRecupere.setSolde(compteRecupere.getSolde() + montant);
		this.compteDAO.enregistrerCompte(compteRecupere);
	}

	public int consulterSolde(String numeroCompte) throws CompteIntrouvableException, ProblemeTechniqueException {
		Compte compteRecupere = this.compteDAO.findCompteByNumeroCompte(numeroCompte);
		return compteRecupere.getSolde();
	}

	public void retirerArgent(String numeroCompte, int somme) throws DebitRefuseException, CompteIntrouvableException, ProblemeTechniqueException {
		this.debiterCompte(numeroCompte, somme);
	}

	public void deposerArgent(String numeroCompte, int somme) throws CreditRefuseException, CompteIntrouvableException, ProblemeTechniqueException {
		this.crediterCompte(numeroCompte, somme);
	}

	public void virerArgent(String numeroCompteDebit, String numeroCompteCredit, int somme) throws VirementRefuseException, DebitRefuseException, CreditRefuseException, CompteIntrouvableException, ProblemeTechniqueException {
		if(Compte.MONTANT_VIREMENT_MAX_AUTORISE < somme){
			throw new VirementRefuseException();
		}
		this.debiterCompte(numeroCompteDebit, somme);
		this.crediterCompte(numeroCompteCredit, somme);
	}

	public void ajouterCompte(Compte compte) throws ProblemeTechniqueException {
		if(compte != null && compte.getId() > 0){
			this.compteDAO.enregistrerCompte(compte);
		}
	}

	public void supprimerCompte(String numeroCompte) throws ProblemeTechniqueException {
		if(numeroCompte != null && !numeroCompte.isEmpty()){
			this.compteDAO.supprimerCompte(numeroCompte);
		}
	}

}
