/**
 * Voici le code de l'implementation de la classe Scenario
 */


/**
 * Imports
 */
package com.Projet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Scenario {

	/**
	 * Connexion a la BDD
	 */
	public static String url="jdbc:mysql://localhost:3306/Projet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static String user = "projet";
	public static String password = "mdp";

	/**
	 * Procédure vider
	 *
	 * Permet de vider les tables Historique et Total
	 */
	public static void vider() {
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
	
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			String sql = "TRUNCATE TABLE Historique";
			myStmt.executeUpdate(sql);
			sql = "TRUNCATE TABLE Total";
			myStmt.executeUpdate(sql);
		} catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * Fonction bissextile
	 * 
	 * Permet de retourner si l'année est bissextile ou non
	 *
	 * @param annee un int qui représente la date
	 * @return un int qui dit si la date est bissextile (1) ou pas (0)
	 */
	public static int bissextile(int annee) {
		int biss = 0;
		
		if (annee % 400 == 0) {
			biss = 1;
		} else if ((annee % 4 == 0) && (annee % 100 != 0)) {
			biss = 1;
		}
		return biss;
	}
	
	/**
	 * Fonction dateSuivante
	 * 
	 * Permet de retourner la date suivante à partir d'une date initiale
	 *
	 * @param date un String qui représente la date
	 * @return une date en String
	 */
	public static String dateSuivante(String date) {
		int jour = Integer.parseInt(date.substring(0,2));
		int mois = Integer.parseInt(date.substring(3,5));
		int annee = Integer.parseInt(date.substring(6));
		int biss = bissextile(annee);
		
		if (jour== 31 && mois==12) {
			jour = 1;
			mois = 1;
			annee = annee + 1;
		} else if ((jour == 30 && (mois==4 || mois==6 || mois==9 || mois==11)) || (jour == 31 && (mois==1 || mois==3 || mois==5 || mois==7 || mois==8 || mois ==10)) || (jour==(28 + biss) && mois==2)) {
			jour = 1;
			mois = mois + 1;
		} else {
			jour = jour+1;
		}
		
		String j = String.valueOf(jour);
		String m = String.valueOf(mois);
		String a = String.valueOf(annee);
		if (j.length() == 1) {
			j = "0" + j;
		}
		if (m.length() == 1) {
			m = "0" + m;
		}
		String nouvelleDate = j + "-" + m + "-" + a;
		return(nouvelleDate);
	}
	
	/**
	 * Procédure initialisation
	 * 
	 * Permet d'initialiser les données pour un nombre de communes données
	 *
	 * @param date un String qui représente la date
	 */
	public static void initialisation(String date) {
		int nombreCommunes = 10;	    // Nombre de Communes de l'étude
		int dureeConfinementMin = 20;	// Durée minimale du confinement (en jours)
		int dureeConfinementMax = 60;	// Durée maximale
		int pourcentagePopContConfMin = 40;	// Pourcentage minimale de population contaminée dans une région confinée
		int pourcentagePopContConfMax = 100;	// Pourcentage maximale de population contaminée dans une région confinée
		int pourcentagePopContNonConfMin = 0;	// Pourcentage minimale de population contaminée dans une région non confinée
		int pourcentagePopContNonConfMax = 39;	// Pourcentage maximale de population contaminée dans une région non confinée
		
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
	
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			for (int i = 1 ; i <= nombreCommunes ; i ++) {
				int confinee = Math.round((float) Math.random());	//On définit aléatoirement si la commune est confinée ou non lors de l'initialisation
				//System.out.print(confinee);
				
				//On recupere la population de la commune 
				int population = 0;
				String sql = "SELECT * FROM Commune WHERE id = " + i;
				ResultSet rs = myStmt.executeQuery(sql);
				if (rs.next()) {
					population = rs.getInt("population_2010");
				}
				
				
				if (confinee == 1) {
					
					// On définit la durée du confinement
					Random rand = new Random();
					int dureeConfinement = rand.nextInt(dureeConfinementMax - dureeConfinementMin + 1) + dureeConfinementMin;
					
					//On définit le nombre de personnes contaminées dans la commune
					int populationContaminee = Math.round((float) (population * (rand.nextInt(pourcentagePopContConfMax - pourcentagePopContConfMin + 1) + pourcentagePopContConfMin)/100.));
					int populationSaine = population - populationContaminee; // au jour 1, pas de morts et pas de gueris
					
					sql = "INSERT INTO Historique (id_commune,date_historique,nombre_contamines_24h,id_confinee,date_confinement,duree_confinement) VALUES ('" + i + "','" + date + "','" + populationContaminee + "'," + confinee + ",'" + date +"'," + dureeConfinement +")";
					myStmt.executeUpdate(sql);
					
					sql = "INSERT INTO Total (id_commune,date,nombre_sains,nombre_contamines,nombre_morts_total,nombre_gueris_total) VALUES ('" + i + "','" + date + "'," + populationSaine + "," + populationContaminee + "," + 0 +"," + 0 +")";
					myStmt.executeUpdate(sql);
				} else {
					//On définit le nombre de personnes contaminées dans la commune
					Random rand = new Random();
					int populationContaminee = Math.round((float) (population * (rand.nextInt(pourcentagePopContNonConfMax - pourcentagePopContNonConfMin + 1) + pourcentagePopContNonConfMin)/100.));
					int populationSaine = population - populationContaminee; // au jour 0, pas de morts et pas de gueris
					
					sql = "INSERT INTO Historique (id_commune,date_historique,nombre_contamines_24h,id_confinee) VALUES ('" + i + "','" + date + "','" + populationContaminee + "'," + confinee +")";
					myStmt.executeUpdate(sql);
					
					sql = "INSERT INTO Total (id_commune,date,nombre_sains,nombre_contamines,nombre_morts_total,nombre_gueris_total) VALUES ('" + i + "','" + date + "'," + populationSaine + "," + populationContaminee + "," + 0 +"," + 0 +")";
					myStmt.executeUpdate(sql);
				}
			}
		} catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * Procédure deroulement
	 * 
	 * Permet de faire une étape de l'algorithme pour un nombre de communes données
	 *
	 * @param dateHier un String qui représente la date d'hier
	 * @param date  un String qui représente la date
	 */
	public static void deroulement(String dateHier, String date) {
		int nombreCommunes = 10;	    // Nombre de Communes de l'étude
		int confinee = 0;
		int confinementRestant = 0;
		int augmentationContaminésNonConfines = 19; 	//en pourcents (x1,19)
		int augmentationContaminésConfines = 90; 	//en pourcents (x0,9)
		int augmentationMorts = 10;	// en pourcents
		int augmentationGueris = 15;	// en pourcents
		int contaminesTotal=0;
		String dateConfinement ="";
		String dateDeconfinement = "";
		int contamines24 = 0;
		int nombreSains = 0;
		int nouveauxContamines = 0;
		int guerisTotal = 0;
		int mortsTotal = 0;
		int dureeConfinementMin = 20;	// Durée minimale du confinement (en jours)
		int dureeConfinementMax = 60;	// Durée maximale
		
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
	
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			Statement myStmt1 = myConn.createStatement();
			for (int i = 1 ; i <= nombreCommunes ; i ++) {
				
				String sql = "SELECT * FROM Historique WHERE date_historique = '" + dateHier + "' AND id_commune = " + i;
				ResultSet rs = myStmt1.executeQuery(sql);
				if (rs.next()) {
					confinee = rs.getInt("id_confinee");
					dateConfinement = rs.getString("date_confinement");
					confinementRestant = rs.getInt("duree_confinement");
					dateDeconfinement = rs.getString("date_dernier_deconfinement");
					contamines24 = rs.getInt("nombre_contamines_24h");
				}
				
				String sqle = "SELECT * FROM Total WHERE id_commune = " + i;
				ResultSet rse = myStmt.executeQuery(sqle);
				if (rse.next()) {
					contaminesTotal = rse.getInt("nombre_contamines");
					mortsTotal = rse.getInt("nombre_morts_total");
					guerisTotal = rse.getInt("nombre_gueris_total");
					nombreSains = rse.getInt("nombre_sains");
				}
				
				if (confinee == 1) {
					if (confinementRestant == 0) {
						
						confinee = 0;
						double cont = contamines24*(1.+(augmentationContaminésNonConfines/100.));
						nouveauxContamines = Math.round((int) cont);
						// si plus de contamines qu'il ne restait de sains, on dit que le nombre de sains qu'il restait sont contaminés
						if (nouveauxContamines>nombreSains) {
							nouveauxContamines = nombreSains;
						}
						int nouveauxDeces = Math.round((int) (contaminesTotal*augmentationMorts/100.));
						int nouveauxGueris = Math.round((int) (contaminesTotal*augmentationGueris/100.));
						sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_dernier_deconfinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + date+"')";
						myStmt.executeUpdate(sql);
						
						
						
						int totalContamines = contaminesTotal + nouveauxContamines - nouveauxGueris - nouveauxDeces;
						int totalGueris = guerisTotal + nouveauxGueris;
						int totalMorts = nouveauxDeces + mortsTotal;
						// on considere que les sains sont ceux qui n'ont jamais eu le virus, et que les décés sont forcément ceux qui ont eu le virus
						int totalSains = nombreSains - nouveauxContamines;
						sql = "UPDATE Total SET date = '"+ date + "', nombre_sains = " + totalSains + ", nombre_contamines = " + totalContamines + ", nombre_morts_total = " + totalMorts + ",nombre_gueris_total = " + totalGueris + " WHERE id_commune = " + i;
						myStmt.executeUpdate(sql);
					} else {
						double cont = contamines24*(augmentationContaminésConfines/100.);
						nouveauxContamines = Math.round((int) cont);
						// si plus de contamines qu'il ne restait de sains, on dit que le nombre de sains qu'il restait sont contaminés
						if (nouveauxContamines>nombreSains) {
							nouveauxContamines = nombreSains;
						}
						int nouveauxDeces = Math.round((int) (contaminesTotal*augmentationMorts/100.));
						confinementRestant = confinementRestant - 1;
						int nouveauxGueris = Math.round((int) (contaminesTotal*augmentationGueris/100.));
						if (dateDeconfinement == null) {
							sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_confinement,duree_confinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + dateConfinement +"'," + confinementRestant + ")";
							myStmt.executeUpdate(sql);
						} else {
							sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_confinement,duree_confinement,date_dernier_deconfinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + dateConfinement +"'," + confinementRestant + ",'" + dateDeconfinement+"')";
							myStmt.executeUpdate(sql);
						}
						
						
						int totalContamines = contaminesTotal + nouveauxContamines - nouveauxGueris - nouveauxDeces;
						int totalGueris = guerisTotal + nouveauxGueris;
						int totalMorts = nouveauxDeces + mortsTotal;
						// on considere que les sains sont ceux qui n'ont jamais eu le virus, et que les décés sont forcément ceux qui ont eu le virus
						int totalSains = nombreSains - nouveauxContamines;
						sql = "UPDATE Total SET date = '"+ date + "', nombre_sains = " + totalSains + ", nombre_contamines = " + totalContamines + ", nombre_morts_total = " + totalMorts + ",nombre_gueris_total = " + totalGueris + " WHERE id_commune = " + i;
						myStmt.executeUpdate(sql);			
					}
			} else {
				// Condition de reconfinement
				if (nombreSains<=2*contaminesTotal) {
					confinee = 1;
					double cont = contamines24*(augmentationContaminésConfines/100.);
					nouveauxContamines = Math.round((int) cont);
					// si plus de contamines qu'il ne restait de sains, on dit que le nombre de sains qu'il restait sont contaminés
					if (nouveauxContamines>nombreSains) {
						nouveauxContamines = nombreSains;
					}
					int nouveauxDeces = Math.round((int) (contaminesTotal*augmentationMorts/100.));
					// On définit la durée du confinement
					Random rand = new Random();
					confinementRestant = rand.nextInt(dureeConfinementMax - dureeConfinementMin + 1) + dureeConfinementMin;
					int nouveauxGueris = Math.round((int) (contaminesTotal*augmentationGueris/100.));
					if (dateDeconfinement==null) {
						sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_confinement,duree_confinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + date +"'," + confinementRestant + ")";
						myStmt.executeUpdate(sql);
					} else {
						sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_confinement,duree_confinement,date_dernier_deconfinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + date +"'," + confinementRestant + ",'" + dateDeconfinement+"')";
						myStmt.executeUpdate(sql);
					}
					
					
					int totalContamines = contaminesTotal + nouveauxContamines - nouveauxGueris - nouveauxDeces;
					int totalGueris = guerisTotal + nouveauxGueris;
					int totalMorts = nouveauxDeces + mortsTotal;
					// on considere que les sains sont ceux qui n'ont jamais eu le virus, et que les décés sont forcément ceux qui ont eu le virus
					int totalSains = nombreSains - nouveauxContamines;
					sql = "UPDATE Total SET date = '"+ date + "', nombre_sains = " + totalSains + ", nombre_contamines = " + totalContamines + ", nombre_morts_total = " + totalMorts + ",nombre_gueris_total = " + totalGueris + " WHERE id_commune = " + i;
					myStmt.executeUpdate(sql);
				} else {
					double cont = contamines24*(1.+(augmentationContaminésNonConfines/100.));
					nouveauxContamines = Math.round((int) cont);
					// si plus de contamines qu'il ne restait de sains, on dit que le nombre de sains qu'il restait sont contaminés
					if (nouveauxContamines>nombreSains) {
						nouveauxContamines = nombreSains;
					}
					int nouveauxDeces = Math.round((int) (contaminesTotal*augmentationMorts/100.));
					int nouveauxGueris = Math.round((int) (contaminesTotal*augmentationGueris/100.));
					
					if (dateDeconfinement==null) {
						sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ")";
						myStmt.executeUpdate(sql);
					} else {
						sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_dernier_deconfinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + dateDeconfinement +"')";
						myStmt.executeUpdate(sql);
					}
					
					
					int totalContamines = contaminesTotal + nouveauxContamines - nouveauxGueris - nouveauxDeces;
					int totalGueris = guerisTotal + nouveauxGueris;
					int totalMorts = nouveauxDeces + mortsTotal;
					// on considere que les sains sont ceux qui n'ont jamais eu le virus, et que les décés sont forcément ceux qui ont eu le virus
					int totalSains = nombreSains - nouveauxContamines;
					sql = "UPDATE Total SET date = '"+ date + "', nombre_sains = " + totalSains + ", nombre_contamines = " + totalContamines + ", nombre_morts_total = " + totalMorts + ",nombre_gueris_total = " + totalGueris + " WHERE id_commune = " + i;
					myStmt.executeUpdate(sql);
				}
			}
			}
		} catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * Procédure initialisation
	 * 
	 * Permet d'initialiser les données pour des régions données
	 *
	 * @param date un String qui représente la date
	 * @param Region1 un String qui représente le code de la région
	 * @param Region2 un String qui représente le code de la région
	 * @param Region3 un String qui représente le code de la région
	 */
	public static void initialisation(String date, String Region1, String Region2, String Region3) {
		int nombreCommunes = 10;	    // Nombre de Communes de l'étude
		int dureeConfinementMin = 20;	// Durée minimale du confinement (en jours)
		int dureeConfinementMax = 60;	// Durée maximale
		int pourcentagePopContConfMin = 40;	// Pourcentage minimale de population contaminée dans une région confinée
		int pourcentagePopContConfMax = 100;	// Pourcentage maximale de population contaminée dans une région confinée
		int pourcentagePopContNonConfMin = 0;	// Pourcentage minimale de population contaminée dans une région non confinée
		int pourcentagePopContNonConfMax = 39;	// Pourcentage maximale de population contaminée dans une région non confinée
		int i = 0;
		String code_dep = "";
		
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
	
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			Statement myStmt1 = myConn.createStatement();
			String sql = "SELECT * FROM Commune WHERE departement IN (SELECT code FROM Departement WHERE region_code = '" + Region1 + "' OR region_code = '" + Region2 + "' OR region_code = '" + Region3 + "')";
			ResultSet rs = myStmt1.executeQuery(sql);
			while(rs.next()) {
;
				//On recupere la population de la commune 
				int population = 0;



					population = rs.getInt("population_2010");
					i = rs.getInt("id");
					
					int confinee = Math.round((float) Math.random());	//On définit aléatoirement si la commune est confinée ou non lors de l'initialisation
					System.out.print(confinee);
				
					if (confinee == 1) {
						
						// On définit la durée du confinement
						Random rand = new Random();
						int dureeConfinement = rand.nextInt(dureeConfinementMax - dureeConfinementMin + 1) + dureeConfinementMin;
						
						//On définit le nombre de personnes contaminées dans la commune
						int populationContaminee = Math.round((float) (population * (rand.nextInt(pourcentagePopContConfMax - pourcentagePopContConfMin + 1) + pourcentagePopContConfMin)/100.));
						int populationSaine = population - populationContaminee; // au jour 1, pas de morts et pas de gueris
						
						sql = "INSERT INTO Historique (id_commune,date_historique,nombre_contamines_24h,id_confinee,date_confinement,duree_confinement) VALUES ('" + i + "','" + date + "','" + populationContaminee + "'," + confinee + ",'" + date +"'," + dureeConfinement +")";
						myStmt.executeUpdate(sql);
						
						sql = "INSERT INTO Total (id_commune,date,nombre_sains,nombre_contamines,nombre_morts_total,nombre_gueris_total) VALUES ('" + i + "','" + date + "'," + populationSaine + "," + populationContaminee + "," + 0 +"," + 0 +")";
						myStmt.executeUpdate(sql);
					} else {
						//On définit le nombre de personnes contaminées dans la commune
						Random rand = new Random();
						int populationContaminee = Math.round((float) (population * (rand.nextInt(pourcentagePopContNonConfMax - pourcentagePopContNonConfMin + 1) + pourcentagePopContNonConfMin)/100.));
						int populationSaine = population - populationContaminee; // au jour 1, pas de morts et pas de gueris
						
						sql = "INSERT INTO Historique (id_commune,date_historique,nombre_contamines_24h,id_confinee) VALUES ('" + i + "','" + date + "','" + populationContaminee + "'," + confinee +")";
						myStmt.executeUpdate(sql);
						
						sql = "INSERT INTO Total (id_commune,date,nombre_sains,nombre_contamines,nombre_morts_total,nombre_gueris_total) VALUES ('" + i + "','" + date + "'," + populationSaine + "," + populationContaminee + "," + 0 +"," + 0 +")";
						myStmt.executeUpdate(sql);
					
				}
			}
		} catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * Procédure déroulement
	 * 
	 * Permet de faire une étape du scénario pour des régions données
	 *
	 * @param dateHier un String qui représente la date d'hier
	 * @param date un String qui représente la date
	 * @param Region1 un String qui représente le code de la région
	 * @param Region2 un String qui représente le code de la région
	 * @param Region3 un String qui représente le code de la région
	 */
	public static void deroulement(String dateHier, String date, String Region1, String Region2, String Region3) {
		int nombreCommunes = 10;	    // Nombre de Communes de l'étude
		int confinee = 0;
		int confinementRestant = 0;
		Commune commune = new Commune("",0,0,0,0);
		int augmentationContaminésNonConfines = 19; 	//en pourcents (x1,19)
		int augmentationContaminésConfines = 90; 	//en pourcents (x0,9)
		int augmentationMorts = 10;	// en pourcents (10% des contaminés)
		int augmentationGueris = 15;	// en pourcents (15
		int contaminesTotal=0;
		String dateConfinement ="";
		String dateDeconfinement = "";
		int contamines24 = 0;
		int nombreSains = 0;
		int nouveauxContamines = 0;
		int guerisTotal = 0;
		int mortsTotal = 0;
		int dureeConfinementMin = 20;	// Durée minimale du confinement (en jours)
		int dureeConfinementMax = 60;	// Durée maximale
		int i = 0;
		String code_dep = "";
		int population = 0;
		Map<Integer,Integer> map = new HashMap<>();
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
	
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			Statement myStmt1 = myConn.createStatement();
			Statement myStmt2 = myConn.createStatement();
			String sql = "SELECT * FROM Commune WHERE departement IN (SELECT code FROM Departement WHERE region_code = '" + Region1 + "' OR region_code = '" + Region2 + "' OR region_code = '" + Region3 + "')";
			ResultSet rs = myStmt1.executeQuery(sql);
			while(rs.next()) {
				//On recupere la population de la commune 
				population = rs.getInt("population_2010");
				int id = rs.getInt("id");
				map.put(id,population);
			}
			for (Map.Entry mapentry : map.entrySet()) {
		           i = (int) mapentry.getKey(); 
		           population = (int) mapentry.getValue();
				String sqla = "SELECT * FROM Historique WHERE date_historique = '" + dateHier + "' AND id_commune = " + i;
				ResultSet rsa = myStmt.executeQuery(sqla);
				if (rsa.next()) {
					confinee = rsa.getInt("id_confinee");
					dateConfinement = rsa.getString("date_confinement");
					confinementRestant = rsa.getInt("duree_confinement");
					dateDeconfinement = rsa.getString("date_dernier_deconfinement");
					contamines24 = rsa.getInt("nombre_contamines_24h");
				}
						
				sql = "SELECT * FROM Total WHERE id_commune = " + i;
				rs = myStmt2.executeQuery(sql);
				if (rs.next()) {
					contaminesTotal = rs.getInt("nombre_contamines");
					mortsTotal = rs.getInt("nombre_morts_total");
					guerisTotal = rs.getInt("nombre_gueris_total");
					nombreSains = rs.getInt("nombre_sains");
				}		
				if (confinee == 1) {
					if (confinementRestant == 0) {			
						confinee = 0;
						double cont = contamines24*(1.+(augmentationContaminésNonConfines/100.));
						nouveauxContamines = Math.round((int) cont);
						// si plus de contamines qu'il ne restait de sains, on dit que le nombre de sains qu'il restait sont contaminés
						if (nouveauxContamines>nombreSains) {
							nouveauxContamines = nombreSains;
						}
						int nouveauxDeces = Math.round((int) (contaminesTotal*augmentationMorts/100.));
						int nouveauxGueris = Math.round((int) (contaminesTotal*augmentationGueris/100.));
						sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_dernier_deconfinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + date+"')";
						myStmt.executeUpdate(sql);
										
						int totalContamines = contaminesTotal + nouveauxContamines - nouveauxGueris - nouveauxDeces;
						int totalGueris = guerisTotal + nouveauxGueris;
						int totalMorts = nouveauxDeces + mortsTotal;
						// on considere que les sains sont ceux qui n'ont jamais eu le virus, et que les décés sont forcément ceux qui ont eu le virus
						int totalSains = nombreSains - nouveauxContamines;
						sql = "UPDATE Total SET date = '"+ date + "', nombre_sains = " + totalSains + ", nombre_contamines = " + totalContamines + ", nombre_morts_total = " + totalMorts + ",nombre_gueris_total = " + totalGueris + " WHERE id_commune = " + i;
						myStmt.executeUpdate(sql);
					} else {
						double cont = contamines24*(augmentationContaminésConfines/100.);
						nouveauxContamines = Math.round((int) cont);
								// si plus de contamines qu'il ne restait de sains, on dit que le nombre de sains qu'il restait sont contaminés
								if (nouveauxContamines>nombreSains) {
									nouveauxContamines = nombreSains;
								}
								int nouveauxDeces = Math.round((int) (contaminesTotal*augmentationMorts/100.));
								confinementRestant = confinementRestant - 1;
								int nouveauxGueris = Math.round((int) (contaminesTotal*augmentationGueris/100.));
								if (dateDeconfinement == null) {
									sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_confinement,duree_confinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + dateConfinement +"'," + confinementRestant + ")";
									myStmt.executeUpdate(sql);
								} else {
									sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_confinement,duree_confinement,date_dernier_deconfinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + dateConfinement +"'," + confinementRestant + ",'" + dateDeconfinement+"')";
									myStmt.executeUpdate(sql);
								}
								
								
								int totalContamines = contaminesTotal + nouveauxContamines - nouveauxGueris - nouveauxDeces;
								int totalGueris = guerisTotal + nouveauxGueris;
								int totalMorts = nouveauxDeces + mortsTotal;
								// on considere que les sains sont ceux qui n'ont jamais eu le virus, et que les décés sont forcément ceux qui ont eu le virus
								int totalSains = nombreSains - nouveauxContamines;
								sql = "UPDATE Total SET date = '"+ date + "', nombre_sains = " + totalSains + ", nombre_contamines = " + totalContamines + ", nombre_morts_total = " + totalMorts + ",nombre_gueris_total = " + totalGueris + " WHERE id_commune = " + i;
								myStmt.executeUpdate(sql);			
							}
					} else {
						// Condition de reconfinement
						if (nombreSains<=2*contaminesTotal) {
							confinee = 1;
							double cont = contamines24*(augmentationContaminésConfines/100.);
							nouveauxContamines = Math.round((int) cont);
							// si plus de contamines qu'il ne restait de sains, on dit que le nombre de sains qu'il restait sont contaminés
							if (nouveauxContamines>nombreSains) {
								nouveauxContamines = nombreSains;
							}
							int nouveauxDeces = Math.round((int) (contaminesTotal*augmentationMorts/100.));
							// On définit la durée du confinement
							Random rand = new Random();
							confinementRestant = rand.nextInt(dureeConfinementMax - dureeConfinementMin + 1) + dureeConfinementMin;
							int nouveauxGueris = Math.round((int) (contaminesTotal*augmentationGueris/100.));
							if (dateDeconfinement==null) {
								sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_confinement,duree_confinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + date +"'," + confinementRestant + ")";
								myStmt.executeUpdate(sql);
							} else {
								sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_confinement,duree_confinement,date_dernier_deconfinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + date +"'," + confinementRestant + ",'" + dateDeconfinement+"')";
								myStmt.executeUpdate(sql);
							}
							
							
							int totalContamines = contaminesTotal + nouveauxContamines - nouveauxGueris - nouveauxDeces;
							int totalGueris = guerisTotal + nouveauxGueris;
							int totalMorts = nouveauxDeces + mortsTotal;
							// on considere que les sains sont ceux qui n'ont jamais eu le virus, et que les décés sont forcément ceux qui ont eu le virus
							int totalSains = nombreSains - nouveauxContamines;
							sql = "UPDATE Total SET date = '"+ date + "', nombre_sains = " + totalSains + ", nombre_contamines = " + totalContamines + ", nombre_morts_total = " + totalMorts + ",nombre_gueris_total = " + totalGueris + " WHERE id_commune = " + i;
							myStmt.executeUpdate(sql);
						} else {
							double cont = contamines24*(1.+(augmentationContaminésNonConfines/100.));
							nouveauxContamines = Math.round((int) cont);
							// si plus de contamines qu'il ne restait de sains, on dit que le nombre de sains qu'il restait sont contaminés
							if (nouveauxContamines>nombreSains) {
								nouveauxContamines = nombreSains;
							}
							int nouveauxDeces = Math.round((int) (contaminesTotal*augmentationMorts/100.));
							int nouveauxGueris = Math.round((int) (contaminesTotal*augmentationGueris/100.));
							
							if (dateDeconfinement==null) {
								sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ")";
								myStmt.executeUpdate(sql);
							} else {
								sql = "INSERT INTO Historique (id_commune,date_historique,nombre_morts_24h,nombre_gueris_24h,nombre_contamines_24h,id_confinee,date_dernier_deconfinement) VALUES ('" + i + "','" + date + "','" + nouveauxDeces + "','" + nouveauxGueris + "','" + nouveauxContamines + "'," + confinee + ",'" + dateDeconfinement +"')";
								myStmt.executeUpdate(sql);
							}
							
							
							int totalContamines = contaminesTotal + nouveauxContamines - nouveauxGueris - nouveauxDeces;
							int totalGueris = guerisTotal + nouveauxGueris;
							int totalMorts = nouveauxDeces + mortsTotal;
							// on considere que les sains sont ceux qui n'ont jamais eu le virus, et que les décés sont forcément ceux qui ont eu le virus
							int totalSains = nombreSains - nouveauxContamines;
							sql = "UPDATE Total SET date = '"+ date + "', nombre_sains = " + totalSains + ", nombre_contamines = " + totalContamines + ", nombre_morts_total = " + totalMorts + ",nombre_gueris_total = " + totalGueris + " WHERE id_commune = " + i;
							myStmt.executeUpdate(sql);
						}
					}
			}
		} catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * Procédure simulation
	 * 
	 * Permet d'effectuer une simulation pour des régions données
	 * 
	 * Fonction exécutée par l'interface graphique
	 *
	 * @param stringnombreDeJours un String qui représente le nombre de jours de la simulation
	 * @param Region1 un String qui représente le code de la région
	 * @param Region2 un String qui représente le code de la région
	 * @param Region3 un String qui représente le code de la région
	 */
	public static String simulation(String stringnombreDeJours, String region1, String region2, String region3) {
		String result = "";
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
	
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			String sql = "SELECT * FROM Total";
			ResultSet rs = myStmt.executeQuery(sql);
			if (rs.next()) {
				result = "Simulation déjà effectuée. Supprimez l'historique.";
			} else {
				
				if (!stringnombreDeJours.isEmpty() && (!region1.isEmpty() || !region2.isEmpty() || !region3.isEmpty())) {
					String sql1 = "SELECT * FROM Commune WHERE Departement IN (SELECT code FROM Departement WHERE region_code IN (SELECT code FROM Region WHERE code = '" + region1 + "' OR code ='" + region2 + "' OR code = '" + region3 + "'))";
					ResultSet rs1 = myStmt.executeQuery(sql1);
					if (!rs1.next()) {
						result = "Aucune des régions entrées n'existe.";
					} else {
					
				
						int nombreDeJours = Integer.parseInt(stringnombreDeJours);		// nombre de jours de simulation
						if (nombreDeJours<0) {
							result = ("Le nombre de jours doit être positif.");
						} else {
							String d1 = "01-01-2020"; // date du début de l'étude
							String d2 = dateSuivante(d1);
							String d3 = dateSuivante(d2);
							
							for (int i=1 ; i<=nombreDeJours ; i++) {
								if (i == 1) {
									Scenario.initialisation(d1,region1,region2,region3);	// on initialise les valeurs dans les tables
									if (!region1.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region1 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme1 = new Histogramme(region1,d1,d2, d3);
										}
									}
									if (!region2.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region2 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme2 = new Histogramme(region2,d1,d2, d3);
										}
									}
									if (!region3.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region3 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme3 = new Histogramme(region3,d1,d2, d3);
										}
									}
									
								} else if (i == 2) {
									Scenario.deroulement(d1,d2,region1,region2,region3);
									if (!region1.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region1 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme1 = new Histogramme(region1,d1,d2, d3);
										}
									}
									if (!region2.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region2 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme2 = new Histogramme(region2,d1,d2, d3);
										}
									}
									if (!region3.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region3 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme3 = new Histogramme(region3,d1,d2, d3);
										}
									}
									
								} else if (i == 3) {
									Scenario.deroulement(d2,d3,region1,region2,region3);
									if (!region1.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region1 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme1 = new Histogramme(region1,d1,d2, d3);
										}
									}
									if (!region2.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region2 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme2 = new Histogramme(region2,d1,d2, d3);
										}
									}
									if (!region3.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region3 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme3 = new Histogramme(region3,d1,d2, d3);
										}
									}
									
								} else {
									d1 = d2;
									d2 = d3;
									d3 = dateSuivante(d2);
								
									Scenario.deroulement(d2, d3,region1,region2,region3);	// on effectue le scenario
								
									if (!region1.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region1 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme1 = new Histogramme(region1,d1,d2, d3);
										}
									}
									if (!region2.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region2 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme2 = new Histogramme(region2,d1,d2, d3);
										}
									}
									if (!region3.isEmpty()) {
										sql = "SELECT * FROM Region WHERE code = '" + region3 + "'";
										rs = myStmt.executeQuery(sql);
										if (rs.next()) {
											Histogramme histogramme3 = new Histogramme(region3,d1,d2, d3);
										}
									}
								}
							}
							result = ("Simulation effectuée.");
						}
					}
				} else {
					result = "Veuillez entrer des paramètres";
				}
			}
		} catch (Exception exc){
			exc.printStackTrace();
		}
		return result;
	}
	
	
	
	public static HashMap<String, Commune> constructionLstVilles(String commune1, String commune2) {
		// On met toutes les villes de la base de données sous forme de liste de villes
		HashMap<String, Commune> liste_ville = new HashMap<String, Commune>();
		try {        	
			// Connection à la BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
			Statement myStmt = myConn.createStatement();
					
			String requete = "SELECT * FROM Commune WHERE id IN (SELECT id_commune FROM Total)";
			ResultSet rs = myStmt.executeQuery(requete);
			boolean next = rs.next();		
			while (next) {
				Commune newCommune = new Commune(null, 0, 0, 0, 0);    	
				String nom = rs.getString("nom_reel");
				newCommune.setNom(nom);
			    newCommune.setId(rs.getInt("id"));
				newCommune.setPopulation(rs.getInt("population_2010"));
				newCommune.setSurface(rs.getDouble("surface"));
				newCommune.setLatitude(rs.getDouble("latitude_deg"));
				newCommune.setLongitude(rs.getDouble("longitude_deg"));
					
				liste_ville.put(nom, newCommune);
				next=rs.next();
			}
				rs.close();	
		} catch (Exception exc) {
			exc.printStackTrace();
		}
				
		System.out.println(liste_ville.size());
	
			// pour chaque ville, on la relie a ses copines
			for(Map.Entry<String, Commune> mapEntry : liste_ville.entrySet()) {
				Commune c = mapEntry.getValue();
				Map<Commune, Double> distance = c.communesAdjacentesMap();
					
					
				for(Map.Entry<Commune, Double> distanceEntry : distance.entrySet()) {
					if (liste_ville.get(distanceEntry.getKey().getNom()) != null) {					
						c.addDestination(liste_ville.get(distanceEntry.getKey().getNom()), distanceEntry.getValue());
					} else {
						System.out.println(distanceEntry.getKey().getNom());
					}
				}
			}
		return(liste_ville);		
	}
	
	public static Graphe constructionGraphe(HashMap<String, Commune> liste_ville, String commune1, String commune2) {

			Set<Commune> setCommunes = new HashSet<Commune>(liste_ville.values());
				
			Graphe graphe = new Graphe(setCommunes);
			
			Iterator<Commune> it = setCommunes.iterator();
			while(it.hasNext()) {
				Commune c = it.next();
				
				// On verifie si la commune est non confinee 
				if (Commune.etatCom(c) == 0) {
					  graphe.addCommune(c);
				}
			}
		return(graphe);		
	}
	
	
	public static String testadj(String commune1, String commune2) {
		String result;
		if (!commune1.isEmpty() && !commune2.isEmpty()) {
		String nC1 = "";
		String nC2 = "";
		result = "-1";
		HashMap<String, Commune> liste_ville = constructionLstVilles(commune1, commune2);
		Graphe graphe = constructionGraphe(liste_ville, commune1, commune2);
		// On remet les id sous leur forme réelle
		int id1 = Integer.parseInt(commune1);
		int id2 = Integer.parseInt(commune2);
		try {
		// Connection à la BDD
		Connection myConn = DriverManager.getConnection(url, user , password);
		Statement myStmt = myConn.createStatement();
							
		String requete = "SELECT * FROM Commune where id = " + id1;
		ResultSet rs = myStmt.executeQuery(requete);
		if (rs.next()) {
			nC1=rs.getString("nom_reel");
		}
		requete = "SELECT * FROM Commune where id = " + id2;
		rs = myStmt.executeQuery(requete);
		if (rs.next()) {
			nC2=rs.getString("nom_reel");
		}
		
		} catch (Exception exc){
			exc.printStackTrace();
		}
		
			if(liste_ville.keySet().contains(nC1)){
				graphe = Dijkstra.calculPlusCourtCheminDepuisSource(graphe,liste_ville.get(nC1) );
			} else {
				result = result + ". Cette commune ne fait pas partie des communes étudiées dans la zone choisie";
				return result;
			}
				
			Iterator<Commune> iter = graphe.getCommunes().iterator();
			

			
			while(iter.hasNext()) {
				Commune p =iter.next();
				//POur obtenir la distance entre la source et une commune donnée uniquement, remplacer le nom de la commune ci-dessous
				if (p.getNom()==liste_ville.get(nC2).getNom()){
					result = String.valueOf(p.getDistance());
					result = result + ". Pour aller à " +p.getNom() + ", il faut passer par " +  p.getCheminLePlusCourt() + " -> Distance: " + p.getDistance() + " km";
				}  
				//Dans le cas ou la distance entre deux communes n'a pas été reévaluée, cela correspond à une erreur de BDD (commune sans commune adjacente)
				if (p.getDistance() == Double.MAX_VALUE){
					result = result + ". Pour aller à " +p.getNom() + ", il n'y a pas de chemin avec " + nC1 + " comme source (Pb de BDD)";
					
				}
				else {
					result = String.valueOf(p.getDistance());
					result = result + ". Pour aller à " +p.getNom() + ", il faut passer par " +  p.getCheminLePlusCourt() + " -> Distance: " + p.getDistance() + " km";
				}
			}
	
	//	************************************ DISTANCE MINIMUM ENTRE 2 COMMUNES ************************************
			 System.out.println("\n************************** DISTANCE MINIMUM ENTRE DEUX COMMUNES **************************");
			   
	//	************************************ COMMUNES ADJACENTES ************************************
			 System.out.println("\n************************** COMMUNES ADJACENTES **************************"+"\nLes communes adjacentes de "+ liste_ville.get(nC1)+ " sont " + liste_ville.get(nC1).communesAdjacentesMap());
	
	//  ************************************ DISTANCES AVEC COMMUNES ADJACENTES ************************************
			 Distance distance_0_2 = new Distance(liste_ville.get("Lyon"),liste_ville.get(nC1),0.00);
			 System.out.println("\n************************** DISTANCES AVEC COMMUNES ADJACENTES **************************\n"+ distance_0_2.distancesCommunesAdjacentes(liste_ville.get(nC1)));
			 
	}     else {
		result = "Veuillez remplir les champs";
	} 
		return result;	
	}
	
	
}
