/**
 * Voici le code de l'implementation de la classe ComConf
 */


/**
 * Imports
 */
package com.Projet;
import java.time.LocalDate;
import java.util.ArrayList;


public class ComConf extends Commune {

	private LocalDate dateConfinement;
	private int dureePrevisionnelle;
	
//	************************************ CONSTRUCTOR ************************************
	
	/**
	 * Constructeur basique ComConf
	 *
	 * @param nbDeces un int qui représente le nombre de décès
	 * @param nbContamine un int qui représente le nombre de contaminés
	 * @param nbGuerison un int qui représente le nombre de guérisons
	 * @param historiques une liste d'historiques
	 * @param dateConf un String qui représente la date de confinement
	 * @param dureePrev un int qui représente la durée restante
	 */
	public ComConf(String nom, int population, double surface, double longitude, double latitude,int nbDeces, int nbContamine, int nbGuerison, ArrayList<Historique> historiques, String dateConf, int dureePrev) {
		super(nom, population, surface, longitude, latitude,nbDeces, nbContamine, nbGuerison, historiques);
		// TODO Auto-generated constructor stub
		Date d = new Date(); 
		this.dateConfinement = d.creerDate(dateConf);
		this.dureePrevisionnelle = dureePrev;
	}
	

	/**
	 * Ensemble des getters et setters
	 */
//	************************************ GETTER ************************************
	public LocalDate getDateConfinement() {
		return this.dateConfinement;
	}

	public int getDureePrevisionnelle() {
		return this.dureePrevisionnelle;
	}	
	
//	************************************ SETTER ************************************
	public void setDateConfinement(String date) {
		Date d = new Date(); 
		this.dateConfinement = d.creerDate(date);
	}

	public void setDureePrevisionnelle(int d) {
		 this.dureePrevisionnelle = d;
	}	

	
	
	
}
