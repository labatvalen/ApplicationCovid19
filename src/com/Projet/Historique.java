/**
 * Voici le code de l'implementation de la classe Historique
 */


/**
 * Imports
 */
package com.Projet;
import java.time.LocalDate;

public class Historique {
	private int nbDeces24h;
	private int nbNouveauContamine24h;
	private int nbGuerison24h;
	boolean estConfine;
	LocalDate date;
	
//	************************************ CONSTRUCTOR ************************************
	
	/**
	 * Constructeur basique Historique
	 *
	 * @param nbDeces24h un int qui représente le nombre de décès en 24h
	 * @param nbNouveauContamine24h un int qui représente le nombre de nouveaux contaminés
	 * @param nbGuerison24h un int qui représente le nombre de nouvelles guérisons
	 * @param estConfinee un boolean qui signifie si une commune est confinée ou non
	 * @date une LocalDate qui représente la date
	 */
	public Historique(int nbDeces24h, int nbNouveauContamine24h, int nbGuerison24h, boolean estConfine, LocalDate date) {
	// TODO Auto-generated constructor stub
		this.nbDeces24h = nbDeces24h;
		this.nbNouveauContamine24h = nbNouveauContamine24h;
		this.nbGuerison24h = nbGuerison24h;
		this.estConfine = estConfine;
		this.date = date;
	}

}
