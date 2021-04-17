
package com.Projet;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra {

//	************************************ ATTRIBUTS ************************************
		private static Set<Commune>communesDistanceInconnue = new HashSet<Commune>();
		private static Set<Commune>communeDistanceConnue= new HashSet<Commune>();


//  ************************************ DIJKSTRA ************************************
	
		
		public static Graphe calculPlusCourtCheminDepuisSource(Graphe graphe, Commune source) {
	
			 source.setDistance(0.00);
			 communesDistanceInconnue.add(source);

			    while (communesDistanceInconnue.size() != 0) {
			        Commune communeA = CommuneLaPlusPetiteDistance(communesDistanceInconnue);
			        communesDistanceInconnue.remove(communeA);
			       
			        for (Entry<Commune, Double> communesAdjacentesDeA: communeA.communesAdjacentes.entrySet()) {
			           Commune communeAdjacentedeA = communesAdjacentesDeA.getKey();
			            Double distancedeA = communesAdjacentesDeA.getValue();
			            //System.out.println(edgeWeight);
			            if (communeDistanceConnue.contains(communeAdjacentedeA) == false) {
			                calculateMinimumDistance(communeAdjacentedeA, distancedeA, communeA);
			                communesDistanceInconnue.add(communeAdjacentedeA);
			            }
			        }
			        communeDistanceConnue.add(communeA);
			        graphe.addCommune(communeA);
			    }
			    return graphe;
			}

		// Renvoie la commune avec la distance à la source la plus basse des communes encore non visitées
		private static Commune CommuneLaPlusPetiteDistance(Set<Commune> communesDistanceInconnue) {
			
			  Commune lowestDistanceCommune = null;
			  Double lowestDistance = Double.MAX_VALUE;
			    for (Commune commune: communesDistanceInconnue) {
			        double distanceCommune = commune.getDistance();
			        if (distanceCommune < lowestDistance) {
			            lowestDistance = distanceCommune;
			            lowestDistanceCommune = commune;
			        }
			    }
			 //   System.out.println(lowestDistanceCommune);
			    return lowestDistanceCommune;
		}
		
		// Compare la distance réelle à la distance calculée qui passe par un nouveau chemin (communeTest)
		private static void calculateMinimumDistance(Commune communeTest, Double distanceDeA, Commune source) {
		    Double sourceDistance = source.getDistance();
		    if (sourceDistance +  distanceDeA < communeTest.getDistance()) {		 
		    	communeTest.setDistance(sourceDistance +  distanceDeA);
		        LinkedList<Commune> shortestPath = new LinkedList<>(source.getCheminLePlusCourt());
		        shortestPath.add(source);
		        communeTest.setCheminLePlusCourt(shortestPath);
		    }
		}

}
		

   

