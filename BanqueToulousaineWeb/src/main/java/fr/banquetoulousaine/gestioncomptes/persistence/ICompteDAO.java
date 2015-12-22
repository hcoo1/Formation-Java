package fr.banquetoulousaine.gestioncomptes.persistence;

import fr.banquetoulousaine.gestioncomptes.entite.Compte;
import fr.banquetoulousaine.gestioncomptes.exceptions.CompteIntrouvableException;
import fr.banquetoulousaine.gestioncomptes.exceptions.ProblemeTechniqueException;

public interface ICompteDAO {
	
	public Compte findCompteByNumeroCompte(String numeroCompte) throws CompteIntrouvableException, ProblemeTechniqueException;
	public void enregistrerCompte(Compte compte) throws ProblemeTechniqueException;
	public void supprimerCompte(String numeroCompte) throws ProblemeTechniqueException;

}
