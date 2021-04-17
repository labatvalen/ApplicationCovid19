/**
 * Voici le code de l'implementation de la classe Departement
 */


/**
 * Imports
 */
package com.Projet;
import java.sql.*;
import java.util.*;

public class Departement {

	/**
	 * Connexion a la BDD
	 */
	public static String url="jdbc:mysql://localhost:3306/Projet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static String user = "projet";
	public static String password = "mdp";
	
	
	private String nomDepartement;
	private String code;
	private ArrayList<Commune> communes;

//	************************************ CONSTRUCTOR ************************************
	/**
	 * Constructeur basique Departement
	 *
	 * @param nomDep un String qui représente le nom du Departement
	 * @param code un String qui représente le code du Departement
	 */
	public Departement(String nomDep, String code) {
		// TODO Auto-generated constructor stub
		this.nomDepartement = nomDep;
		this.code= code;
	}

	/**
	 * Constructeur Departement avec liste de communes dans le departement
	 *
	 * @param nomDep un String qui représente le nom du Departement
	 * @param code un String qui représente le code du Departement
	 * @param communes la liste des communes dans le departement
	 */
	public Departement(String nomDep, String code, ArrayList<Commune> communes) {
		// TODO Auto-generated constructor stub
		this.nomDepartement = nomDep;
		this.code = code;
		this.communes = communes;
	}
	
	
	/**
	 * Ensemble des getters et setters
	 */
//	************************************ GETTER ************************************
	public String getNomDepartement() {
		return this.nomDepartement;
	}

	public String getCode() {
		return this.code;
	}

	public ArrayList<Commune> getCommunes() {
		return this.communes;
	}

//	************************************ SETTER ************************************
	public void setNomDepartement(String nomDep) {
		this.nomDepartement = nomDep;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setCommunes(ArrayList<Commune> com) {
		this.communes = com;
	}

	
	/**
	 * Procedure ajoutDepartement()
	 * <p>
	 * Cette procedure ajoute un departement à la base de donnees
	 *
	 * @param codeRegion un String qui represente le code de la Region dans lequel se situe le Departement
	 * @param code un String qui représente le code du Departement
	 * @param nom un String qui represente le nom du Departement a ajouter
	 */
	public static String ajoutDepartement(String codeRegion, String code, String nom) {
		String result = "";
		if (!codeRegion.isEmpty() && !code.isEmpty() && !nom.isEmpty()) {
			try {
				//SE CONNECTER A LA BDD
				Connection myConn = DriverManager.getConnection(url, user , password);
	
				//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
				Statement myStmt = myConn.createStatement();
				String sql = "SELECT * FROM Region WHERE code = '" + codeRegion + "'";
				ResultSet rs = myStmt.executeQuery(sql);
				if (rs.next()) {
					// On suppose ici que deux dépatements ne peuvent avoir le même nom dans une seule région
					String sql2 = "SELECT * FROM Departement WHERE region_code = '" + codeRegion + "' AND name = '" + nom + "'";
					ResultSet rs2 = myStmt.executeQuery(sql2);
					if (rs2.next()) {
						result = ("Ce nom de département existe déjà dans cette région. Choisissez un autre nom");
					} else {
						String sqla = "SELECT * FROM Departement WHERE code = '" + code + "' OR (name = '" + nom + "' AND region_code = '" + codeRegion + "')";
						ResultSet rsa = myStmt.executeQuery(sqla);
						if (rsa.next()) {
							result = ("Ce code de département existe déjà.  Choisissez un autre code");
						} else {
							String sql3 = "INSERT INTO Departement (region_code,code,name) VALUES ('" + codeRegion + "','" + code + "','" + nom + "')";
							myStmt.executeUpdate(sql3);
							result = ("Département ajouté.");
						}
					}
				} else {
					result = ("Ce code de région n'existe pas . Modifiez le ou créez d'abord une nouvelle region");
				}
			} catch (Exception exc){
				exc.printStackTrace();
			}
		} else {
			result = ("Veuillez entrer des données sous une forme correcte.");
		}
		return result;
	}
	
	/**
	 * Procedure suppressionDepartement()
	 * <p>
	 * Cette procedure supprime un Departement de la base de donnees à partir de son code, ainsi que toutes les Communes du Departement
	 *
	 * @param code un String qui représente le code du Departement
	 */
	public static String suppressionDepartement(String code) {
		String result = "";
		if (!code.isEmpty()) {
			try {
				//SE CONNECTER A LA BDD
				Connection myConn = DriverManager.getConnection(url, user , password);
		
				//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
				Statement myStmt = myConn.createStatement();
				String sql2 = "DELETE IGNORE FROM Commune WHERE departement = '" + code + "'";
				myStmt.executeUpdate(sql2);
				result = result + (" Communes supprimées.");
				String sql = "DELETE IGNORE FROM Departement WHERE code = '" + code + "'";
				myStmt.executeUpdate(sql);
				result = result + (" Departement supprimé.");
			} catch (Exception exc){
				exc.printStackTrace();
			}
		} else {
			result = ("Veuillez entrer tous les paramètres.");
		}
		return result;
	}
	
	/**
	 * Procedure suppressionDepartement()
	 * <p>
	 * Cette procedure supprime un Departement de la base de donnees à partir du code de la Region et de son nom
	 *
	 * @param codeRegion un String qui représente le code de la Region dans lequel est le Departement
	 * @param nom un String qui représente le nom du Departement
	 */
	public static void suppressionDepartement(String codeRegion, String nom) {
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
			
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			String sql2 = "SELECT * FROM Departement WHERE region_code = '" + codeRegion + "' AND name = '" + nom + "'";
			ResultSet rs2 = myStmt.executeQuery(sql2);
			String code = rs2.getString("code");
			sql2 = "DELETE IGNORE FROM Commune WHERE departement = '" + code + "'";
			myStmt.executeUpdate(sql2);
			System.out.println("Communes du Departement suprimées.");
			String sql = "DELETE IGNORE FROM Departement WHERE code = '" + code + "'";
			myStmt.executeUpdate(sql);
			System.out.println("Departement suprimé.");
		} catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * Procedure modifDepartement()
	 * <p>
	 * Cette procedure modifie un Departement dans la base de donnees
	 *
	 * @param CodeRegion un String qui represente le code de la region du Departement
	 * @param nom un String qui représente le nom actuel du Departement
	 * @param nouveauNom un String qui représente le nom modifié du Departement
	 */
	public static void modifDepartement(String CodeRegion, String nom, String nouveauNom) {
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
	
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			if (!nouveauNom.isEmpty() && !nom.isEmpty() && !CodeRegion.isEmpty()) {
				String sql = "UPDATE Departement SET name = '" + nouveauNom + "' WHERE name = '" + nom + "' AND region_code = '" + CodeRegion + "'";				
				myStmt.executeUpdate(sql);
				System.out.println("Departement modifié.");
			}  else {
				System.out.println("Veuillez entrer des données sous une forme correcte.");
			}
		} catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * Procedure modifDepartement()
	 * <p>
	 * Cette procedure modifie un Departement dans la base de donnees
	 *
	 * @param CodeRegion un String qui represente le code du Departement
	 * @param nouveauNom un String qui représente le nom modifié du Departement
	 */
	public static String modifDepartement(String code, String nouveauNom) {
		String result = "";
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
	
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			if (!nouveauNom.isEmpty() && !code.isEmpty()) {
				String sql = "SELECT * FROM Departement WHERE code = '" + code + "'";
				ResultSet rs = myStmt.executeQuery(sql);
				if (!rs.next()) {
					result = ("Ce département n'est pas dans la base de données.");
				} else {
					String sql2 = "UPDATE Departement SET name = '" + nouveauNom + "' WHERE code = '" + code + "'";				
					myStmt.executeUpdate(sql2);
					result = ("Departement modifié.");
				}
			}  else {
				result = ("Veuillez entrer des données sous une forme correcte.");
			}
		} catch (Exception exc){
			exc.printStackTrace();
		}
		return result;
	}
}
