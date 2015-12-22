package fr.banquetoulousaine.gestioncomptes.persistence.impl;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
import java.util.ArrayList;

import fr.banquetoulousaine.gestioncomptes.entite.Compte;
import fr.banquetoulousaine.gestioncomptes.exceptions.CompteIntrouvableException;
import fr.banquetoulousaine.gestioncomptes.exceptions.ProblemeTechniqueException;
import fr.banquetoulousaine.gestioncomptes.persistence.ICompteDAO;

public class CompteDAOFichierBinaireImpl implements ICompteDAO {
	
	@SuppressWarnings("unchecked")
	private ArrayList<Compte> lireComptesEnregistres() throws ProblemeTechniqueException, NotSerializableException, FileNotFoundException {
		try{
			FileInputStream fin = new FileInputStream("C:/Users/ab/Documents/Activities/Training/Source_de_donnees/comptes2.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			ArrayList<Compte> listeComptes = (ArrayList<Compte>) ois.readObject();
			ois.close();
			return listeComptes;
		}catch(EOFException e){
			this.majFichierDesComptes(new ArrayList<Compte>());
			return lireComptesEnregistres();
		}catch(WriteAbortedException e){
			this.majFichierDesComptes(new ArrayList<Compte>());
			return lireComptesEnregistres();
		}catch(IOException e){
			throw new ProblemeTechniqueException(e);
		}catch(ClassNotFoundException e){
			throw new ProblemeTechniqueException(e);
		}
	}
	
	private void majFichierDesComptes(ArrayList<Compte> listeComptes) throws ProblemeTechniqueException{
		try{
			FileOutputStream fout = new FileOutputStream("C:/Users/ab/Documents/Activities/Training/Source_de_donnees/comptes2.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(listeComptes);
			oos.close();
		}catch(IOException e){
			throw new ProblemeTechniqueException(e);
		}
	}

	public Compte findCompteByNumeroCompte(String numeroCompte) throws CompteIntrouvableException {
		try{
			ArrayList<Compte> comptesEnregistres = this.lireComptesEnregistres();
			if(comptesEnregistres != null){
				for(Compte compte : comptesEnregistres){
					if(compte.getNumeroCompte().equals(numeroCompte)){
						return compte;
					}
				}
			}
			throw new CompteIntrouvableException();
		}catch(FileNotFoundException e){
			throw new CompteIntrouvableException();
		} catch (NotSerializableException e) {
			throw new CompteIntrouvableException();
		} catch (ProblemeTechniqueException e) {
			throw new CompteIntrouvableException();
		}
	}

	private void sauvegarderPremierCompte(Compte compte) throws ProblemeTechniqueException {
		ArrayList<Compte> listeComptes = new ArrayList<Compte>();
		listeComptes.add(compte);
		this.majFichierDesComptes(listeComptes);
	}

	private void majCompteEnregistre(Compte compte) throws ProblemeTechniqueException {
		try{
			ArrayList<Compte> listeComptes = this.lireComptesEnregistres();
			if(listeComptes != null){
				listeComptes.remove(compte);
				listeComptes.add(compte);
			}else{
				listeComptes = new ArrayList<Compte>();
			}
			this.majFichierDesComptes(listeComptes);
		}catch(NotSerializableException e){
			this.sauvegarderPremierCompte(compte);
		}catch(FileNotFoundException e){
			this.sauvegarderPremierCompte(compte);
		}
	}

	public void enregistrerCompte(Compte compte) throws ProblemeTechniqueException {
		if(compte != null){
			this.majCompteEnregistre(compte);
		}
	}

	public void supprimerCompte(String numeroCompte) throws ProblemeTechniqueException {
		try{
			ArrayList<Compte> listeComptes = this.lireComptesEnregistres();
			for (Compte compte2 : listeComptes) {
				if(compte2.getNumeroCompte().equals(numeroCompte)){
					listeComptes.remove(compte2);
				}
			}
			this.majFichierDesComptes(listeComptes);
		}catch(FileNotFoundException e){
			throw new ProblemeTechniqueException(e);
		} catch (NotSerializableException e) {
			throw new ProblemeTechniqueException(e);
		}
	}

}
