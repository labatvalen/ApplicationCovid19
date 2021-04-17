/**
 * Voici le code de l'implementation de la classe ComNonConf
 */


/**
 * Imports
 */
package com.Projet;
import java.time.LocalDate;
import java.util.ArrayList;


public class ComNonConf extends Commune {

	private LocalDate dateDeconfinement;
	
//	************************************ CONSTRUCTOR ************************************
	/**
	 * Constructeur basique ComNonConf
	 *
	 * @param nbDeces un int qui représente le nombre de décès
	 * @param nbContamine un int qui représente le nombre de contaminés
	 * @param nbGuerison un int qui représente le nombre de guérisons
	 * @param historiques une liste d'historiques
	 * @param dateDeconf un String qui représente la date de deconfinement
	 */
	public ComNonConf(String nom, int population, double surface, double longitude, double latitude,int nbDeces, int nbContamine, int nbGuerison,ArrayList<Historique> historiques ,String dateDeconf) {
		super(nom, population, surface, longitude, latitude,nbDeces, nbContamine, nbGuerison, historiques);
		// TODO Auto-generated constructor stub
		Date d = new Date(); 
		this.dateDeconfinement = d.creerDate(dateDeconf);
	}
	
	/**
	 * Ensemble des getters et setters
	 */
//	************************************ GETTER ************************************
	public LocalDate getDateDeconfinement() {
		return this.dateDeconfinement;
	}
	
//	************************************ GETTER ************************************
	public void setDateDeconfinement(String date) {
		Date d = new Date(); 
		this.dateDeconfinement = d.creerDate(date);
	}
	
}
