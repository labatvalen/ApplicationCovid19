/**
 * Voici le code de l'implementation de la classe Date
 */


/**
 * Imports
 */
package com.Projet;

import java.time.LocalDate;

public class Date {
	
	/**
	 * Fonction creerDate
	 *
	 * @param date un String qui représente la date
	 */
	public LocalDate creerDate(String date) {
		// TODO Auto-generated constructor stub
		String[] chaine = date.split("-");
		int j = Integer.parseInt(chaine[0]);
		int m = Integer.parseInt(chaine[1]);
		int a = Integer.parseInt(chaine[2]);
		return LocalDate.of(a, m, j);
	}

}
