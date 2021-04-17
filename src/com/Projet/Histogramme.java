/**
 * Voici le code de l'implementation de la classe Histogramme
 */


/**
 * Imports
 */
package com.Projet;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Histogramme extends JFrame
{	
	/**
	 * Connexion a la BDD
	 */
	public static String url="jdbc:mysql://localhost:3306/Projet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static String user = "projet";
	public static String password = "mdp";
	
	private static final long serialVersionUID = 1L;

	// Le nom de la région et la date
	private String nom;
	private String dateEtude;
	
	// valeurs1 de l'histogramme
	private int[] valeursTxCommunesConfinees, valeursTxCommunesNonConfinees, valeursTxCommunesJamaisConfinees, valeursTxContamines, valeursTxMorts, valeursTxGueris;

	//max
	private int maxTxCommunesConfinees, maxTxCommunesNonConfinees, maxTxCommunesJamaisConfinees, maxTxContamines, maxTxMorts, maxTxGueris;

	//tableaux de couleurs
	private Color[] couleurs;
	
	//dates
	private String dateDebut, dateMilieu, dateFin;

	// Décalage en X du graphique par rapport à la gauche de la fenêtre
	private static final int DEC_X = 40;
   
	// Décalage en Y du graphique par rapport au bas de la fenêtre
	private static final int DEC_Y = 40;
   
	// Décalage en X du texte au-dessus des barres de l'histogramme
	private static final int DEC_TX = DEC_X + 5;
   
	// Décalage en Y du texte au-dessus des barres de l'histogramme
	private static final int DEC_TY = DEC_Y + 2;
   
	// Décalage en hauteur de la ligne permettant de créer la flèche
	private static final int DEC_FH = 4;
   
	// Décalage en longueur de la ligne permettant de créer la flèche
	private static final int DEC_FL = 8;
   
	// Largeur d'une barre de l'histogramme
	private static final int LG_B = 30;
   
	// INCR1ément pour calculer la hauteur des barres de l'histogramme en fonction de la valeur
	private static final int INCR1 = 4;
	private static final int INCR2 = 6;

	/**
	 * Fonction maxTab
	 *
	 * @param tab un tableau d'entier
	 * @return le maximum du tableau 
	 */
	public int maxTab(int tab[]) {
		int max = 0;
		for (int i=0; i<tab.length;i++) {
			if(tab[i]>max) {
				max=tab[i];
			}
		}
		return max;
	}
   
	/**
	 * Constructeur basique Histogramme
	 *
	 * @param code un String qui représente le code de la Region
	 * @param date1 un String qui représente la date
	 * @param date2 un String qui représente la date
	 * @param date3 un String qui représente la date 
	 */
	public Histogramme(String code, String date1, String date2, String date3)
	{
		super("Histogramme");
		
		try {
			
			//SE CONNECTER A LA BDD
			Connection myConn = DriverManager.getConnection(url, user, password);
			
			// On recupère le nom de la région
			Statement myStmt0 = myConn.createStatement();
			//ON DEFINIT NOS REQUETES
			String sql0 = "SELECT * FROM Region WHERE code ='"+code+"'";
			ResultSet rs0 = myStmt0.executeQuery(sql0);
			//INITIALITATION
			String nom = "";
			//RECUPERATION DES valeurs1
			while(rs0.next()) {
				nom = rs0.getString("nom");
			}
			System.out.println(nom);
			//Population de la region
			//ON CREE UN STATEMENT POUR POUVOIR FAIRE DES REQUETES
			Statement myStmt1 = myConn.createStatement();
			//ON DEFINIT NOS REQUETES
			String sql1 = "SELECT SUM(population_2010) AS somme FROM Commune c, Departement d WHERE c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs1 = myStmt1.executeQuery(sql1);
			//INITIALITATION
			int population = 0;
			//RECUPERATION DES valeurs1
			while(rs1.next()) {
				population = rs1.getInt("somme");
			}
			
			//nombre des communes
			Statement myStmt2 = myConn.createStatement();
			String sql2 = "SELECT COUNT(c.id) AS comptage FROM Commune c, Departement d WHERE c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs2 = myStmt2.executeQuery(sql2);
			int nbCommunes = 0;
			while(rs2.next()) {
				nbCommunes = rs2.getInt("comptage");
			}
			
			
			//pourcentage de communes confinées aux 3 dates
			Statement myStmt3 = myConn.createStatement();
			String sql3 = "SELECT COUNT(h.id) AS comptConf FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date1+"'"+"AND h.id_confinee=1 AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs3 = myStmt3.executeQuery(sql3);
			int nbConfines = 0;
			while(rs3.next()) {
				nbConfines = rs3.getInt("comptConf");
			}
			int txCommunesConfinees1 = (int) Math.floor((double) nbConfines/nbCommunes *100);
			
			Statement myStmt32 = myConn.createStatement();
			String sql32 = "SELECT COUNT(h.id) AS comptConf FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date2+"'"+" AND h.id_confinee=1 AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs32 = myStmt32.executeQuery(sql32);
			int nbConfines2 = 0;
			while(rs32.next()) {
				nbConfines2 = rs32.getInt("comptConf");
			}
			int txCommunesConfinees2 = (int) Math.floor((double) nbConfines2/nbCommunes *100);
			
			Statement myStmt33 = myConn.createStatement();
			String sql33 = "SELECT COUNT(h.id) AS comptConf FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date3+"'"+" AND h.id_confinee=1 AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs33 = myStmt33.executeQuery(sql33);
			int nbConfines3 = 0;
			while(rs33.next()) {
				nbConfines3 = rs33.getInt("comptConf");
			}
			int txCommunesConfinees3 = (int) Math.floor((double) nbConfines3/nbCommunes *100);
			
			
			//pourcentage de communes non confinees aux trois dates
			Statement myStmt4 = myConn.createStatement();
			String sql4 = "SELECT COUNT(h.id) AS comptNonConf FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date1+"'"+" AND h.id_confinee=0 AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs4 = myStmt4.executeQuery(sql4);
			int nbNonConfines = 0;
			while(rs4.next()) {
				nbNonConfines = rs4.getInt("comptNonConf");
			}
			int txCommunesNonConfinees1 =(int) Math.floor((double) nbNonConfines/nbCommunes *100);
			
			Statement myStmt42 = myConn.createStatement();
			String sql42 = "SELECT COUNT(h.id) AS comptNonConf FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date2+"'"+" AND h.id_confinee=0 AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs42 = myStmt42.executeQuery(sql42);
			int nbNonConfines2 = 0;
			while(rs42.next()) {
				nbNonConfines2 = rs42.getInt("comptNonConf");
			}
			int txCommunesNonConfinees2 =(int) Math.floor((double) nbNonConfines2/nbCommunes *100);
			
			Statement myStmt43 = myConn.createStatement();
			String sql43 = "SELECT COUNT(h.id) AS comptNonConf FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date3+"'"+" AND h.id_confinee=0 AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs43 = myStmt43.executeQuery(sql43);
			int nbNonConfines3 = 0;
			while(rs43.next()) {
				nbNonConfines3 = rs43.getInt("comptNonConf");
			}
			int txCommunesNonConfinees3 =(int) Math.floor((double) nbNonConfines3/nbCommunes *100);
			
			
			//pourcentatge de communes jamais confinées aux trois dates
			Statement myStmt5 = myConn.createStatement();
			String sql5 = "SELECT COUNT(h.id) AS comptJamaisConf FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date1+"'"+" AND h.date_confinement IS NULL AND h.date_dernier_deconfinement IS NULL AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs5 = myStmt5.executeQuery(sql5);
			int nbJamaisConfines = 0;
			while(rs5.next()) {
				nbJamaisConfines = rs5.getInt("comptJamaisConf");
			}
			int txCommunesJamaisConfinees1 = (int) Math.floor((double) nbJamaisConfines/nbCommunes *100);
			
			Statement myStmt52 = myConn.createStatement();
			String sql52 = "SELECT COUNT(h.id) AS comptJamaisConf FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date2+"'"+" AND h.date_confinement IS NULL AND h.date_dernier_deconfinement IS NULL AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs52 = myStmt52.executeQuery(sql52);
			int nbJamaisConfines2 = 0;
			while(rs52.next()) {
				nbJamaisConfines2 = rs52.getInt("comptJamaisConf");
			}
			int txCommunesJamaisConfinees2 = (int) Math.floor((double) nbJamaisConfines2/nbCommunes *100);
			
			Statement myStmt53 = myConn.createStatement();
			String sql53 = "SELECT COUNT(h.id) AS comptJamaisConf FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date3+"'"+" AND h.date_confinement IS NULL AND h.date_dernier_deconfinement IS NULL AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs53 = myStmt53.executeQuery(sql53);
			int nbJamaisConfines3 = 0;
			while(rs53.next()) {
				nbJamaisConfines3 = rs53.getInt("comptJamaisConf");
			}
			int txCommunesJamaisConfinees3 = (int) Math.floor((double) nbJamaisConfines3/nbCommunes *100);
			
			
			//pourcentage de personnes contaminées aux trois dates dans les 24h
			Statement myStmt6 = myConn.createStatement();
			String sql6 = "SELECT SUM(nombre_contamines_24h) AS sommeContamines FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date1+"'"+" AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs6 = myStmt6.executeQuery(sql6);
			int nbContamines = 0;
			while(rs6.next()) {
				nbContamines = rs6.getInt("sommeContamines");
			}
			int txContamines1 = (int)Math.floor((double) nbContamines/population *100);
			
			Statement myStmt62 = myConn.createStatement();
			String sql62 = "SELECT SUM(nombre_contamines_24h) AS sommeContamines FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date2+"'"+" AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs62 = myStmt62.executeQuery(sql62);
			int nbContamines2 = 0;
			while(rs62.next()) {
				nbContamines2 = rs62.getInt("sommeContamines");
			}
			int txContamines2 = (int)Math.floor((double) nbContamines2/population *100);
			
			Statement myStmt63 = myConn.createStatement();
			String sql63 = "SELECT SUM(nombre_contamines_24h) AS sommeContamines FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date3+"'"+" AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs63 = myStmt63.executeQuery(sql63);
			int nbContamines3 = 0;
			while(rs63.next()) {
				nbContamines3 = rs63.getInt("sommeContamines");
			}
			int txContamines3 = (int)Math.floor((double) nbContamines3/population *100);

			
			//pourcentage de personnes mortes aux trois dates dans les 24h
			Statement myStmt7 = myConn.createStatement();
			String sql7 = "SELECT SUM(nombre_morts_24h) AS sommeMorts FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date1+"'"+" AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs7 = myStmt7.executeQuery(sql7);
			int nbMorts = 0;
			while(rs7.next()) {
				nbMorts = rs7.getInt("sommeMorts");
			}
			int txMorts1 = (int) Math.floor((double) nbMorts/population *100);
			
			Statement myStmt72 = myConn.createStatement();
			String sql72 = "SELECT SUM(nombre_morts_24h) AS sommeMorts FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date2+"'"+" AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs72 = myStmt72.executeQuery(sql72);
			int nbMorts2 = 0;
			while(rs72.next()) {
				nbMorts2 = rs72.getInt("sommeMorts");
			}
			int txMorts2 = (int) Math.floor((double) nbMorts2/population *100);
			
			Statement myStmt73 = myConn.createStatement();
			String sql73 = "SELECT SUM(nombre_morts_24h) AS sommeMorts FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date3+"'"+" AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs73 = myStmt73.executeQuery(sql73);
			int nbMorts3 = 0;
			while(rs73.next()) {
				nbMorts3 = rs73.getInt("sommeMorts");
			}
			int txMorts3 = (int) Math.floor((double) nbMorts3/population *100);

			
			//pourcentage de personnes gueris aux trois dates dans les 24h
			Statement myStmt8 = myConn.createStatement();
			String sql8 = "SELECT SUM(nombre_gueris_24h) AS sommeGueris FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date1+"'"+" AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs8 = myStmt8.executeQuery(sql8);
			int nbGueris = 0;
			while(rs8.next()) {
				nbGueris = rs8.getInt("sommeGueris");
			}
			int txGueris1 = (int) Math.floor((double) nbGueris/population *100);
			
			Statement myStmt82 = myConn.createStatement();
			String sql82 = "SELECT SUM(nombre_gueris_24h) AS sommeGueris FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date2+"'"+" AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs82 = myStmt82.executeQuery(sql82);
			int nbGueris2 = 0;
			while(rs82.next()) {
				nbGueris2 = rs82.getInt("sommeGueris");
			}
			int txGueris2 = (int) Math.floor((double) nbGueris2/population *100);
			
			Statement myStmt83 = myConn.createStatement();
			String sql83 = "SELECT SUM(nombre_gueris_24h) AS sommeGueris FROM Commune c, Departement d, Historique h WHERE h.id_commune=c.id AND h.date_historique='"+date3+"'"+" AND c.departement=d.code AND d.region_code='"+code+"'";
			ResultSet rs83 = myStmt83.executeQuery(sql83);
			int nbGueris3 = 0;
			while(rs83.next()) {
				nbGueris3 = rs83.getInt("sommeGueris");
			}
			int txGueris3 = (int) Math.floor((double) nbGueris3/population *100);

			// On récupère la date de l'étude
			Statement myStmt84 = myConn.createStatement();
			String sql84 = "SELECT * FROM Total";
			ResultSet rs84 = myStmt84.executeQuery(sql84);
			String dateEtude = "";
			while(rs84.next()) {
				dateEtude = rs84.getString("date");
			}
			
			// Initialisation du nom et de la date
			this.nom = nom;
			this.dateEtude = dateEtude;
			
			// Initialisation des valeurs1
			this.valeursTxCommunesConfinees = new int[3];
			this.valeursTxCommunesConfinees[0] = txCommunesConfinees1;
			this.valeursTxCommunesConfinees[1] = txCommunesConfinees2;
			this.valeursTxCommunesConfinees[2] = txCommunesConfinees3;
			this.maxTxCommunesConfinees = maxTab(valeursTxCommunesConfinees);
			
	      
			// Initialisation des valeurs2
			this.valeursTxCommunesNonConfinees = new int[3];
			this.valeursTxCommunesNonConfinees[0] = txCommunesNonConfinees1;
			this.valeursTxCommunesNonConfinees[1] = txCommunesNonConfinees2;
			this.valeursTxCommunesNonConfinees[2] = txCommunesNonConfinees3;
			this.maxTxCommunesNonConfinees = maxTab(valeursTxCommunesNonConfinees);
			
			// Initialisation des valeurs3
			this.valeursTxCommunesJamaisConfinees = new int[3];
			this.valeursTxCommunesJamaisConfinees[0] = txCommunesJamaisConfinees1;
			this.valeursTxCommunesJamaisConfinees[1] = txCommunesJamaisConfinees2;
			this.valeursTxCommunesJamaisConfinees[2] = txCommunesJamaisConfinees3;
			this.maxTxCommunesJamaisConfinees = maxTab(valeursTxCommunesJamaisConfinees);
			
			// Initialisation des valeurs4
			this.valeursTxContamines = new int[3];
			this.valeursTxContamines[0] = txContamines1;
			this.valeursTxContamines[1] = txContamines2;
			this.valeursTxContamines[2] = txContamines3;
			this.maxTxContamines = maxTab(valeursTxContamines);
						
			// Initialisation des valeurs5
			this.valeursTxMorts = new int[3];
			this.valeursTxMorts[0] = txMorts1;
			this.valeursTxMorts[1] = txMorts2;
			this.valeursTxMorts[2] = txMorts3;
			this.maxTxMorts = maxTab(valeursTxMorts);
			
			// Initialisation des valeurs6
			this.valeursTxGueris = new int[3];
			this.valeursTxGueris[0] = txGueris1;
			this.valeursTxGueris[1] = txGueris2;
			this.valeursTxGueris[2] = txGueris3;
			this.maxTxGueris = maxTab(valeursTxGueris);
	      

			// Initialisation des couleurs
			this.couleurs = new Color[3];
			this.couleurs[0] = Color.RED;
			this.couleurs[1] = Color.GREEN;
			this.couleurs[2] = Color.BLUE;
	      
			this.dateDebut=date1;
			this.dateMilieu=date2;
			this.dateFin=date3;

			// Propriétés de la fenêtre
			setLocation(50, 50);
			setSize(1400, 1000);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

   
	/**
	 * Fonction paint
	 * 
	 * Permet de peindre les graphiques
	 *
	 * @param g un Graphics 
	 */
	@Override
	public void paint(Graphics g)
	{
		// Déclaration des variables utiles pour les calculs de l'histogramme
		int x, y, x1, y1, x2, y2, largeur, hauteur;
		// Affichage du nom et de la date
		g.drawString("" + this.nom + " au " + this.dateEtude, 600, 200);
		
		// Affichage des barres de l'histogramme 1
		for(int i = 0; i < this.valeursTxCommunesConfinees.length; i++)
		{
			// Barre
			x = DEC_X + i * (LG_B + 1);
			y = getHeight() - DEC_Y - this.valeursTxCommunesConfinees[i] * INCR1;
			largeur = LG_B;
			hauteur = this.valeursTxCommunesConfinees[i] * INCR1;
			g.setColor(this.couleurs[i]);
			g.fillRect(x, y, largeur, hauteur);
         
			// Valeur
			x = DEC_TX + i * (LG_B + 1);
			y = getHeight() - DEC_TY - this.valeursTxCommunesConfinees[i] * INCR1;
			g.setColor(Color.BLACK);
			g.drawString("" + this.valeursTxCommunesConfinees[i], x, y);   
		}
		
		// Affichage de l'axe X1
		g.setColor(Color.BLACK);
		x1 = DEC_X;
		y1 = getHeight() - DEC_Y;
		x2 = x1 + this.valeursTxCommunesConfinees.length * LG_B + LG_B;
		y2 = y1;
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x2, y2, x2 - DEC_FL, y2 - DEC_FH);
		g.drawLine(x2, y2, x2 - DEC_FL, y2 + DEC_FH);
		g.drawString("Taux de communes confinées", 20, 980);
      
		// Affichage de l'axe Y1
		x1 = DEC_X;
		y1 = getHeight() - DEC_Y;
		x2 = x1;
		y2 = y1 - (this.maxTxCommunesConfinees+5) * INCR1;
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x2, y2, x2 - DEC_FH, y2 + DEC_FL);
		g.drawLine(x2, y2, x2 + DEC_FH, y2 + DEC_FL);
		
		
		
		// Affichage des barres de l'histogramme 2
		for(int i = 0; i < this.valeursTxCommunesNonConfinees.length; i++)
		{
			x = DEC_X + i * (LG_B + 1) + 300;
			y = getHeight() - DEC_Y - this.valeursTxCommunesNonConfinees[i] * INCR1;
			largeur = LG_B;
			hauteur = this.valeursTxCommunesNonConfinees[i] * INCR1;
			g.setColor(this.couleurs[i]);
       	  	g.fillRect(x, y, largeur, hauteur);
          
       	  	x = DEC_TX + i * (LG_B + 1) + 300;
       	  	y = getHeight() - DEC_TY - this.valeursTxCommunesNonConfinees[i] * INCR1;
       	  	g.setColor(Color.BLACK);
       	  	g.drawString("" + this.valeursTxCommunesNonConfinees[i], x, y);
		}

		// Affichage de l'axe X2
		g.setColor(Color.BLACK);
		x1 = DEC_X + 300;
		y1 = getHeight() - DEC_Y;
		x2 = x1 + this.valeursTxCommunesNonConfinees.length * LG_B + LG_B;
		y2 = y1;
		g.drawLine(x1, y1, x2, y2);
		g.drawLine(x2, y2, x2 - DEC_FL, y2 - DEC_FH);
		g.drawLine(x2, y2, x2 - DEC_FL, y2 + DEC_FH);
		g.drawString("Taux de communes non confinées", 300, 980);
      
		// Affichage de l'axe Y2
		x1 = DEC_X + 300;
    	 y1 = getHeight() - DEC_Y;
    	 x2 = x1;
    	 y2 = y1 - (this.maxTxCommunesNonConfinees+5) * INCR1;
    	 g.drawLine(x1, y1, x2, y2);
    	 g.drawLine(x2, y2, x2 - DEC_FH, y2 + DEC_FL);
    	 g.drawLine(x2, y2, x2 + DEC_FH, y2 + DEC_FL);
    	 
    	 
    	 
    	// Affichage des barres de l'histogramme 3
 		for(int i = 0; i < this.valeursTxCommunesJamaisConfinees.length; i++)
 		{
 			x = DEC_X + i * (LG_B + 1) + 600;
 			y = getHeight() - DEC_Y - this.valeursTxCommunesJamaisConfinees[i] * INCR1;
 			largeur = LG_B;
 			hauteur = this.valeursTxCommunesJamaisConfinees[i] * INCR1;
 			g.setColor(this.couleurs[i]);
        	  	g.fillRect(x, y, largeur, hauteur);
           
        	  	x = DEC_TX + i * (LG_B + 1) + 600;
        	  	y = getHeight() - DEC_TY - this.valeursTxCommunesJamaisConfinees[i] * INCR1;
        	  	g.setColor(Color.BLACK);
        	  	g.drawString("" + this.valeursTxCommunesJamaisConfinees[i], x, y);
 		}

 		// Affichage de l'axe X3
 		g.setColor(Color.BLACK);
 		x1 = DEC_X + 600;
 		y1 = getHeight() - DEC_Y;
 		x2 = x1 + this.valeursTxCommunesJamaisConfinees.length * LG_B + LG_B;
 		y2 = y1;
 		g.drawLine(x1, y1, x2, y2);
 		g.drawLine(x2, y2, x2 - DEC_FL, y2 - DEC_FH);
 		g.drawLine(x2, y2, x2 - DEC_FL, y2 + DEC_FH);
 		g.drawString("Taux de communes jamais confinées", 600, 980);
       
 		// Affichage de l'axe Y3
 		x1 = DEC_X + 600;
     	 y1 = getHeight() - DEC_Y;
     	 x2 = x1;
     	 y2 = y1 - (this.maxTxCommunesJamaisConfinees+5) * INCR1;
     	 g.drawLine(x1, y1, x2, y2);
     	 g.drawLine(x2, y2, x2 - DEC_FH, y2 + DEC_FL);
     	 g.drawLine(x2, y2, x2 + DEC_FH, y2 + DEC_FL);
     	 
     	 
     	// Affichage des barres de l'histogramme 4
 		for(int i = 0; i < this.valeursTxContamines.length; i++)
 		{
 			// Barre
 			x = DEC_X + i * (LG_B + 1);
 			y = getHeight() - DEC_Y - this.valeursTxContamines[i] * INCR2 - 550;
 			largeur = LG_B;
 			hauteur = this.valeursTxContamines[i] * INCR2;
 			g.setColor(this.couleurs[i]);
 			g.fillRect(x, y, largeur, hauteur);
          
 			// Valeur
 			x = DEC_TX + i * (LG_B + 1);
 			y = getHeight() - DEC_TY - this.valeursTxContamines[i] * INCR2 - 550;
 			g.setColor(Color.BLACK);
 			g.drawString("" + this.valeursTxContamines[i], x, y);   
 		}
 		
 		// Affichage de l'axe X4
 		g.setColor(Color.BLACK);
 		x1 = DEC_X;
 		y1 = getHeight() - DEC_Y - 550;
 		x2 = x1 + this.valeursTxContamines.length * LG_B + LG_B;
 		y2 = y1;
 		g.drawLine(x1, y1, x2, y2);
 		g.drawLine(x2, y2, x2 - DEC_FL, y2 - DEC_FH);
 		g.drawLine(x2, y2, x2 - DEC_FL, y2 + DEC_FH);
 		g.drawString("Taux de personnes contaminées", 20, 430);
 		g.drawString("dans les 24h", 80, 450);
       
 		// Affichage de l'axe Y4
 		x1 = DEC_X;
 		y1 = getHeight() - DEC_Y - 550;
 		x2 = x1;
 		y2 = y1 - (this.maxTxContamines+5) * INCR2;
 		g.drawLine(x1, y1, x2, y2);
 		g.drawLine(x2, y2, x2 - DEC_FH, y2 + DEC_FL);
 		g.drawLine(x2, y2, x2 + DEC_FH, y2 + DEC_FL);
 		
 		
 		
 		// Affichage des barres de l'histogramme 5
 	 	for(int i = 0; i < this.valeursTxMorts.length; i++)
 	 	{
 	 		// Barre
 	 		x = DEC_X + i * (LG_B + 1) + 300;
 	 		y = getHeight() - DEC_Y - this.valeursTxMorts[i] * INCR2 - 550;
 	 		largeur = LG_B;
 	 		hauteur = this.valeursTxMorts[i] * INCR2;
 	 		g.setColor(this.couleurs[i]);
 	 		g.fillRect(x, y, largeur, hauteur);
 	          
 	 		// Valeur
 	 		x = DEC_TX + i * (LG_B + 1) + 300;
 	 		y = getHeight() - DEC_TY - this.valeursTxMorts[i] * INCR2 - 550;
 	 		g.setColor(Color.BLACK);
 	 		g.drawString("" + this.valeursTxMorts[i], x, y);   
 	 	}
 	 		
 	 	// Affichage de l'axe X5
 	 	g.setColor(Color.BLACK);
 	 	x1 = DEC_X + 300;
 	 	y1 = getHeight() - DEC_Y - 550;
 	 	x2 = x1 + this.valeursTxMorts.length * LG_B + LG_B;
 	 	y2 = y1;
 	 	g.drawLine(x1, y1, x2, y2);
 	 	g.drawLine(x2, y2, x2 - DEC_FL, y2 - DEC_FH);
 	 	g.drawLine(x2, y2, x2 - DEC_FL, y2 + DEC_FH);
 	 	g.drawString("Taux de personnes décédées", 300, 430);
 	 	g.drawString("dans les 24h", 360, 450);
 	       
 	 	// Affichage de l'axe Y5
 	 	x1 = DEC_X + 300;
 	 	y1 = getHeight() - DEC_Y - 550;
 	 	x2 = x1;
 	 	y2 = y1 - (this.maxTxMorts+5) * INCR2;
 	 	g.drawLine(x1, y1, x2, y2);
 	 	g.drawLine(x2, y2, x2 - DEC_FH, y2 + DEC_FL);
 	 	g.drawLine(x2, y2, x2 + DEC_FH, y2 + DEC_FL);
 	 	
 	 	
 	 	
 	// Affichage des barres de l'histogramme 6
 	 	for(int i = 0; i < this.valeursTxGueris.length; i++)
 	 	{
 	 		// Barre
 	 		x = DEC_X + i * (LG_B + 1) + 600;
 	 		y = getHeight() - DEC_Y - this.valeursTxGueris[i] * INCR2 - 550;
 	 		largeur = LG_B;
 	 		hauteur = this.valeursTxGueris[i] * INCR2;
 	 		g.setColor(this.couleurs[i]);
 	 		g.fillRect(x, y, largeur, hauteur);
 	          
 	 		// Valeur
 	 		x = DEC_TX + i * (LG_B + 1) + 600;
 	 		y = getHeight() - DEC_TY - this.valeursTxGueris[i] * INCR2 - 550;
 	 		g.setColor(Color.BLACK);
 	 		g.drawString("" + this.valeursTxGueris[i], x, y);   
 	 	}
 	 		
 	 	// Affichage de l'axe X6
 	 	g.setColor(Color.BLACK);
 	 	x1 = DEC_X + 600;
 	 	y1 = getHeight() - DEC_Y - 550;
 	 	x2 = x1 + this.valeursTxGueris.length * LG_B + LG_B;
 	 	y2 = y1;
 	 	g.drawLine(x1, y1, x2, y2);
 	 	g.drawLine(x2, y2, x2 - DEC_FL, y2 - DEC_FH);
 	 	g.drawLine(x2, y2, x2 - DEC_FL, y2 + DEC_FH);
 	 	g.drawString("Taux de personnes guéries", 600, 430);
 	 	g.drawString("dans les 24h", 660, 450);
 	       
 	 	// Affichage de l'axe Y6
 	 	x1 = DEC_X + 600;
 	 	y1 = getHeight() - DEC_Y - 550;
 	 	x2 = x1;
 	 	y2 = y1 - (this.maxTxGueris+5) * INCR2;
 	 	g.drawLine(x1, y1, x2, y2);
 	 	g.drawLine(x2, y2, x2 - DEC_FH, y2 + DEC_FL);
 	 	g.drawLine(x2, y2, x2 + DEC_FH, y2 + DEC_FL);
 	 	
 	 	//legende
 	 	for(int i = 0; i < 3; i++)
 	 	{
 	 		// Barre
 	 		x = 1000;
 	 		y = 500+i*50;
 	 		largeur = LG_B;
 	 		hauteur = LG_B;
 	 		g.setColor(this.couleurs[i]);
 	 		g.fillRect(x, y, largeur, hauteur); 
 	 	}
 	 	g.setColor(Color.BLACK);
 	 	g.drawString(dateDebut, 1040, 520);
 	 	g.drawString(dateMilieu, 1040, 570);
 	 	g.drawString(dateFin, 1040, 620);
	}
}