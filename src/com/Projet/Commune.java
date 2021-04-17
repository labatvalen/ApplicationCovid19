/**
 * Voici le code de l'implementation de la classe Commune
 */


/**
 * Imports
 */
package com.Projet;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;

public class Commune {
	
	/**
	 * Connexion a la BDD
	 */
	public static String url="jdbc:mysql://localhost:3306/Projet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static String user = "projet";
	public static String password = "mdp";
	
	protected String nom;
	protected int population;
	protected double surface;
	protected double longitude;
	protected double latitude;
	protected int nbDeces24h;
	protected int nbNouveauContamine24h;
	protected int nbGuerison24h;
	protected ArrayList<Historique> historiques;

	protected int id;
	private Double distance = Double.MAX_VALUE;
	public HashMap<Commune, Double> communesAdjacentes = new HashMap<>();
	private List<Commune> cheminLePlusCourt = new LinkedList<>();

//	************************************ CONSTRUCTOR ************************************
	/**
	 * Constructeur basique Commune
	 *
	 * @param nom un String qui représente le nom de la Commune
	 * @param population, un Int qui représente la population de la commune
	 * @param surface un double qui représente la surface de la Commune
	 * @param longitude un double qui représente la longitude de la Commune
	 * @param latitude un double qui représente la latitude de la Commune
	 */
	public Commune(String nom, int population, double surface, double longitude, double latitude) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.population = population;
		this.surface = surface;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	/**
	 * Constructeur Commune avec données 24 heures
	 *
	 * @param nom un String qui représente le nom de la Commune
	 * @param population, un Int qui représente la population de la commune
	 * @param surface un double qui représente la surface de la Commune
	 * @param longitude un double qui représente la longitude de la Commune
	 * @param latitude un double qui représente la latitude de la Commune
	 * @param nbDeces un int qui représente le nombre de deces en 24 heures dans la Commune
	 * @param nbContamine un int qui represente le nombre de contamines en 24h dans la Commune
	 * @param ndGuerison un int qui représente le nombre de guérisons en 24h dans la Commune
	 */
	public Commune(String nom, int population, double surface, double longitude, double latitude, int nbDeces, int nbContamine, int nbGuerison) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.population = population;
		this.surface = surface;
		this.longitude = longitude;
		this.latitude = latitude;
		this.nbDeces24h = nbDeces;
		this.nbNouveauContamine24h = nbContamine;
		this.nbGuerison24h = nbGuerison;
	}

	/**
	 * Constructeur Commune avec données 24 heures et liste d'Historique
	 *
	 * @param nom un String qui représente le nom de la Commune
	 * @param population, un Int qui représente la population de la commune
	 * @param surface un double qui représente la surface de la Commune
	 * @param longitude un double qui représente la longitude de la Commune
	 * @param latitude un double qui représente la latitude de la Commune
	 * @param nbDeces un int qui représente le nombre de deces en 24 heures dans la Commune
	 * @param nbContamine un int qui represente le nombre de contamines en 24h dans la Commune
	 * @param ndGuerison un int qui représente le nombre de guérisons en 24h dans la Commune
	 * @param hist une liste d'Historique
	 */
	public Commune(String nom, int population, double surface, double longitude, double latitude, int nbDeces, int nbContamine, int nbGuerison, ArrayList<Historique> hist) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.population = population;
		this.surface = surface;
		this.longitude = longitude;
		this.latitude = latitude;
		this.nbDeces24h = nbDeces;
		this.nbNouveauContamine24h = nbContamine;
		this.nbGuerison24h = nbGuerison;
		this.historiques = hist;
	}
	
	/**
	 * Constructeur test Commune
	 *
	 * @param communeName un String qui représente le nom de la commune
	 * @param test un boolean
	 */
	public Commune(String communeName, boolean test) {
		this.nom = communeName;
	}
	
	/**
	 * Constructeur Commune depuis la BDD
	 *
	 * @param communeName un String qui représente le nom de la commune
	 */
	public Commune(String communeName) {
			try {
				//SE CONNECTER A LA BDD
				Connection myConn = DriverManager.getConnection(url, user , password);

				//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
				Statement myStmt = myConn.createStatement();
			
				String requete = "SELECT * FROM Commune WHERE nom_reel='"+communeName+"' ";
				ResultSet rs = myStmt.executeQuery(requete);
				
				boolean next = rs.next();
				
				while(next) {
						this.setNom(rs.getString("nom_reel"));
						this.setPopulation(rs.getInt("population_2010"));
						this.setSurface(rs.getDouble("surface"));
						this.setLatitude(rs.getDouble("latitude_deg"));
						this.setLongitude(rs.getDouble("longitude_deg"));
						next=rs.next();
				}
				rs.close();
			}      
			catch (Exception exc){
				exc.printStackTrace();
			}
	}	

	/**
	 * Ensemble des getters et setters
	 */
//	************************************ GETTER ************************************
	public String getNom() {
		return this.nom;
	}
	
	public int getId() {
		return this.id;
	}

	public int getPopulation() {
		return this.population;
	}
	
	public double getSurface() {
		return this.surface;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public int getNbDeces24h() {
		return this.nbDeces24h;
	}

	public int getNbNouveauContamine24h() {
		return this.nbNouveauContamine24h;
	}

	public int getNbGuerison24h() {
		return this.nbGuerison24h;
	}

	public Double getDistance() {
		return this.distance;
	}

	public List<Commune> getCheminLePlusCourt(){
		return this.cheminLePlusCourt;
	}

//	************************************ SETTER ************************************
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
	
	public void setSurface(double surface) {
		this.surface = surface;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public void setNbDeces24h(int nbDeces) {
		this.nbDeces24h = nbDeces;
	}

	public void setNbNouveauContamine24h(int nbContamine) {
		this.nbNouveauContamine24h = nbContamine;
	}

	public void setNbGuerison24h(int nbGuerison) {
		this.nbGuerison24h = nbGuerison;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public void setCheminLePlusCourt(List<Commune> cheminLePlusCourt) {
		this.cheminLePlusCourt = cheminLePlusCourt;
	}

//	************************************ METHODS ************************************
	
	public String toString() {
	    //return  nom+ "[Pop:" +population+ ",Surf:"+ surface + ",Lat:" + latitude + ",Long:"+longitude+"]";
	      return nom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Commune other = (Commune) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
	return true;
	}

//	*********************************** FONCTIONS ***********************************
	
	/**
	 * Procedure ajoutCommune()
	 * <p>
	 * Cette procedure ajoute une Commune à la base de donnees
	 *
	 * @param codeDepartement un String qui represente le code du departement dans lequel se situe la Commune
	 * @param nom un String qui represente le nom de la Commune a ajouter
	 * @param stringPopulation un String qui représente la population de la Commune
	 * @param stringSurface, un String qui représente la surface de la Commune
	 * @param stringLatitude un String qui représente la latitude à laquelle se situe la Commune
	 * @param stringLongitude un String qui représente la longitude à laquelle se situe la Commune
	 */
	public static String ajoutCommune(String codeDepartement, String nom, String stringPopulation, String stringSurface, String stringLatitude, String stringLongitude) {
		String result = "";
		if (!codeDepartement.isEmpty() && !nom.isEmpty() && !stringPopulation.isEmpty() && !stringSurface.isEmpty() && !stringLatitude.isEmpty() && !stringLongitude.isEmpty()) {
			
			int population = Integer.parseInt(stringPopulation);
			double surface = Double.parseDouble(stringSurface);
			double longitude = Double.parseDouble(stringLongitude);
			double latitude = Double.parseDouble(stringLatitude);
			if ((population>=0) && (surface>0) && (latitude!=0) && (longitude!=0)) {
				try {
					//SE CONNECTER A LA BDD
					Connection myConn = DriverManager.getConnection(url, user , password);
		
					//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
					Statement myStmt = myConn.createStatement();
					String sql = "SELECT * FROM Departement WHERE code = '" + codeDepartement + "'";
					ResultSet rs = myStmt.executeQuery(sql);
					if (rs.next()) {
						// On suppose ici que deux communes ne peuvent avoir le même nom dans un seul département
						String sql2 = "SELECT * FROM Commune WHERE departement = '" + codeDepartement + "' AND nom_reel = '" + nom + "'";
						ResultSet rs2 = myStmt.executeQuery(sql2);
						if (rs2.next()) {
							result = ("Ce nom de commune existe déjà dans ce département.");
						} else {
							String sql3 = "INSERT INTO Commune (departement,nom_reel,population_2010,surface,longitude_deg,latitude_deg) VALUES ('" + codeDepartement + "','" + nom + "','" + population + "','" + surface + "','" + longitude + "','" + latitude + "')";
							myStmt.executeUpdate(sql3);
							result = ("Commune ajoutée.");
						}
					} else {
						result = ("Ce code de département n'existe pas . Modifiez le ou créez d'abord un nouveau département");
					}
				} catch (Exception exc){
					exc.printStackTrace();
				}
			} else {
				result = ("Veuillez entrer des données sous une forme correcte.");
			}	
		} else {
			result = ("Veuillez entrer des données sous une forme correcte.");
		}
		return result;
	}
	
	/**
	 * Procedure suppressionCommune()
	 * <p>
	 * Cette procedure supprime une Commune de la base de donnees à partir de l'id
	 *
	 * @param stringid un String qui représente l'id de la Commune
	 */
	public static String suppressionCommune(String stringid) {
		String result = "";
		if (!stringid.isEmpty()) {
			int id = Integer.parseInt(stringid);
			try {
				//SE CONNECTER A LA BDD
				Connection myConn = DriverManager.getConnection(url, user , password);
		
				//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
				Statement myStmt = myConn.createStatement();
				String sql = "DELETE IGNORE FROM Commune WHERE id = " + id;
				myStmt.executeUpdate(sql);
				result = ("Commune suprimée.");
			} catch (Exception exc){
				exc.printStackTrace();
			}
		} else {
			result = "Veuillez entrer tous les paramètres";
		}
		
		return result;
	}
	
	/**
	 * Procedure suppressionCommune()
	 * <p>
	 * Cette procedure supprime une Commune de la base de donnees à partir du departement et du nom de la Commune
	 *
	 * @param departement un String qui représente le code du Departement dans lequel est la Commune
	 * @param nom un String qui représente le nom de la Commune
	 */
	public static void suppressionCommune(String departement, String nom) {
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
	
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			String sql = "DELETE IGNORE FROM Commune WHERE departement = '" + departement + "' AND nom_reel = '" + nom + "'";
			myStmt.executeUpdate(sql);
			System.out.println("Commune suprimée.");
		} catch (Exception exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * Procedure modifCommune()
	 * <p>
	 * Cette procedure modifie une Commune dans la base de donnees
	 *
	 * @param codeDepartement un String qui represente le code du departement dans lequel se situe la Commune
	 * @param nom un String qui represente le nom de la Commune a modifier
	 * @nouveauNom un String qui représente le nouveau nom de la Commune
	 * @param nouvellePopulation un int qui représente la nouvelle population de la Commune
	 */
	public static String modifCommune(String codeDepartement, String nom, String nouveauNom, int nouvellePopulation) {
		String result = "";
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
	
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			if (!codeDepartement.isEmpty() && !nom.isEmpty() && !nouveauNom.isEmpty() && (nouvellePopulation>=0)) {
				String sql = "UPDATE Commune SET nom_reel = '" + nouveauNom + "', population_2010 = " + nouvellePopulation + " WHERE departement = '" + codeDepartement + "' AND nom_reel = '" + nom + "'";				
				myStmt.executeUpdate(sql);
				result = ("Commune modifiée.");
			} else if (!codeDepartement.isEmpty() && !nom.isEmpty() && (nouvellePopulation>=0)) {
				String sql = "UPDATE Commune SET population_2010 = " + nouvellePopulation + " WHERE departement = '" + codeDepartement + "' AND nom_reel = '" + nom + "'";
				myStmt.executeUpdate(sql);
				result = ("Commune modifiée.");
			} else if (!codeDepartement.isEmpty() && !nom.isEmpty() && !nouveauNom.isEmpty()) {
				String sql = "UPDATE Commune SET nom_reel = '" + nouveauNom + "' WHERE departement = '" + codeDepartement + "' AND nom_reel = '" + nom + "'";
				myStmt.executeUpdate(sql);
				result=("Commune modifiée.");
			} else {
				result=("Veuillez entrer des données sous une forme correcte.");
			}
		} catch (Exception exc){
			exc.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Procedure modifCommune()
	 * <p>
	 * Cette procedure modifie une Commune dans la base de donnees
	 *
	 * @param stringid un String qui represente l'id de la Commune a modifier
	 * @param nouveauNom un String qui représente le nouveau nom de la Commune
	 * @param stringnouvellePopulation un String qui représente la nouvelle population de la Commune
	 */
	public static String modifCommune(String stringid, String nouveauNom, String stringnouvellePopulation) {
		String result = "";
		if (!stringid.isEmpty() && (!nouveauNom.isEmpty() || !stringnouvellePopulation.isEmpty())) {
			int id = Integer.parseInt(stringid);
			try {
				//SE CONNECTER A LA BDD
				Connection myConn = DriverManager.getConnection(url, user , password);
		
				//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
				Statement myStmt = myConn.createStatement();
				
				String sql1 = "SELECT * FROM Commune WHERE id = " + id;
				ResultSet rs1 = myStmt.executeQuery(sql1);
				if (!rs1.next()) {
					result = ("Cette commune n'est pas dans la base de données.");
				} else {
				
					if (!stringid.isEmpty() && !nouveauNom.isEmpty() && !stringnouvellePopulation.isEmpty()) {
						int nouvellePopulation = Integer.parseInt(stringnouvellePopulation);
						if (nouvellePopulation>=0) {
							String sql = "UPDATE Commune SET nom_reel = '" + nouveauNom + "', population_2010 = " + nouvellePopulation + " WHERE id = " + id;				
							myStmt.executeUpdate(sql);
							result = ("Commune modifiée.");
						} else {
							result = "Une population est un entier positif";
						}
					} else if (!stringid.isEmpty() && !stringnouvellePopulation.isEmpty()) {
						int nouvellePopulation = Integer.parseInt(stringnouvellePopulation);
						if (nouvellePopulation>=0) {
							String sql = "UPDATE Commune SET population_2010 = " + nouvellePopulation + " WHERE id = " + id;
							myStmt.executeUpdate(sql);
							result = ("Commune modifiée.");
						} else {
							result = "Une population est un entier positif";
						}
					} else {
						String sql = "UPDATE Commune SET nom_reel = '" + nouveauNom + "' WHERE id = " + id;
						myStmt.executeUpdate(sql);
						result = ("Commune modifiée.");
					}
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
	 * fonction etatCom()
	 * <p>
	 * Cette procedure retourne si la commune est confinée ou non
	 */
	public static int etatCom(int id) {
		String date = "";
		int conf = 0;
		
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);

			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			
			// On récupère la dernière date utilisée
			String sql = "SELECT * FROM Total";
			ResultSet rs = myStmt.executeQuery(sql);
			if (rs.next()) {
				date = rs.getString("date");
			}
			
			// On récupère l'état de la commune (confinée ou non)
			String sql1 = "SELECT * FROM Historique WHERE date_historique = '" + date + "' AND id_commune = " + id;
			ResultSet rs1 = myStmt.executeQuery(sql1);
			if (rs1.next()) {
				conf = rs1.getInt("id_confinee");
			}
		} catch (Exception exc){
			exc.printStackTrace();
		}
		
		return conf;
	}
	
	/**
	 * fonction etatCom()
	 * <p>
	 * Cette procedure retourne si la commune est confinée ou non
	 */
	public static int etatCom(Commune c) {
		String date = "";
		int conf = 0;
		
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);

			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES,INSERT .....
			Statement myStmt = myConn.createStatement();
			
			// On récupère la dernière date utilisée
			String sql = "SELECT * FROM Total";
			ResultSet rs = myStmt.executeQuery(sql);
			if (rs.next()) {
				date = rs.getString("date");
			}
			
			// On récupère l'état de la commune (confinée ou non)
			String sql1 = "SELECT * FROM Historique WHERE date_historique = '" + date + "' AND id_commune = " + c.id;
			ResultSet rs1 = myStmt.executeQuery(sql1);
			if (rs1.next()) {
				conf = rs1.getInt("id_confinee");
			}
		} catch (Exception exc){
			exc.printStackTrace();
		}
		
		return conf;
	}

	/**
	 * Procedure addDestination()
	 * <p>
	 * Cette procedure permet d'ajouter à une commune toutes ses communes adjacentes et leur distance.
	 */
	public void addDestination(Commune destination, double distance) {
		communesAdjacentes.put(destination, distance);
	}

	/**
	 * Fonction distance()
	 * <p>
	 * Cette fonction retourne la distance entre deux communes selon leurs coordonnées geodésiques
	 */
	public double distance(Commune communeA) {
		return 6371*Math.acos((Math.sin(this.getLatitude()*Math.PI/180)*Math.sin(communeA.getLatitude()*Math.PI/180))   +     (Math.cos(this.getLatitude()*Math.PI/180)*Math.cos(communeA.getLatitude()*Math.PI/180)*Math.cos((communeA.getLongitude()-this.getLongitude())*Math.PI/180)));
	}


	/**
	 * Fonction nomsAdjacents()
	 * <p>
	 * Cette fonction retourne le nom de communes adjacentes d'une commune donnée
	 */
	public LinkedList<String> nomsAdjacents(){
		
		LinkedList<String> inseeadjacents = new LinkedList<String>();
		Adjacent newAdjacent = new Adjacent("", "", "", "","");
		
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
			//ON CREE 2 STATEMENTs POUR POUVOIR FAIRE 2 REQUETES SIMULTANNÉES
			Statement myStmt = myConn.createStatement();
		
			String requete = "SELECT * FROM Adjacent WHERE LENGTH('id')<5 ";

			ResultSet rs2 = myStmt.executeQuery(requete);
			boolean next2 = rs2.next();
			
			//PARCOURS DE LA BDD CONTENANT LES COMMUNES ET LEURS COMMUNES ADJACENTES PAR NUMERO INSEE
			while (next2) {
				    newAdjacent.setNom(rs2.getString("nom"));
				    newAdjacent.setInsee(rs2.getString("id"));
				    newAdjacent.setNb_voisins(rs2.getString("nb_voisins"));
				    newAdjacent.setNoms_voisins(rs2.getString("noms_voisins"));
					
				    	if(newAdjacent.getNom().compareTo(this.getNom())==0) {   
				    		
				    		//DECOUPAGE DE LA CHIANE DE CARCATÈRE CONTENANT LES NMERO INSEE EN TABLEAU CONTENANT LES NOMS DES COMMUNES ADJACENTES
				    		  String inseeString = newAdjacent.getNoms_voisins();
					          String[] insee = inseeString.split("\\|"); 
				    		  for (int i = 0; i < insee.length; i++) {
						            System.out.println(insee[i]);
						            inseeadjacents.add(insee[i]);
						      }   
				    	}
					newAdjacent = new Adjacent("", "", "", "","");
					next2=rs2.next();
			}
			rs2.close();
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
	return inseeadjacents;
	}

	/**
	* Fonction communesAdjacentes()
	* <p>
	* Cette fonction retourne les communes adjacentes d'une commune donnée sous forme de liste
	*/
	public LinkedList<Commune> communesAdjacentes(){
		HashMap<String,String> inseeadjacents = new HashMap<String,String>();
		LinkedList<Commune> communesadjacentes = new LinkedList<Commune>();
		LinkedList<Commune> communes = new LinkedList<Commune>();
	
		Adjacent newAdjacent = new Adjacent("", "", "", "","");
		Commune newCommune = new Commune(null, 0, 0, 0, 0);
	
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
			//ON CREE 2 STATEMENTs POUR POUVOIR FAIRE 2 REQUETES SIMULTANNÉES
			Statement myStmt = myConn.createStatement();
			Statement myStmt2 = myConn.createStatement();

			String requete = "SELECT * FROM Commune WHERE departement = '01' OR departement ='69' OR departement ='38'OR departement = '73' OR departement ='74' OR departement = '39' OR departement ='71';";
			String requete2 = "SELECT * FROM Adjacent WHERE LENGTH('id')<5 ";
		
			ResultSet rs = myStmt.executeQuery(requete);
			boolean next = rs.next();

			ResultSet rs2 = myStmt2.executeQuery(requete2);
			boolean next2 = rs2.next();
		
			//PARCOURS DE LA BDD CONTENANT LES COMMUNES ET LEURS COMMUNES ADJACENTES PAR NUMERO INSEE
			while (next2) {
			    newAdjacent.setNom(rs2.getString("nom"));
			    newAdjacent.setInsee(rs2.getString("id"));
			    newAdjacent.setNb_voisins(rs2.getString("nb_voisins"));
			    newAdjacent.setNoms_voisins(rs2.getString("noms_voisins"));
			    newAdjacent.setInsee_voisins(rs2.getString("insee_voisins"));
			
			    	if(newAdjacent.getNom().compareTo(this.getNom())==0) {   
			    		
			    		//DECOUPAGE DES CHAINES DE CARCATÈRES CONTENANT LES NUMERO INSEE ET LES NOMS EN TABLEAU CONTENANT LES NOMS DES COMMUNES ADJACENTES ET LEUR NUMERO INSEE
			    		  String inseeString = newAdjacent.getInsee_voisins();
				          String[] insee = inseeString.split("\\|"); 
				          String nomString = newAdjacent.getNoms_voisins();
				          String[] nom = nomString.split("\\|"); 
				          if(nom.length==insee.length) {
				        	  // AJOUT DES NUMERO INSEE ET DES NOMS DANS UNE TABLE DE HACHAGE
				    		  for (int i = 0; i < nom.length; i++) {
						            inseeadjacents.put(insee[i],nom[i]);
						      }   
				          }
				       
			    	}
			    	
			   //REINITIALISATION DE LA COMMUNE 
				newAdjacent = new Adjacent("", "", "", "","");
				next2=rs2.next();
			}
			rs2.close();
		
			//PARCOURS DE LA BDD CONTENANT TOUTES LES COMMUNES
			while (next) {
			
				newCommune.setNom(rs.getString("nom_reel"));
				newCommune.setPopulation(rs.getInt("population_2010"));
				newCommune.setSurface(rs.getDouble("surface"));
				newCommune.setLatitude(rs.getDouble("latitude_deg"));
				newCommune.setLongitude(rs.getDouble("longitude_deg"));
				communes.add(newCommune);
		
				Iterator<String> iter = inseeadjacents.keySet().iterator();
			
				while(iter.hasNext()) {
					String p = iter.next();
					String departement =p.substring(0,2);
		
					// INTERSECTION DES DEUX BDD POUR TROUVER LA COMMUNE DEMANDÉE
					if((inseeadjacents.get(p).compareTo(newCommune.getNom())==0) && (departement.compareTo(rs.getString("departement"))==0)) { 
						communesadjacentes.add(newCommune);				    	
					}
				}
				newCommune = new Commune(null, 0, 0, 0, 0);
				next=rs.next();
			}
			rs.close();	
			}
			catch (Exception exc){
				exc.printStackTrace();
			}	
	return communesadjacentes;
	}


	/**
	 * Fonction communesAdjacentes()
	 * <p>
	 * Cette fonction retourne les communes adjacentes d'une commune donnée sous forme de Map  <Commune, distance>
	*/
	public Map<Commune, Double> communesAdjacentesMap(){
		HashMap<String,String> inseeadjacents = new HashMap<String,String>();
		Map<Commune, Double> communesadjacentesMap = new HashMap<Commune, Double>();
		LinkedList<Commune> communes = new LinkedList<Commune>();
	
		Adjacent newAdjacent = new Adjacent("", "", "", "","");
		Commune newCommune = new Commune(null, 0, 0, 0, 0);
	
		try {
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user , password);
			//ON CREE 2 STATEMENTs POUR POUVOIR FAIRE 2 REQUETES SIMULTANNÉES
			Statement myStmt = myConn.createStatement();
			Statement myStmt2 = myConn.createStatement();

			String requete = "SELECT * FROM Commune WHERE departement='01'";
			String requete2 = "SELECT * FROM Adjacent WHERE LENGTH('id')<5 ";
		
			ResultSet rs = myStmt.executeQuery(requete);
			boolean next = rs.next();

			ResultSet rs2 = myStmt2.executeQuery(requete2);
			boolean next2 = rs2.next();
		
			//PARCOURS DE LA BDD CONTENANT LES COMMUNES ET LEURS COMMUNES ADJACENTES PAR NUMERO INSEE
			while (next2) {
			    newAdjacent.setNom(rs2.getString("nom"));
			    newAdjacent.setInsee(rs2.getString("id"));
			    newAdjacent.setNb_voisins(rs2.getString("nb_voisins"));
			    newAdjacent.setNoms_voisins(rs2.getString("noms_voisins"));
			    newAdjacent.setInsee_voisins(rs2.getString("insee_voisins"));
			
			    	if(newAdjacent.getNom().compareTo(this.getNom())==0) {   
			    		
			    		//DECOUPAGE DES CHAINES DE CARCATÈRES CONTENANT LES NUMERO INSEE ET LES NOMS EN TABLEAU CONTENANT LES NOMS DES COMMUNES ADJACENTES ET LEUR NUMERO INSEE
			    		  String inseeString = newAdjacent.getInsee_voisins();
				          String[] insee = inseeString.split("\\|"); 
				          String nomString = newAdjacent.getNoms_voisins();
				          String[] nom = nomString.split("\\|"); 
				          if(nom.length==insee.length) {
				        	  // AJOUT DES NUMERO INSEE ET DES NOMS DANS UNE TABLE DE HACHAGE
				    		  for (int i = 0; i < nom.length; i++) {
						            inseeadjacents.put(insee[i],nom[i]);
						      }   
				          }
				       
			    	}
			    	
			   //REINITIALISATION DE LA COMMUNE 
				newAdjacent = new Adjacent("", "", "", "","");
				next2=rs2.next();
			}
			rs2.close();
		
			//PARCOURS DE LA BDD CONTENANT TOUTES LES COMMUNES
			while (next) {
				newCommune.setNom(rs.getString("nom_reel"));
				newCommune.setPopulation(rs.getInt("population_2010"));
				newCommune.setSurface(rs.getDouble("surface"));
				newCommune.setLatitude(rs.getDouble("latitude_deg"));
				newCommune.setLongitude(rs.getDouble("longitude_deg"));
				communes.add(newCommune);
		
				Iterator<String> iter = inseeadjacents.keySet().iterator();
				while(iter.hasNext()) {
					String p = iter.next();
					String departement =p.substring(0,2);
		
					// INTERSECTION DES DEUX BDD POUR TROUVER LA COMMUNE DEMANDÉE
					if((inseeadjacents.get(p).compareTo(newCommune.getNom())==0) && (departement.compareTo(rs.getString("departement"))==0)) { 
						communesadjacentesMap.put(newCommune, this.distance(newCommune));				    	
					}
				}
				newCommune = new Commune(null, 0, 0, 0, 0);
				next=rs.next();
			}
			rs.close();	
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
	return communesadjacentesMap;
	}

}
