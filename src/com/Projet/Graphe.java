package com.Projet;

import java.util.*;
import java.util.Iterator;

public class Graphe {

	private Set<Commune> communes = new HashSet<>();

//	************************************ CONSTRUCTORS ************************************
	public Graphe (Set<Commune> communes) {
		this.communes = communes;	
	}
	
//	************************************ GETTERS & SETTERS ************************************
	public Set<Commune> getCommunes() {
		return communes;
	}

//	************************************ METHODS ************************************
	public void addCommune (Commune communeA) {
		communes.add(communeA);
	}
}