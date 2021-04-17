
package com.Projet;

import java.util.Iterator;
import java.util.LinkedList;

//Class répertoriant les chemins existant entre communes adjacentes
public class Distance {
	
	
    private Commune source;
    private Commune destination;
    private Double km = Double.MAX_VALUE;
    
//	************************************ CONSTRUCTORS ************************************
    
	public Distance(Commune source, Commune destination, double km) {
		super();
		
		this.source = source;
		this.destination = destination;
		this.km = km;
	}




//	************************************ GETTERS ************************************
	

	public Commune getSource() {
		return source;
	}

	public Commune getDestination() {
		return destination;
	}

	public double getKm() {
		return km;
	}
	


//	************************************ SETTERS ************************************

	public void setSource(Commune source) {
		this.source = source;
	}

	public void setDestination(Commune destination) {
		this.destination = destination;
	}

	public void setKm(Double km) {
		this.km = km;
	}

//	************************************ METHODS ************************************

	@Override
    public String toString() {
        return source + "-" + destination+ ":" + km + "km";
    }
	



	public boolean equals(Distance d) {
		return(super.equals(this)&&this.getSource()==d.getSource()&& this.getDestination()==d.getDestination());
	}
	
	

//	************************************ FONCTIONS ************************************
	
	/**
	 * Fonction distancesCommunesAdjacentes()
	 * <p>
	 * Cette fonction retourne les distances entre chaque commune adjacente par rapport à une commune donnée
	 */
	
	public LinkedList<Distance> distancesCommunesAdjacentes(Commune source){
		

		LinkedList<Distance> distances = new LinkedList<Distance>();
		//LinkedList<Commune> communes = new LinkedList<Commune>();
		LinkedList<Commune> communesAdj = source.communesAdjacentes();
		
		int i=0;
		Iterator<Commune> it = communesAdj.iterator();
		Distance distanceA = new Distance(source,null,0.00);
	    while(it.hasNext()) {
	    	i = i+1;
	    	Commune d = it.next();
	    	distanceA.setDestination(d);
	    	distanceA.setKm(source.distance(d));
	    	distances.add(distanceA);
	    	
	        distanceA = new Distance(source,null,0.00);
	    }
	  
		return distances;
		
	
	}
	
}