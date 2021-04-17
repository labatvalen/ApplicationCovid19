package com.Projet;

public class Adjacent {
	
	
	/**
	 * Connexion a la BDD
	 */
	public static String url="jdbc:mysql://localhost:3306/Projet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static String user = "projet";
	public static String password = "mdp";
	
	private String insee;
	private String nom;
	private String nb_voisins;
	private String insee_voisins;
	private String noms_voisins;
	

//	************************************ CONSTRUCTOR ************************************
	



	/**
	 * Constructeur basique Adjacent
	 *
	 * @param nom une String qui représente le nom de la Commune source
	 * @param numInsee, un Int qui représente le numéro insee de la commune source
	 * @param nb_voisins un int qui représente le nombre de voisins de la commune source
	 * @param longitude une string qui représente les numeros insee des voisins de la commune source
	 */
	
	public Adjacent(String insee, String nom, String nb_voisins, String insee_vosins, String noms_voisins) {
		super();
		this.insee = insee;
		this.nom = nom;
		this.nb_voisins = nb_voisins;
		this.insee_voisins = insee_vosins;
		this.noms_voisins = noms_voisins;
	}
	
	
	/**
	 * Ensemble des getters et setters
	 */
//	************************************ GETTER ************************************
	public String getNom() {
		return this.nom;
	}

	public String getInsee() {
		return insee;
	}

	public String getNb_voisins() {
		return nb_voisins;
	}

	public String getInsee_voisins() {
		return insee_voisins;
	}
	
	public String getNoms_voisins() {
		return noms_voisins;
	}


//	************************************ SETTER ************************************
	
	public void setInsee(String insee) {
		this.insee = insee;
	}

	public void setNb_voisins(String d) {
		this.nb_voisins = d;
	}

	public void setInsee_voisins(String insee_voisins) {
		this.insee_voisins = insee_voisins;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setNoms_voisins(String noms_voisins) {
		this.noms_voisins = noms_voisins;
	}

//	************************************ METHODS ************************************
	
	public String toString() {
	    return  nom+ "[n°Insee:" +insee+ ", NbVoisins:"+ nb_voisins+ ", n°InseeVoisins:" + insee_voisins +",nomsVoisins:"+noms_voisins +"]";
	}


	

//	*********************************** FONCTIONS ***********************************

	
	
	
	
	
	
	
	
	
	
}
