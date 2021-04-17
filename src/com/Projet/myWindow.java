/**
 * Voici le code de l'implementation de la classe myWindow
 */


/**
 * Imports
 */
package com.Projet;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;


/**
 * Voici le code de l'implementation de l'interface graphique
 */


public class myWindow {

	private JFrame frame;
	
	private JFrame frame_region_2;
	private JTextField codeRegionT2;
	private JTextField codeRegionT3;
	private JTextField nameRegion3T;
	private JFrame frame_region;
	private JTextField nameRegionT;
	private JTextField codeRegionT;
	private JFrame frame_region_3;

	private JFrame frame_departement;
	private JFrame frame_departement_2;
	private JFrame frame_departement_3;
	private JTextField codeRegionTbis;
	private JTextField codeT;
	private JTextField nameDptT;
	private JTextField codeDptT2;
	private JTextField codeDptT3;
	private JTextField nameDpt3T;
	private JTextField codeDptT;
	
	private JFrame frame_commune;
	private JFrame frame_commune_2;
	private JFrame frame_commune_3;
	private JTextField codeCommuneT2;
	private JTextField codeCommuneT3;
	private JTextField nameCommune3T;
	private JTextField nomT;
	private JTextField popT;
	private JTextField surfaceT;
	private JTextField longitudeT;
	private JTextField latitudeT;
	
	private JFrame frame_simulation;
	private JTextField joursT;
	private JTextField r1T;
	private JTextField r2T;
	private JTextField r3T;
	
	private JFrame frame_vidage;
	
	private JFrame frame_distance;
	private JTextField c1T;
	private JTextField c2T;
	private JTextField popuT;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myWindow window = new myWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public myWindow() {
		initialize();
		
		region1();
		region2();
		region3();
		
		departement1();
		departement2();
		departement3();
		
		commune1();
		commune2();
		commune3();
		
		simulation();
		vidage();
		
		distance();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);


		
		// Titre de l'interface
		JLabel lblProjetTransverse = new JLabel("Projet Transverse");
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 25));
		lblProjetTransverse.setBounds(290, 20, 270, 40);
		panel.add(lblProjetTransverse);

		JButton addRegion = new JButton("Ajouter une région");
		addRegion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_region = new myWindow();
				myWindow_region.frame_region.setVisible(true);
			}
		});
		addRegion.setBounds(30, 110, 250, 25);
		panel.add(addRegion);

		JButton suppRegion = new JButton("Supprimer une région");
		suppRegion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_region_2 = new myWindow();
				myWindow_region_2.frame_region_2.setVisible(true);
			}
		});
		suppRegion.setBounds(300, 110, 250, 25);
		panel.add(suppRegion);
		
		JButton modifRegion = new JButton("Modifier une région");
		modifRegion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_region_3 = new myWindow();
				myWindow_region_3.frame_region_3.setVisible(true);
			}
		});
		modifRegion.setBounds(570, 110, 250, 25);
		panel.add(modifRegion);

		JButton addDpt = new JButton("Ajouter un département");
		addDpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_departement_1 = new myWindow();
				myWindow_departement_1.frame_departement.setVisible(true);
			}
		});
		addDpt.setBounds(30, 170, 250, 25);
		panel.add(addDpt);
		
		JButton suppDpt = new JButton("Supprimer un département");
		suppDpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_departement_2 = new myWindow();
				myWindow_departement_2.frame_departement_2.setVisible(true);
			}
		});
		suppDpt.setBounds(300, 170, 250, 25);
		panel.add(suppDpt);
		
		JButton modifDpt = new JButton("Modifier un département");
		modifDpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_departement_3 = new myWindow();
				myWindow_departement_3.frame_departement_3.setVisible(true);
			}
		});
		modifDpt.setBounds(570, 170, 250, 25);
		panel.add(modifDpt);
		
		JButton addCommune = new JButton("Ajouter une commune");
		addCommune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_commune_1 = new myWindow();
				myWindow_commune_1.frame_commune.setVisible(true);
			}
		});
		addCommune.setBounds(30, 230, 250, 25);
		panel.add(addCommune);
		
		JButton suppCommune = new JButton("Supprimer une commune");
		suppCommune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_commune_2 = new myWindow();
				myWindow_commune_2.frame_commune_2.setVisible(true);
			}
		});
		suppCommune.setBounds(300, 230, 250, 25);
		panel.add(suppCommune);
		
		JButton modifCommune = new JButton("Modifier une commune");
		modifCommune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_commune_3 = new myWindow();
				myWindow_commune_3.frame_commune_3.setVisible(true);
			}
		});
		modifCommune.setBounds(570, 230, 250, 25);
		panel.add(modifCommune);
		
		JButton simu = new JButton("Lancer une simulation");
		simu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_simulation = new myWindow();
				myWindow_simulation.frame_simulation.setVisible(true);
			}
		});
		simu.setBounds(30, 350, 250, 25);
		panel.add(simu);
		
		JButton vide = new JButton("Vider l'historique");
		vide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_vidage = new myWindow();
				myWindow_vidage.frame_vidage.setVisible(true);
			}
		});
		vide.setBounds(300, 350, 250, 25);
		panel.add(vide);
		
		JButton dist = new JButton("Calcul de Distance");
		dist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myWindow myWindow_distance = new myWindow();
				myWindow_distance.frame_distance.setVisible(true);
			}
		});
		dist.setBounds(570, 350, 250, 25);
		panel.add(dist);
	}

	
	/**
	 * Liste des fenêtres et des contenus
	 */
	
	private void region1() {
		frame_region = new JFrame();
		frame_region.setBounds(100, 100, 400, 400);
		frame_region.setLocationRelativeTo(null);

		JPanel panel_region = new JPanel();
		frame_region.getContentPane().add(panel_region, BorderLayout.CENTER);
		panel_region.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Ajouter une région");
		lblProjetTransverse.setBounds(110, 20, 200, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_region.add(lblProjetTransverse);

		JLabel coderegion = new JLabel("Code de la région");
		coderegion.setBounds(50, 80, 125, 15);
		panel_region.add(coderegion);

		codeRegionT = new JTextField();
		codeRegionT.setBounds(200, 80, 125, 20);
		panel_region.add(codeRegionT);
		codeRegionT.setColumns(10);

		JLabel nameRegion = new JLabel("Nom de la région");
		nameRegion.setBounds(50, 140, 125, 15);
		panel_region.add(nameRegion);

		nameRegionT = new JTextField();
		nameRegionT.setBounds(200, 140, 125, 20);
		panel_region.add(nameRegionT);
		nameRegionT.setColumns(10);

		JLabel Valide = new JLabel("");
		Valide.setBounds(50, 340, 300, 15);
		panel_region.add(Valide);

		JButton ajoutRegion = new JButton("Ajouter");
		ajoutRegion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = Region.ajoutRegion(codeRegionT.getText(),nameRegionT.getText());
				Valide.setText(res);
			}
		});
		ajoutRegion.setBounds(170, 300, 90, 25);
		panel_region.add(ajoutRegion);
	}

	private void region2() {
		frame_region_2 = new JFrame();
		frame_region_2.setBounds(100, 100, 500, 400);
		frame_region_2.setLocationRelativeTo(null);

		JPanel panel_region_2 = new JPanel();
		frame_region_2.getContentPane().add(panel_region_2, BorderLayout.CENTER);
		panel_region_2.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Supprimer une région");
		lblProjetTransverse.setBounds(160, 20, 200, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_region_2.add(lblProjetTransverse);

		JLabel codeRegion2 = new JLabel("Code de la région");
		codeRegion2.setBounds(100, 80, 125, 15);
		panel_region_2.add(codeRegion2);

		codeRegionT2 = new JTextField();
		codeRegionT2.setBounds(300, 80, 125, 20);
		panel_region_2.add(codeRegionT2);
		codeRegionT2.setColumns(10);

		JLabel Valide = new JLabel("");
		Valide.setBounds(50, 340, 400, 15);
		panel_region_2.add(Valide);

		JButton suppRegion = new JButton("Supprimer");
		suppRegion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = Region.suppressionRegion(codeRegionT2.getText());
				Valide.setText(res);
			}
		});
		suppRegion.setBounds(250, 300, 120, 25);
		panel_region_2.add(suppRegion);
	}

	
	private void region3() {
		frame_region_3 = new JFrame();
		frame_region_3.setBounds(100, 100, 500, 400);
		frame_region_3.setLocationRelativeTo(null);

		JPanel panel_region_3 = new JPanel();
		frame_region_3.getContentPane().add(panel_region_3, BorderLayout.CENTER);
		panel_region_3.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Modifier une région");
		lblProjetTransverse.setBounds(160, 20, 200, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_region_3.add(lblProjetTransverse);

		JLabel codeRegion3 = new JLabel("Code de la région");
		codeRegion3.setBounds(100, 80, 125, 15);
		panel_region_3.add(codeRegion3);

		codeRegionT3 = new JTextField();
		codeRegionT3.setBounds(250, 80, 125, 20);
		panel_region_3.add(codeRegionT3);
		codeRegionT3.setColumns(10);

		JLabel nameRegion3 = new JLabel("Nouveau nom");
		nameRegion3.setBounds(100, 120, 125, 15);
		panel_region_3.add(nameRegion3);

		nameRegion3T = new JTextField();
		nameRegion3T.setBounds(250, 120, 125, 20);
		panel_region_3.add(nameRegion3T);
		nameRegion3T.setColumns(10);

		JLabel Valide = new JLabel("");
		Valide.setBounds(50, 340, 400, 15);
		panel_region_3.add(Valide);

		JButton modifRegion = new JButton("Modifier");
		modifRegion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = Region.modifRegion(codeRegionT3.getText(), nameRegion3T.getText());
				Valide.setText(res);
			}
		});
		modifRegion.setBounds(200, 300, 110, 25);
		panel_region_3.add(modifRegion);
	}

	
	private void departement1() {
		frame_departement = new JFrame();
		frame_departement.setBounds(100, 100, 700, 400);
		frame_departement.setLocationRelativeTo(null);

		JPanel panel_departement = new JPanel();
		frame_departement.getContentPane().add(panel_departement, BorderLayout.CENTER);
		panel_departement.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Ajouter un département");
		lblProjetTransverse.setBounds(260, 20, 200, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_departement.add(lblProjetTransverse);

		JLabel coderegionbis = new JLabel("Code de la région");
		coderegionbis.setBounds(200, 80, 175, 15);
		panel_departement.add(coderegionbis);

		codeRegionTbis = new JTextField();
		codeRegionTbis.setBounds(400, 80, 125, 20);
		panel_departement.add(codeRegionTbis);
		codeRegionTbis.setColumns(10);

		JLabel code = new JLabel("Code du département");
		code.setBounds(200, 120, 175, 15);
		panel_departement.add(code);

		codeT = new JTextField();
		codeT.setBounds(400, 120, 125, 20);
		panel_departement.add(codeT);
		codeT.setColumns(10);
		
		JLabel nameDpt = new JLabel("Nom du département");
		nameDpt.setBounds(200, 160, 175, 15);
		panel_departement.add(nameDpt);

		nameDptT = new JTextField();
		nameDptT.setBounds(400, 160, 125, 20);
		panel_departement.add(nameDptT);
		nameDptT.setColumns(10);

		JLabel Valide = new JLabel("");
		Valide.setBounds(50, 340, 600, 15);
		panel_departement.add(Valide);

		JButton ajoutDpt = new JButton("Ajouter");
		ajoutDpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = Departement.ajoutDepartement(codeRegionTbis.getText(),codeT.getText(),nameDptT.getText());
				Valide.setText(res);
			}
		});
		ajoutDpt.setBounds(330, 300, 90, 25);
		panel_departement.add(ajoutDpt);
	}
	
	private void departement2() {
		frame_departement_2 = new JFrame();
		frame_departement_2.setBounds(100, 100, 500, 400);
		frame_departement_2.setLocationRelativeTo(null);

		JPanel panel_departement_2 = new JPanel();
		frame_departement_2.getContentPane().add(panel_departement_2, BorderLayout.CENTER);
		panel_departement_2.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Supprimer un département");
		lblProjetTransverse.setBounds(135, 20, 250, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_departement_2.add(lblProjetTransverse);

		JLabel codeDpt2 = new JLabel("Code du département");
		codeDpt2.setBounds(100, 80, 175, 15);
		panel_departement_2.add(codeDpt2);

		codeDptT2 = new JTextField();
		codeDptT2.setBounds(300, 80, 125, 20);
		panel_departement_2.add(codeDptT2);
		codeDptT2.setColumns(10);

		JLabel Valide = new JLabel("");
		Valide.setBounds(50, 340, 400, 15);
		panel_departement_2.add(Valide);

		JButton suppDpt = new JButton("Supprimer");
		suppDpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = Departement.suppressionDepartement(codeDptT2.getText());
				Valide.setText(res);
			}
		});
		suppDpt.setBounds(200, 300, 120, 25);
		panel_departement_2.add(suppDpt);
	}
	
	private void departement3() {
		frame_departement_3 = new JFrame();
		frame_departement_3.setBounds(100, 100, 500, 400);
		frame_departement_3.setLocationRelativeTo(null);

		JPanel panel_departement_3 = new JPanel();
		frame_departement_3.getContentPane().add(panel_departement_3, BorderLayout.CENTER);
		panel_departement_3.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Modifier un département");
		lblProjetTransverse.setBounds(135, 20, 250, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_departement_3.add(lblProjetTransverse);

		JLabel codeDpt3 = new JLabel("Code du département");
		codeDpt3.setBounds(100, 80, 175, 15);
		panel_departement_3.add(codeDpt3);

		codeDptT3 = new JTextField();
		codeDptT3.setBounds(300, 80, 125, 20);
		panel_departement_3.add(codeDptT3);
		codeDptT3.setColumns(10);

		JLabel nameDpt3 = new JLabel("Nouveau nom");
		nameDpt3.setBounds(100, 120, 175, 15);
		panel_departement_3.add(nameDpt3);

		nameDpt3T = new JTextField();
		nameDpt3T.setBounds(300, 120, 125, 20);
		panel_departement_3.add(nameDpt3T);
		nameDpt3T.setColumns(10);

		JLabel Valide = new JLabel("");
		Valide.setBounds(50, 340, 400, 15);
		panel_departement_3.add(Valide);

		JButton modifDpt = new JButton("Modifier");
		modifDpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = Departement.modifDepartement(codeDptT3.getText(), nameDpt3T.getText());
				Valide.setText(res);
			}
		});
		modifDpt.setBounds(200, 300, 110, 25);
		panel_departement_3.add(modifDpt);
	}
	
	private void commune1() {
		frame_commune = new JFrame();
		frame_commune.setBounds(100, 100, 600, 400);
		frame_commune.setLocationRelativeTo(null);

		JPanel panel_commune = new JPanel();
		frame_commune.getContentPane().add(panel_commune, BorderLayout.CENTER);
		panel_commune.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Ajouter une commune");
		lblProjetTransverse.setBounds(210, 20, 200, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_commune.add(lblProjetTransverse);

		JLabel codedepartement = new JLabel("Code du département");
		codedepartement.setBounds(125, 60, 175, 15);
		panel_commune.add(codedepartement);

		codeDptT = new JTextField();
		codeDptT.setBounds(325, 60, 125, 20);
		panel_commune.add(codeDptT);
		codeDptT.setColumns(10);

		JLabel nom = new JLabel("Nom de la commune");
		nom.setBounds(125, 100, 175, 15);
		panel_commune.add(nom);

		nomT = new JTextField();
		nomT.setBounds(325, 100, 125, 20);
		panel_commune.add(nomT);
		nomT.setColumns(10);

		JLabel pop = new JLabel("Population");
		pop.setBounds(125, 140, 175, 15);
		panel_commune.add(pop);

		popuT = new JTextField();
		popuT.setBounds(325, 140, 125, 20);
		panel_commune.add(popuT);
		popuT.setColumns(10);
		
		JLabel surface = new JLabel("Surface de la commune");
		surface.setBounds(125, 180, 175, 15);
		panel_commune.add(surface);

		surfaceT = new JTextField();
		surfaceT.setBounds(325, 180, 125, 20);
		panel_commune.add(surfaceT);
		surfaceT.setColumns(10);
		
		JLabel latitude = new JLabel("Latitude de la commune");
		latitude.setBounds(125, 220, 175, 15);
		panel_commune.add(latitude);

		latitudeT = new JTextField();
		latitudeT.setBounds(325, 220, 125, 20);
		panel_commune.add(latitudeT);
		latitudeT.setColumns(10);
		
		JLabel longitude = new JLabel("Longitude de la commune");
		longitude.setBounds(125, 260, 200, 15);
		panel_commune.add(longitude);

		longitudeT = new JTextField();
		longitudeT.setBounds(325, 260, 125, 20);
		panel_commune.add(longitudeT);
		longitudeT.setColumns(10);
		
		JLabel Valide = new JLabel("");
		Valide.setBounds(50, 340, 500, 15);
		panel_commune.add(Valide);

		JButton ajoutCommune = new JButton("Ajouter");
		ajoutCommune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = Commune.ajoutCommune(codeDptT.getText(),nomT.getText(),popuT.getText(),surfaceT.getText(),latitudeT.getText(),longitudeT.getText());
				Valide.setText(res);
			}
		});
		ajoutCommune.setBounds(270, 300, 90, 25);
		panel_commune.add(ajoutCommune);
	}

	private void commune2() {
		frame_commune_2 = new JFrame();
		frame_commune_2.setBounds(100, 100, 400, 400);
		frame_commune_2.setLocationRelativeTo(null);

		JPanel panel_commune_2 = new JPanel();
		frame_commune_2.getContentPane().add(panel_commune_2, BorderLayout.CENTER);
		panel_commune_2.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Supprimer un département");
		lblProjetTransverse.setBounds(110, 20, 200, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_commune_2.add(lblProjetTransverse);

		JLabel codeCommune2 = new JLabel("Id de la commune");
		codeCommune2.setBounds(50, 80, 125, 15);
		panel_commune_2.add(codeCommune2);

		codeCommuneT2 = new JTextField();
		codeCommuneT2.setBounds(200, 80, 125, 20);
		panel_commune_2.add(codeCommuneT2);
		codeCommuneT2.setColumns(10);

		JLabel Valide = new JLabel("");
		Valide.setBounds(50, 340, 300, 15);
		panel_commune_2.add(Valide);

		JButton suppCommune = new JButton("Supprimer");
		suppCommune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = Commune.suppressionCommune(codeCommuneT2.getText());
				Valide.setText(res);
			}
		});
		suppCommune.setBounds(150, 300, 120, 25);
		panel_commune_2.add(suppCommune);
	}

	private void commune3() {
		frame_commune_3 = new JFrame();
		frame_commune_3.setBounds(100, 100, 500, 400);
		frame_commune_3.setLocationRelativeTo(null);

		JPanel panel_commune_3 = new JPanel();
		frame_commune_3.getContentPane().add(panel_commune_3, BorderLayout.CENTER);
		panel_commune_3.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Modifier une commune");
		lblProjetTransverse.setBounds(160, 20, 200, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_commune_3.add(lblProjetTransverse);

		JLabel codeCommune3 = new JLabel("Id de la commune");
		codeCommune3.setBounds(100, 80, 125, 15);
		panel_commune_3.add(codeCommune3);

		codeCommuneT3 = new JTextField();
		codeCommuneT3.setBounds(250, 80, 125, 20);
		panel_commune_3.add(codeCommuneT3);
		codeCommuneT3.setColumns(10);

		JLabel nameCommune3 = new JLabel("Nouveau nom");
		nameCommune3.setBounds(100, 120, 125, 15);
		panel_commune_3.add(nameCommune3);

		nameCommune3T = new JTextField();
		nameCommune3T.setBounds(250, 120, 125, 20);
		panel_commune_3.add(nameCommune3T);
		nameCommune3T.setColumns(10);

		JLabel pop = new JLabel("Nouvelle population");
		pop.setBounds(100, 160, 150, 15);
		panel_commune_3.add(pop);

		popT = new JTextField();
		popT.setBounds(250, 160, 125, 20);
		panel_commune_3.add(popT);
		popT.setColumns(10);

		
		JLabel Valide = new JLabel("");
		Valide.setBounds(50, 340, 400, 15);
		panel_commune_3.add(Valide);

		JButton modifCommune = new JButton("Modifier");
		modifCommune.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = Commune.modifCommune(codeCommuneT3.getText(), nameCommune3T.getText(),popT.getText());
				Valide.setText(res);
			}
		});
		modifCommune.setBounds(200, 300, 110, 25);
		panel_commune_3.add(modifCommune);
	}
	
	private void simulation() {
		frame_simulation = new JFrame();
		frame_simulation.setBounds(100, 100, 500, 400);
		frame_simulation.setLocationRelativeTo(null);

		JPanel panel_simulation = new JPanel();
		frame_simulation.getContentPane().add(panel_simulation, BorderLayout.CENTER);
		panel_simulation.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Lancer une simulation");
		lblProjetTransverse.setBounds(160, 20, 200, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_simulation.add(lblProjetTransverse);

		JLabel jours = new JLabel("Nombre de jours");
		jours.setBounds(100, 60, 125, 15);
		panel_simulation.add(jours);

		joursT = new JTextField();
		joursT.setBounds(250, 60, 125, 20);
		panel_simulation.add(joursT);
		joursT.setColumns(10);

		JLabel r1 = new JLabel("Code région 1");
		r1.setBounds(100, 100, 125, 15);
		panel_simulation.add(r1);

		r1T = new JTextField();
		r1T.setBounds(250, 100, 125, 20);
		panel_simulation.add(r1T);
		r1T.setColumns(10);

		JLabel r2 = new JLabel("Code région 2");
		r2.setBounds(100, 140, 125, 15);
		panel_simulation.add(r2);

		r2T = new JTextField();
		r2T.setBounds(250, 140, 125, 20);
		panel_simulation.add(r2T);
		r2T.setColumns(10);
		
		JLabel r3 = new JLabel("Code région 3");
		r3.setBounds(100, 180, 125, 15);
		panel_simulation.add(r3);

		r3T = new JTextField();
		r3T.setBounds(250, 180, 125, 20);
		panel_simulation.add(r3T);
		r3T.setColumns(10);
		
		JLabel Valide = new JLabel("");
		Valide.setBounds(50, 340, 400, 15);
		panel_simulation.add(Valide);

		JButton simu = new JButton("Lancer");
		simu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String res = Scenario.simulation(joursT.getText(),r1T.getText(),r2T.getText(),r3T.getText());
				Valide.setText(res);
			}
		});
		simu.setBounds(220, 300, 90, 25);
		panel_simulation.add(simu);
	}
	
	private void vidage() {
		frame_vidage = new JFrame();
		frame_vidage.setBounds(100, 100, 400, 400);
		frame_vidage.setLocationRelativeTo(null);

		JPanel panel_vidage = new JPanel();
		frame_vidage.getContentPane().add(panel_vidage, BorderLayout.CENTER);
		panel_vidage.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Vider l'historique");
		lblProjetTransverse.setBounds(110, 20, 200, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_vidage.add(lblProjetTransverse);
		
		JLabel Valide = new JLabel("");
		Valide.setBounds(165, 340, 125, 15);
		panel_vidage.add(Valide);

		JButton vide = new JButton("Vider");
		vide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Scenario.vider();
				Valide.setText("Historique vidé");
			}
		});
		vide.setBounds(170, 300, 90, 25);
		panel_vidage.add(vide);
	}
	
	private void distance() {
		frame_distance = new JFrame();
		frame_distance.setBounds(100, 100, 800, 400);
		frame_distance.setLocationRelativeTo(null);

		JPanel panel_distance = new JPanel();
		frame_distance.getContentPane().add(panel_distance, BorderLayout.CENTER);
		panel_distance.setLayout(null);

		JLabel lblProjetTransverse = new JLabel("Tester la Distance");
		lblProjetTransverse.setBounds(310, 20, 200, 25);
		lblProjetTransverse.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_distance.add(lblProjetTransverse);
		
		JLabel c1 = new JLabel("Id de la commune de départ");
		c1.setBounds(250, 60, 125, 15);
		panel_distance.add(c1);

		c1T = new JTextField();
		c1T.setBounds(400, 60, 125, 20);
		panel_distance.add(c1T);
		c1T.setColumns(10);

		JLabel c2 = new JLabel("Id de la commune d'arrivée");
		c2.setBounds(250, 100, 125, 15);
		panel_distance.add(c2);

		c2T = new JTextField();
		c2T.setBounds(400, 100, 125, 20);
		panel_distance.add(c2T);
		c2T.setColumns(10);
		
		JLabel Valide = new JLabel("");
		Valide.setBounds(20, 340, 780, 15);
		panel_distance.add(Valide);

		JButton dist = new JButton("Tester");
		dist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String resultat = Scenario.testadj(c1T.getText(), c2T.getText());
				Valide.setText(resultat);
			}
		});
		dist.setBounds(370, 300, 90, 25);
		panel_distance.add(dist);
	}

}
