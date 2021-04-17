/**
 * Voici le code de l'implementation de la classe Test
 */


/**
 * Imports
 */
package com.Projet;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;




public class Test {

	/**
	 * Connexion a la BDD
	 */
	public static String url="jdbc:mysql://localhost:3306/Projet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static String user = "projet";
	public static String password = "mdp";

	/**
	 * Procédure main
	 *
	 * C'est la fonction principale de l'exécution sans interface graphique
	 */
	public static void main(String[] args) {
		int nombreDeJours = 3;		// nombre de jours de simulation
		String date = "01-01-2020"; // date du début de l'étude
		String dateSuivante = date;
		String region1 = "01";
		String region2 = "08";
		String region3 = "09";
		/*
		Scenario.vider(); // vide les tables remplies par le scenario precedent
		Scenario.initialisation(date,region1,region2,region3);	// on initialise les valeurs dans les tables
		for (int i=1 ; i<=nombreDeJours ; i++) {
			dateSuivante = Scenario.dateSuivante(date);	// on passe au lendemain
			Scenario.deroulement(date, dateSuivante,region1,region2,region3);	// on effectue le scenario
			date = dateSuivante;
		}*/
		System.out.println(Scenario.bissextile(2019));
	}
}
