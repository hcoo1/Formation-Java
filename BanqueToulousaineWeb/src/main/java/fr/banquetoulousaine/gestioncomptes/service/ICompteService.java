package fr.banquetoulousaine.gestioncomptes.service;

import fr.banquetoulousaine.gestioncomptes.entite.Compte;
import fr.banquetoulousaine.gestioncomptes.exceptions.CompteIntrouvableException;
import fr.banquetoulousaine.gestioncomptes.exceptions.CreditRefuseException;
import fr.banquetoulousaine.gestioncomptes.exceptions.DebitRefuseException;
import fr.banquetoulousaine.gestioncomptes.exceptions.ProblemeTechniqueException;
import fr.banquetoulousaine.gestioncomptes.exceptions.VirementRefuseException;

public interface ICompteService {
	
	public void ajouterCompte(Compte compte) throws ProblemeTechniqueException;
	public void supprimerCompte(String numeroCompte) throws ProblemeTechniqueException;
	public int consulterSolde(String numeroCompte) throws CompteIntrouvableException, ProblemeTechniqueException;
	public void retirerArgent(String numeroCompte, int somme) throws DebitRefuseException, CompteIntrouvableException, ProblemeTechniqueException;
	public void deposerArgent(String numeroCompte, int somme) throws CreditRefuseException, CompteIntrouvableException, ProblemeTechniqueException;
	public void virerArgent(String numeroCompteDebit, String numeroCompteCredit, int somme) throws VirementRefuseException, DebitRefuseException, CreditRefuseException, CompteIntrouvableException, ProblemeTechniqueException;

}
