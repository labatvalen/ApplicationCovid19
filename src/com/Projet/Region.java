/**
 * Voici le code de l'implementation de la classe Region
 */


/**
 * Imports
 */
package com.Projet;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;


public class Region {
	
	/**
	 * Connexion a la BDD
	 */
	public static String url="jdbc:mysql://localhost:3306/Projet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static String user = "projet";
	public static String password = "mdp";
	
	
	private String code;
	private String nomRegion;
	private ArrayList<Departement> departements;
	
//	************************************ CONSTRUCTOR ************************************
	
	/**
	 * Constructeur Region
	 *
	 * @param code un String qui represente le code de la Region
	 * @param nomReg un String qui represente le nom de la Region
	 */
	public Region(String code, String nomReg) {
		this.nomRegion = nomReg;
		this.code = code;
	}

	
	/**
	 * Deuxième Constructeur Region
	 *
	 * @param code un String qui represente le code de la Region
	 * @param nomReg un String qui represente le nom de la Region
	 * @param dep un ArrayList de Departements qui represente la liste de departements dans la region
	 */
	public Region(String code, String nomReg, ArrayList<Departement> dep) {
		// TODO Auto-generated constructor stub
		this.nomRegion = nomReg;
		this.departements = dep;
		this.code = code;
	}
	
	
	
	/**
	 * Ensemble des getters et setters
	 */
//	************************************ GETTER ************************************
	public String getCode() {
		return this.code;
	}
	
	
	public String getNomRegion() {
		return this.nomRegion;
	}
	
	public ArrayList<Departement> getDepartements() {
		return this.departements;
	}
	
//	************************************ SETTER ************************************	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setNomRegion(String nomReg) {
	this.nomRegion = nomReg;
	}
	
	public void setDepartements(ArrayList<Departement> dep) {
		this.departements = dep;
	}

	
	
	/**
	 * Procedure ajoutRegion()
	 * <p>
	 * Cette procedure ajoute une Region à la base de donnees
	 *
	 * @param code un String qui represente le code de la Region
	 * @param nom un String qui represente le nom de la Region
	 */
	public static String ajoutRegion(String code, String nom) {
		String result = "";
		if (!code.isEmpty() && !nom.isEmpty()) {
			if (code.length() <= 3) {
				try {
					//SE CONNECTER A LA BDD
					Connection myConn = DriverManager.getConnection(url, user , password);
	
					//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
					Statement myStmt = myConn.createStatement();
					String sql = "SELECT * FROM Region WHERE code = '" + code + "' AND nom = '" + nom + "'";
					ResultSet rs = myStmt.executeQuery(sql);
					if (rs.next()) {
						result = ("Cette région est déjà dans la base de données.");
					} else {
						sql = "SELECT * FROM Region WHERE code = '" + code + "' OR nom = '" + nom + "'";
						rs = myStmt.executeQuery(sql);
						if (rs.next()) {
							result = ("Cet id ou ce nom existe déjà.");
						} else {
							sql = "INSERT INTO Region (code,nom) VALUES ('" + code + "','" + nom + "')";
							myStmt.executeUpdate(sql);
							result = ("Région ajoutée.");
						}
					}
				}
				catch (Exception exc){
					exc.printStackTrace();
				}
			} else {
				result = ("Le code doit avoir trois composantes ou moins.");
			}
		} else {
			result = ("Veuillez entrer tous les paramètres.");
		}
		return result;
	}
	
	
	/**
	 * Procedure suppressionRegion()
	 * <p>
	 * Cette procedure supprime une Region à la base de donnees
	 *
	 * @param code un String qui represente le code de la Region
	 */
	public static String suppressionRegion(String code) {
		String result = "";
		if (!code.isEmpty()) {
			try {
				//SE CONNECTER A LA BDD
				Connection myConn = DriverManager.getConnection(url, user , password);

				//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
				Statement myStmt = myConn.createStatement();
				
				String codeDep = "";
				String sql2 = "SELECT * FROM Departement WHERE region_code = '" + code + "'";
				ResultSet rs2 = myStmt.executeQuery(sql2);
				while (rs2.next()) {
					codeDep = rs2.getString("code");
					String sql = "DELETE IGNORE FROM Commune WHERE departement = '" + codeDep + "'";
					myStmt.executeUpdate(sql);
					result = result + (" Communes suprimées.");
					sql = "DELETE IGNORE FROM Departement WHERE code = '" + codeDep + "'";
					myStmt.executeUpdate(sql);
					result = result + (" Departements suprimés.");
				}
				String sql = "DELETE IGNORE FROM Region WHERE code = '" + code + "'";
				myStmt.executeUpdate(sql);
				result = result + (" Région supprimée.");
			}
			catch (Exception exc){
				exc.printStackTrace();
			}
		} else {
			result = ("Veuillez entrer tous les paramètres.");
		}
		return result;
	}
	
	/**
	 * Procedure modifRegion()
	 * <p>
	 * Cette procedure modifie une Region dans la base de donnees
	 *
	 * @param code un String qui represente le code de la Region
	 * @param nom un String qui represente le nouveau nom de la Region
	 */
	public static String modifRegion(String code, String nom) {
		String result = "";
		if (!code.isEmpty() && !nom.isEmpty()) {
			try {
				//SE CONNECTER A LA BDD
				Connection myConn = DriverManager.getConnection(url, user , password);
	
				//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
				Statement myStmt = myConn.createStatement();
				String sql = "SELECT * FROM Region WHERE code = '" + code + "'";
				ResultSet rs = myStmt.executeQuery(sql);
				if (!rs.next()) {
					result = ("Cette région n'est pas dans la base de données.");
				} else {
					sql = "UPDATE Region SET nom = '" + nom + "' WHERE code = '" + code + "'";
					myStmt.executeUpdate(sql);
					result = ("Région modifiée.");
				}
			}
			catch (Exception exc){
				exc.printStackTrace();
			}
		} else {
			result = ("Veuillez entrer tous les paramètres.");
		}
		return result;
	}
}
