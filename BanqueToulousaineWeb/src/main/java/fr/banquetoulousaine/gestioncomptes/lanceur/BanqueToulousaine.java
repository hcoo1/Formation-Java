package fr.banquetoulousaine.gestioncomptes.lanceur;

import fr.banquetoulousaine.gestioncomptes.entite.Compte;
import fr.banquetoulousaine.gestioncomptes.exceptions.CompteIntrouvableException;
import fr.banquetoulousaine.gestioncomptes.exceptions.CreditRefuseException;
import fr.banquetoulousaine.gestioncomptes.exceptions.DebitRefuseException;
import fr.banquetoulousaine.gestioncomptes.exceptions.ProblemeTechniqueException;
import fr.banquetoulousaine.gestioncomptes.exceptions.VirementRefuseException;
import fr.banquetoulousaine.gestioncomptes.service.ICompteService;
import fr.banquetoulousaine.gestioncomptes.service.impl.CompteServiceImpl;

/**
 * Hello world!
 *
 */
public class BanqueToulousaine {
	
	private ICompteService compteService;
	
	public BanqueToulousaine(){
		try{
			this.compteService = new CompteServiceImpl();
	    	
	    	Compte compte1 = new Compte();
	    	compte1.setId(1);
	    	compte1.setNumeroCompte("123456789CO1");
	    	compte1.setSolde(0);
	    	this.compteService.ajouterCompte(compte1);
	    	
	    	Compte compte2 = new Compte();
	    	compte2.setId(2);
	    	compte2.setNumeroCompte("123456789CO2");
	    	compte2.setSolde(0);
	    	this.compteService.ajouterCompte(compte2);
	    	
	    	Compte compte3 = new Compte();
	    	compte3.setId(3);
	    	compte3.setNumeroCompte("123456789CO3");
	    	compte3.setSolde(0);
	    	this.compteService.ajouterCompte(compte3);
	    	
	    	this.compteService.deposerArgent("123456789CO1", 300);
	    	this.compteService.deposerArgent("123456789CO2", 1000);
	    	
	    	this.compteService.retirerArgent("123456789CO2", 500);
	    	
	    	this.compteService.virerArgent("123456789CO1", "123456789CO3", 200);
	    	
	    	System.out.println("Solde du compte n°" + compte1.getNumeroCompte() + " : " + this.compteService.consulterSolde("123456789CO1"));
	    	System.out.println("Solde du compte n°" + compte2.getNumeroCompte() + " : " + this.compteService.consulterSolde("123456789CO2"));
	    	System.out.println("Solde du compte n°" + compte3.getNumeroCompte() + " : " + this.compteService.consulterSolde("123456789CO3"));
    	
		}catch(CompteIntrouvableException e){
			System.out.println("Compte introuvable.");
		}catch(CreditRefuseException e){
			System.out.println("Crédit refusé.");
		}catch(DebitRefuseException e){
			System.out.println("Débit refusé.");
		}catch(VirementRefuseException e){
			System.out.println("Virement refusé.");
		} catch (ProblemeTechniqueException e) {
			System.out.println("Problème technique.");
			e.printStackTrace();
		}
	}	
	
    public static void main( String[] args ) {
    	new BanqueToulousaine();    	
    }
}
