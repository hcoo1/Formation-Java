package fr.banquetoulousaine.gestioncomptes.entite;

import java.io.Serializable;

public class Compte implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int MONTANT_VIREMENT_MAX_AUTORISE = 5000;

	public static int DECOUVERT_AUTORISE = -100;
	
	private long id;
	private String numeroCompte;
	private int solde;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroCompte == null) ? 0 : numeroCompte.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		if (numeroCompte == null) {
			if (other.numeroCompte != null)
				return false;
		} else if (!numeroCompte.equals(other.numeroCompte))
			return false;
		return true;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	public int getSolde() {
		return solde;
	}
	public void setSolde(int solde) {
		this.solde = solde;
	}

}
